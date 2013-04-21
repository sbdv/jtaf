package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.domain.access.JtafRequestFactory;
import org.jtaf.website.client.app.domain.access.UserProfileRequest;
import org.jtaf.website.client.app.domain.entities.UserProfileProxy;
import org.jtaf.website.client.app.ui.event.LoadingUserDataEvent;
import org.jtaf.website.client.app.ui.event.LoadingUserDataHandler;
import org.jtaf.website.client.app.ui.views.LoginView;
import org.jtaf.website.client.app.ui.views.LoginView.Presenter;
import org.jtaf.website.client.app.ui.widgets.Profile;
import org.jtaf.website.client.security.domain.access.SecuredReceiver;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class LoginPresenter implements Presenter {

    private static final Auth AUTH = Auth.get();
    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    // This app's personal client ID assigned by the Google APIs Console
    // (http://code.google.com/apis/console).
    private static final String GOOGLE_CLIENT_ID = "264546229669.apps.googleusercontent.com";
    // The auth scope being requested. This scope will allow the application to
    // identify who the authenticated user is.
    private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";
    private static final String DEFAULT_SPRING_LOGIN_URL = "j_spring_security_check";
    private final LoginView loginView;
    private final HandlerManager handlerManager;
    private final JtafRequestFactory requestFactory;
    private LoginEditorDriver loginEditorDriver;

    private String springLoginUrl = null;

    private String getSpringLoginUrl() {
        if (this.springLoginUrl == null) {
            this.springLoginUrl = GWT.getHostPageBaseURL() + DEFAULT_SPRING_LOGIN_URL;
        }
        return springLoginUrl;
    }

    @Inject
    public LoginPresenter(LoginView loginView, HandlerManager handlerManager, JtafRequestFactory requestFactory) {
        this.loginView = loginView;
        this.handlerManager = handlerManager;
		this.requestFactory = requestFactory;
        bind();
    }

    private void bind() {
    	//init databinding
    	loginEditorDriver = LoginEditorDriver.Util.getInstance();
    	loginEditorDriver.initialize(loginView.getProfile());
    	handlerManager.addHandler(LoadingUserDataEvent.TYPE,
				new LoadingUserDataHandler() {

					@Override
					public void onLoadingUserData(LoadingUserDataEvent event) {
						UserProfileRequest userRequest = requestFactory
								.getUserProfileRequest();
						userRequest.userProfileInformation().fire(
								new SecuredReceiver<UserProfileProxy>() {

									@Override
									public void onSuccess(
											UserProfileProxy response) {	
										loginView.logged();
										loginEditorDriver.display(response);
									}
								});
					}
				});
    }

    @Override
    public void processLogin() {
        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID).withScopes(PLUS_ME_SCOPE);
        AUTH.login(req, new Callback<String, Throwable>() {
            @Override
            public void onSuccess(String token) {
                loginView.isLoggingIn();
                final String url = GWT.getModuleBaseURL() + getSpringLoginUrl();

                final RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, url);

                rb.setHeader("Content-Type", "application/x-www-form-urlencoded");
                rb.setHeader("X-GWT-Secured", "Logging...");
                final StringBuilder sbParams = new StringBuilder(100);
                sbParams.append("j_username=");
                sbParams.append(token.toString());

                try {
                    rb.sendRequest(sbParams.toString(), new RequestCallback() {

                        @Override
                        public void onResponseReceived(final Request request, final Response response) {

                            int status = response.getStatusCode();
                            if (status == Response.SC_OK) { // 200: everything's ok
                                handlerManager.fireEvent(new LoadingUserDataEvent());
                            }
                            else if (status == Response.SC_UNAUTHORIZED) { // 401: oups...
                                loginView.notLogged();
                            }
                            else { // something else ?
                                loginView.notLogged();
                            }
                        }

                        @Override
                        public void onError(final Request request, final Throwable exception) {
                            loginView.notLogged();
                        }
                    });
                }
                catch (RequestException exception) {
                    loginView.notLogged();
                }
            }

            @Override
            public void onFailure(Throwable caught) {

            }
        });
    }
    
    public interface LoginEditorDriver extends RequestFactoryEditorDriver<UserProfileProxy, Profile>{
    	public class Util{
    		private Util(){
    			
    		}
    		private static LoginEditorDriver loginEditorDriver;
    		
    		public static LoginEditorDriver getInstance(){
    			if(loginEditorDriver == null){
    				loginEditorDriver = GWT.create(LoginEditorDriver.class);
    			}
    			return loginEditorDriver;
    		}
    	}
    }

	@Override
	public void alreadyLogged(UserProfileProxy user) {
		loginEditorDriver.display(user);
		loginView.logged();
	}

}
