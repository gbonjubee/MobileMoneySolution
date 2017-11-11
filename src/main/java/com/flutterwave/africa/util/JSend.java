/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flutterwave.africa.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author josepholaoye
 */
public class JSend {
       /**
     * Main kye that must be in response always.
     */
    public static final String STATUS = "status";

    /**
     * Acts as the wrapper for any data returned by the API call.
     * If the call returns no data (as in the last example), data should be set to null
     */
    public static final String DATA = "data";

    /**
     * Kye in response that follow width fail status
     */
    public static final String MESSAGE = "message";

    /**
     * All went well, and (usually) some data was returned.
     */
    public static final String SUCCESS = "success";

    /**
     * There was a problem with the data submitted, or some pre-condition of the API call wasn't satisfied
     */
    public static final String FAIL = "fail";

    /**
     * An error occurred in processing the request, i.e. an exception was thrown
     */
    public static final String ERROR = "error";

    /**
     * Validation JSend specification.
     *
     * @param object JSONObject
     * @return boolean
     *
     * @throws JSONException
     */
    public boolean isValid(JSONObject object) throws JSONException {

        if (!object.has(STATUS)) {
            // must have key "status"
            return false;
        } else if (!object.get(STATUS).equals(SUCCESS) && !object.get(STATUS).equals(FAIL) && !object.get(STATUS).equals(ERROR)) {
            // key "status" must be in "success", "fail" or "error" only
            return false;
        } else if (object.get(STATUS).equals(SUCCESS) && !object.has(DATA)) {
            // key "status" must be in pair width key "data"
            return false;
        } else if (object.get(STATUS).equals(FAIL) && !object.has(DATA)) {
            // key "fail" must be in pair width key "data"
            return false;
        } else if (object.get(STATUS).equals(ERROR) && !object.has(MESSAGE)) {
            // key "error" must be in pair width key "message"
            return false;
        }

        return true;
    }
}
