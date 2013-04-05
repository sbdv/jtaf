package org.jtaf.website.rf;

import org.jtaf.website.rf.util.SecurityServerFailure;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Transport sécurisé des request
 * 
 * @author mbellang
 * 
 */
public class SecuredRequestTransport extends DefaultRequestTransport {

    public static final String GWT_SECURED_REQUEST_HEADER = "X-GWT-Secured";
    public static final String GWT_SECURED_REQUEST_HEADER_VALUE = "SECURED";
    private static final String SERVER_ERROR = "Server Error";

    @Override
    protected RequestCallback createRequestCallback(final TransportReceiver receiver) {
        return new RequestCallback() {

            @Override
            public void onError(Request request, Throwable exception) {
                receiver.onTransportFailure(new ServerFailure(exception.getMessage()));
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (Response.SC_OK == response.getStatusCode()) {
                    String text = response.getText();
                    receiver.onTransportSuccess(text);
                }
                else if (Response.SC_FORBIDDEN == response.getStatusCode()
                        || Response.SC_UNAUTHORIZED == response.getStatusCode()) {
                    final String message = SERVER_ERROR + " " + response.getStatusCode() + " "
                            + response.getStatusText() + " " + response.getText();
                    final SecurityServerFailure serverFailure = new SecurityServerFailure(message);
                    serverFailure.setStatusCode(response.getStatusCode());
                    receiver.onTransportFailure(serverFailure);
                }
            }
        };
    }

    @Override
    protected void configureRequestBuilder(final RequestBuilder builder) {
        super.configureRequestBuilder(builder);
        builder.setHeader(GWT_SECURED_REQUEST_HEADER, GWT_SECURED_REQUEST_HEADER_VALUE);
    }

}
