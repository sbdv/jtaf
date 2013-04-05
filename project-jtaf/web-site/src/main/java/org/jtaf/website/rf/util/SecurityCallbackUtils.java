package org.jtaf.website.rf.util;


/**
 * Clase utilitaire des callBack sécurisés
 * 
 * @author mbellang
 * 
 */
public class SecurityCallbackUtils {

    /**
     * Parsing du json
     * 
     * @param rawMessage
     * @return string Json parsé
     */
    private static String extractJson(final String rawMessage) {
        if (rawMessage == null) {
            return null;
        }

        String jsonErrorMessage = null;

        int start = rawMessage.indexOf("{");
        int end = rawMessage.lastIndexOf("}");
        if (start > -1 && end > -1) {
            jsonErrorMessage = rawMessage.subSequence(start, end + 1).toString();
        }
        return jsonErrorMessage;
    }

    /**
     * Extract a SecurityResponseMessage, the old way (JSON parsing)
     * 
     * @param rawMessage
     *            a rawMessage (maybe null)
     * @return a SecurityResponseMessage or null if the message wasn't as expected.
     */
    public static final SecurityResponseMessage extractJsonMessage(final String rawMessage) {

        final String rawJson = SecurityCallbackUtils.extractJson(rawMessage);
        if (rawJson == null) {
            return null;
        }
        else {
            return SecurityResponseMessageImpl.getSecurityResponseMessage(rawJson);
        }

    }

    /**
     * Extract a SecurityResponseMessage, the modern way (AutoBean power)
     * 
     * @param rawMessage
     *            a rawMessage (maybe null)
     * @return a SecurityResponseMessage or null if the message wasn't as expected.
     */
    public static final SecurityResponseMessage extractJsonMessageWithAutoBean(final String rawMessage) {

        final String rawJson = SecurityCallbackUtils.extractJson(rawMessage);
        if (rawJson == null) {
            return null;
        }
        else {
            return SecurityResponseMessageFactory.Builder.getSecurityResponseMessage(rawJson);
        }

    }
}
