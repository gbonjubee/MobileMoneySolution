/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flutterwave.africa.util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author josepholaoye
 */
public class StandardRestResponse extends JSend {

    static JSONObject response;

    public static String sendSuccessMessage(String message) {
        JSONObject success = new JSONObject();
        success.putOnce(STATUS, SUCCESS);
        success.putOnce(DATA, message);
        System.out.println(success.toString());
        return success.toString();
    }

    public static String sendSuccessMessage(JSONObject message) {
        JSONObject success = new JSONObject();
        success.putOnce(STATUS, SUCCESS);
        success.putOnce(DATA, message);
        System.out.println(success.toString());
        return success.toString();
    }
    
     public static String sendSuccessMessage(JSONArray message) {
        JSONObject success = new JSONObject();
        success.putOnce(STATUS, SUCCESS);
        success.putOnce(DATA, message);
        System.out.println(success.toString());
        return success.toString();
    }

    public static String sendErrorMessage(String message) {
        JSONObject error = new JSONObject();
        error.putOnce(STATUS, ERROR);
        if (message != null) {
            error.putOnce(DATA, message);
        } else {
            error.putOnce(DATA, "An error occured!");
        }
        System.out.println(error.toString());
        return error.toString();
    }

    public static String sendErrorMessage(JSONObject message) {
        JSONObject error = new JSONObject();
        error.putOnce(STATUS, ERROR);
        if (message != null) {
            error.putOnce(DATA, message);
        } else {
            error.putOnce(DATA, "An error occured!");
        }
        System.out.println(error.toString());
        return error.toString();
    }
}
