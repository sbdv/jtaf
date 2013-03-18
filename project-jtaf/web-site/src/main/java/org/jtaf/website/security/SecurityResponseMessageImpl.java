package org.jtaf.website.security;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * Classe permettant de récupère le message issu d'une request
 * 
 * @author mbellang
 * 
 */
public class SecurityResponseMessageImpl implements SecurityResponseMessage {

    private String message;

    private int status;

    private String loginFormUrl;

    /**
     * Récupère le message à partir d'une requête json
     * 
     * 
     * @param json
     * @return
     */
    public static final SecurityResponseMessage getSecurityResponseMessage(String json) {
        final JSONValue jsonValue = JSONParser.parseStrict(json);
        final JSONObject object = jsonValue.isObject();
        SecurityResponseMessage responseMsg = null;
        if (object != null) {
            final JSONValue statusValue = object.get("status");
            if (statusValue != null) {
                final JSONValue messageValue = object.get("message");
                final JSONValue loginFormUrlValue = object.get("loginFormUrl");
                final SecurityResponseMessageImpl msg = new SecurityResponseMessageImpl();
                msg.setStatus(statusValue.isNumber());
                msg.setMessage(messageValue.isString());
                msg.setLoginFormUrl((loginFormUrlValue == null) ? null : loginFormUrlValue.isString());
                responseMsg = msg;
            }
        }
        return responseMsg;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(JSONNumber jsonNumber) {
        this.status = (jsonNumber == null) ? 0 : Double.valueOf(jsonNumber.doubleValue()).intValue();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Surcharge du setMessage(String)
     * 
     * @param jsonString
     */
    public void setMessage(JSONString jsonString) {
        this.message = (jsonString == null) ? null : jsonString.stringValue();
    }

    @Override
    public String getLoginFormUrl() {
        return loginFormUrl;
    }

    @Override
    public void setLoginFormUrl(String loginFormUrl) {
        this.loginFormUrl = loginFormUrl;
    }

    /**
     * Surcharge setLoginFormUrl(String)
     * 
     * @param jsonString
     */
    public void setLoginFormUrl(JSONString jsonString) {
        this.loginFormUrl = (jsonString == null) ? null : jsonString.stringValue();
    }
}
