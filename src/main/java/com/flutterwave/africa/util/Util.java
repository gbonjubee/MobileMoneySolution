/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flutterwave.africa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.core.Response;
import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author gbonjubolaamuda
 */
public class Util {

    public Util() {

    }

    private static final Logger LOG = Logger.getLogger(Util.class.getName());

    public static final boolean LIVE_FLAG = true;
    public static String SUCCESS_CODE = "00";
    public static String PENDING_CODE = "02";
    public static String ERROR_CODE = "01";
    public static final String MISSING_AUTH = "Missing Authorization Param";
    public static final String ERROR_RESPONSE_DUPLICATE = "Duplicate Request";
    public static final String ERROR_RESPONSE_CODE = "ER";
    public static final String ERROR_RESPONSE_CORE = "An error occurred while calling core";
    public static final String ERROR_RESPONSE_MSG = "An error occurred while fetching record";
    static final String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String getBankCode() {

        final String BASE_URL;
        if (LIVE_FLAG) {
            BASE_URL = getPropertyValue("bankcode");
        } else {
            BASE_URL = getPropertyValue("bankcode");
        }
        return BASE_URL;
    }

    public static String getMerchantID() {

        final String BASE_URL;
        if (LIVE_FLAG) {
            BASE_URL = getPropertyValue("merchantid");
        } else {
            BASE_URL = getPropertyValue("merchantid");
        }
        return BASE_URL;
    }

    public static String getUrl() {

        final String BASE_URL;
        if (LIVE_FLAG) {
            BASE_URL = getPropertyValue("url");
        } else {
            BASE_URL = getPropertyValue("url");
        }
        return BASE_URL;
    }

    public static String getPrivateKEY() {
        String KEY;
        if (LIVE_FLAG) {
            KEY = getPropertyValue("privatekey");

        } else {
            KEY = getPropertyValue("privatekey");

        }
        return KEY;
    }

    public static String getAPIKEY() {

        final String API_KEY;
        if (LIVE_FLAG) {
            API_KEY = getPropertyValue("apikey");
        } else {
            API_KEY = getPropertyValue("apikey");
        }
        return API_KEY;
    }

    public static String middlewareURL() {

        final String BASE_URL;
        if (LIVE_FLAG) {
            BASE_URL = getPropertyValue("miiddleware");
        } else {
            BASE_URL = getPropertyValue("miiddleware");
        }
        return BASE_URL;
    }

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString().toUpperCase();
    }

    public static String SHA512(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(key.getBytes());
            byte byteData[] = digest.digest();

            //convert the byte to hex format method 1
            StringBuilder hashCodeBuffer = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                hashCodeBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return hashCodeBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static String getConfig() {
        final String CONFIG_FILE_PATH;
        if (LIVE_FLAG) {
            CONFIG_FILE_PATH = "/opt/wildfly/ussdsettings.properties";
        } else {
            CONFIG_FILE_PATH = "/Users/gbonjubolaamuda/config/ussdsettings.properties";
        }
        return CONFIG_FILE_PATH;
    }

    public static String getPropertyValue(String value) {

        Properties prop = new Properties();

        try {

            File file = new File(getConfig());
            FileInputStream fileInput = new FileInputStream(file);
            prop.load(fileInput);
        } catch (IOException e) {
            LOG.error(Arrays.toString(e.getStackTrace()));
        }

        return prop.getProperty(value);
    }
}
