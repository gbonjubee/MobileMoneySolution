/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flutterwave.africa.service;

import com.flutterwave.africa.bean.AccountBean;
import com.flutterwave.africa.bean.SingleDisburseBean;
import com.flutterwave.africa.bean.ValidateBean;
import com.flutterwave.africa.util.EncryptionHelper;
import com.flutterwave.africa.util.ResponseFormatter;
import com.flutterwave.africa.util.StandardRestResponse;
import com.flutterwave.africa.util.Util;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author gbonjubolaamuda
 */
public class ServiceHelper {

    public ServiceHelper() {
    }
    private static final Logger LOG = Logger.getLogger(ServiceHelper.class.getName());

    public String accountPay(AccountBean accountBean) {

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            HttpPost post = new HttpPost((Util.middlewareURL() + "pwc/rest/account/pay"));
            post.addHeader("Authorization", Util.getAPIKEY());

            JSONObject object = new JSONObject();
            object.put("accountnumber", new EncryptionHelper().harden(accountBean.getAccountnumber()));
            object.put("amount", new EncryptionHelper().harden(accountBean.getAmount()));
            object.put("bankcode", new EncryptionHelper().harden(accountBean.getBankcode()));
            object.put("country", new EncryptionHelper().harden(accountBean.getCountry()));
            object.put("currency", new EncryptionHelper().harden(accountBean.getCurrency()));
            object.put("email", new EncryptionHelper().harden(accountBean.getEmail()));
            object.put("firstname", new EncryptionHelper().harden(accountBean.getFirstname()));
            object.put("lastname", new EncryptionHelper().harden(accountBean.getLastname()));
            object.put("merchantid", accountBean.getMerchantid());
            object.put("narration", new EncryptionHelper().harden(accountBean.getNarration()));
            object.put("passcode", new EncryptionHelper().harden(accountBean.getPasscode()));
            object.put("transactionreference", new EncryptionHelper().harden(accountBean.getTransactionreference()));
            object.put("validateoption", new EncryptionHelper().harden(accountBean.getValidateoption()));
            StringEntity input = new StringEntity(object.toString());
            input.setContentType("application/json");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());
            String result = new ResponseFormatter().formatJSON(response);
            return result;

        } catch (UnsupportedEncodingException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            java.util.logging.Logger.getLogger(ServiceHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return StandardRestResponse.sendErrorMessage("An error occurred while fetching request");

    }

    public String validatePay(ValidateBean validateBean) {

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            HttpPost post = new HttpPost((Util.middlewareURL() + "pwc/rest/account/pay/validate"));
            post.addHeader("Authorization", Util.getAPIKEY());

            JSONObject object = new JSONObject();
            object.put("transactionreference", new EncryptionHelper().harden(validateBean.getTransactionreference()));
            object.put("validateparameter", new EncryptionHelper().harden(validateBean.getValidateparameter()));
            object.put("validateparametervalue", new EncryptionHelper().harden(validateBean.getValidateparametervalue()));
            object.put("merchantid", validateBean.getMerchantid());
            StringEntity input = new StringEntity(object.toString());
            input.setContentType("application/json");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());
            String result = new ResponseFormatter().formatJSON(response);
            return result;

        } catch (UnsupportedEncodingException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            java.util.logging.Logger.getLogger(ServiceHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return StandardRestResponse.sendErrorMessage("An error occurred while fetching request");

    }

    public String resolveCustomerAccount(AccountBean accountBean) {

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            String payload = Util.getPrivateKEY() + accountBean.getAccountnumber() + accountBean.getBankcode();
            HttpPost post = new HttpPost((Util.middlewareURL() + "moneywv/api/v1/resolveaccount"));
            post.addHeader("Authorization", Util.SHA512(payload).toUpperCase());
            post.addHeader("content-type", "application/json");

            JSONObject object = new JSONObject();
            object.put("accountnumber", accountBean.getAccountnumber());
            object.put("bankcode", accountBean.getBankcode());
            StringEntity input = new StringEntity(object.toString());
            input.setContentType("application/json");
            post.setEntity(input);

            HttpResponse response = client.execute(post);
            String formattedResponse = new ResponseFormatter().formatJSON(response);
            LOG.info(formattedResponse);
            return formattedResponse;

        } catch (UnsupportedEncodingException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        }
        return null;
        //return Response.status(Response.Status.BAD_REQUEST).entity(StandardRestResponse.sendErrorMessage("An error occurred while fetching request")).build();

    }

    public String doPayment(SingleDisburseBean singleDisburseBean) {

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            String payload = Util.getPrivateKEY() + singleDisburseBean.getAccountNumber() + singleDisburseBean.getAmount() + singleDisburseBean.getRef();

            HttpPost post = new HttpPost((Util.middlewareURL() + "moneywv/api/v1/singledisburse"));
            post.addHeader("Authorization", Util.SHA512(payload).toUpperCase());
            post.addHeader("content-type", "application/json");
            Gson gson = new Gson();
            StringEntity input = new StringEntity(gson.toJson(singleDisburseBean));
            input.setContentType("application/json");
            post.setEntity(input);

            HttpResponse response = client.execute(post);
            String formattedResponse = new ResponseFormatter().formatJSON(response);
            LOG.info(formattedResponse);
            return formattedResponse;

        } catch (UnsupportedEncodingException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()));
        }
        return null;
        //return Response.status(Response.Status.BAD_REQUEST).entity(StandardRestResponse.sendErrorMessage("An error occurred while fetching request")).build();

    }

    public static String testCollection(AccountBean accountBean) {

        accountBean.setCountry("NG");
        accountBean.setCurrency("NGN");
        accountBean.setEmail("gbonjubee@gmail.com");
        accountBean.setMerchantid("tk_YQLScZ0jWv");
        accountBean.setNarration("This is a transfer from " + accountBean.getFirstname() + accountBean.getLastname());
        accountBean.setTransactionreference(Util.randomString(10));
        accountBean.setPasscode("");
        accountBean.setValidateoption("SMS");

        System.out.println(accountBean.toString());
        String accountpayResp = new ServiceHelper().accountPay(accountBean);
        System.out.println("Collection: " + accountpayResp);

        return accountpayResp;

    }

    public static String testPay(SingleDisburseBean singleDisburseBean) {

        singleDisburseBean.setCurrency("NGN");
        singleDisburseBean.setNarration("This is a transfer from " + singleDisburseBean.getSenderName());
        singleDisburseBean.setRef(Util.randomString(10));
        System.out.println(singleDisburseBean.toString());
        String accountpayResp = new ServiceHelper().doPayment(singleDisburseBean);
        System.out.println("Pay: " + accountpayResp);

        return accountpayResp;

    }

    public static String validateCollection(ValidateBean validateBean) {
        String response = new ServiceHelper().validatePay(validateBean);
        return response;
    }

    public static String resolveAccount(AccountBean accountBean) {

        LOG.info(accountBean.toString());
        String name = "";
        ServiceHelper service = new ServiceHelper();
        String result = service.resolveCustomerAccount(accountBean);
        JSONObject jsonResponse = new JSONObject(result);
        JSONObject data;

        if (jsonResponse.has("status") && jsonResponse.getString("status").equals("success")) {
            data = jsonResponse.getJSONObject("data");
            if (data != null) {
                name = data.getString("account_name");
            }
            LOG.info("Response: " + result);
            if (result == null) {
                return StandardRestResponse.sendErrorMessage(Util.ERROR_RESPONSE_MSG);
            }

        }
        return name;
    }
}
