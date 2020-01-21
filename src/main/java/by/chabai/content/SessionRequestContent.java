package by.chabai.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private boolean invalidateSession = false;

    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getAttributeNames();
        String key;
        Object value;
        while (enumeration.hasMoreElements()) {
            key = enumeration.nextElement();
            value = request.getAttribute(key);
            requestAttributes.put(key, value);
        }

        String paramValue;
        enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            key = enumeration.nextElement();
            paramValue = request.getParameter(key);
            requestParameters.put(key, paramValue);
        }

        HttpSession session = request.getSession();
        enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            key = enumeration.nextElement();
            value = session.getAttribute(key);
            sessionAttributes.put(key, value);
        }
    }

    public void insertAttributes(HttpServletRequest request) {
        requestAttributes.forEach(request::setAttribute);
        HttpSession session = request.getSession();
        sessionAttributes.forEach(session::setAttribute);
        if (invalidateSession) {
            session.invalidate();
        }
    }

    public String getRequestParameter(String key) {
        return requestParameters.get(key);
    }

    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public void setRequestParameter(String key, String value) {
        requestParameters.put(key, value);
    }

    public void setRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
    }

    public void setSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    public void invalidateSession() {
        invalidateSession = true;
    }
}