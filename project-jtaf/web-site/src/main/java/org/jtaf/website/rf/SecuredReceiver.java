package org.jtaf.website.rf;

import org.jtaf.website.rf.util.SecurityCallbackUtils;
import org.jtaf.website.rf.util.SecurityResponseMessage;
import org.jtaf.website.rf.util.SecurityServerFailure;

import com.google.gwt.http.client.Response;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Classe abstraite des receiver request factory, gestion des exceptions
 * 
 * @author mbellang
 * 
 * @param <V>
 */
public abstract class SecuredReceiver<V> extends Receiver<V> implements ISecurityCallbackHandler {

    private static final String SPRINGSECURITY_ACCESSDENIEDEXCEPTION = "org.springframework.security.access.AccessDeniedException";
    private boolean canceled;

    @Override
    public final void onFailure(final ServerFailure error) {
        boolean logBaseError = true;
        if (error == null) {
            return;
        }
        final SecurityResponseMessage message = SecurityCallbackUtils.extractJsonMessage(error.getMessage());

        // erreur user non loggué
        if (isNotAuthorized(message)) {

            onAuthorizationExpected(message.getLoginFormUrl());
            return;
        }
        // erreur user non acrédité
        else if (isAccessDenied(error)) {

            onAccessDenied();
            logBaseError = false;
        }

        logBaseError = !onServiceFailure(error);
        // erreur non géré
        if (logBaseError) {

        }

    }

    /**
     * Test si c'est une erreur du au timeout
     * 
     * @param securityResponseMessage
     * @return vrai pour un timeout
     */
    protected boolean isNotAuthorized(final SecurityResponseMessage securityResponseMessage) {
        if (securityResponseMessage != null && securityResponseMessage.getStatus() == Response.SC_UNAUTHORIZED) {
            return true;
        }
        return false;
    }

    /**
     * Test si c'est une erreur de restriction d'accès
     * 
     * @param error
     * @return vrai pour un accès restreint
     */
    protected boolean isAccessDenied(final ServerFailure error) {
        boolean accessDenied = false;
        if (error instanceof SecurityServerFailure) {
            accessDenied = (((SecurityServerFailure) error).getStatusCode() == Response.SC_FORBIDDEN);
        }
        else if (SPRINGSECURITY_ACCESSDENIEDEXCEPTION.equals(error.getExceptionType())) {
            accessDenied = true;
        }
        return accessDenied;
    }

    @Override
    public void onAuthorizationExpected(final String externalLoginUrl) {

    }

    @Override
    public void onAccessDenied() {

    }

    /**
     * This is the new method to use instead of onFailure with a classic Receiver object.
     * 
     * @param error
     * @return true si un mécanisme de recovery à été mis en oeuvre, false si l'erreur doit être remontée comme une
     *         défaillance.
     */
    public boolean onServiceFailure(ServerFailure error) {
        super.onFailure(error);
        return false;
    }

    /**
     * Pattern de cancel des request
     * 
     */
    public void cancel() {
        canceled = true;
    }

    @Override
    public final void onSuccess(V response) {
        if (!canceled) {
            doOnSuccess(response);
        }
    }

    protected abstract void doOnSuccess(V response);
}
