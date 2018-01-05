package org.simpleserver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.HashMap;
import java.util.Map;

public abstract class Endpoint {
    private Map<String, String> pathParam = new HashMap<>();
    private int responseCode;

    public Object handleGET(Object request) {
        this.setResponseCode(500);
        return "Endpoint not implemented";
    }

    public Object handlePOST(Object request) {
        this.setResponseCode(500);
        return "Endpoint not implemented";
    }

    public Object handlePATCH(Object request) {
        this.setResponseCode(500);
        return "Endpoint not implemented";
    }

    public Object handlePUT(Object request) {
        this.setResponseCode(500);
        return "Endpoint not implemented";
    }

    public Object handleDELETE(Object request) {
        this.setResponseCode(500);
        return "Endpoint not implemented";
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String handleRequest(String requestMethod, String requestBody) {
        Object reqObj = JSONValue.parse(requestBody);

        Object respObj = null;
        if(requestMethod.equals("GET")) {
            respObj = handleGET(reqObj);
        }
        if(requestMethod.equals("POST")) {
            respObj = handlePOST(reqObj);
        }
        if(requestMethod.equals("PUT")) {
            respObj = handlePUT(reqObj);
        }
        if(requestMethod.equals("PATCH")) {
            respObj = handlePATCH(reqObj);
        }
        if(requestMethod.equals("DELETE")) {
            respObj = handleDELETE(reqObj);
        }

        if(respObj == null) {
            return "";
        }
        if(respObj instanceof String) {
            return (String)respObj;
        }
        if(respObj instanceof JSONObject) {
            return ((JSONObject)respObj).toJSONString();
        }
        if(respObj instanceof JSONArray) {
            return ((JSONArray)respObj).toJSONString();
        }
        return "";
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void addPathParam(String key, String value) {
        pathParam.put(key, value);
    }

    public String getPathParam(String key) {
        return pathParam.get(key);
    }

    public boolean hasPathParam(String id) {
        return pathParam.containsKey(id);
    }
}
