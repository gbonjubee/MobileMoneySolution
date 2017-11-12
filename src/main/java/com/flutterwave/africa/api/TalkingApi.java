/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flutterwave.africa.api;

import com.flutterwave.africa.bean.RequestBean;
import com.flutterwave.africa.bean.AccountBean;
import com.flutterwave.africa.bean.SingleDisburseBean;
import com.flutterwave.africa.bean.ValidateBean;
import com.flutterwave.africa.db.USSDTransactions;
import com.flutterwave.africa.db.USSDTransactionsFacade;
import com.flutterwave.africa.service.ServiceHelper;
import com.flutterwave.africa.util.Util;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author gbonjubolaamuda
 */
@Path("v1")
public class TalkingApi {

    @EJB
    USSDTransactionsFacade ussdTransactionsFacade;

    private static final Logger LOG = Logger.getLogger(TalkingApi.class.getName());

    public TalkingApi() {
    }

    @POST
    @Path("receiver")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String getRequests(@FormParam("sessionId") String sessionId,
            @FormParam("serviceCode") String serviceCode,
            @FormParam("phoneNumber") String phoneNumber,
            @FormParam("text") String text) {

        RequestBean requestBean = new RequestBean();
        requestBean.setPhoneNumber(phoneNumber);
        requestBean.setServiceCode(serviceCode);
        requestBean.setSessionId(sessionId);
        requestBean.setText(text);
        System.out.println("ussdTransactionsFacade:: " + ussdTransactionsFacade);
        System.out.println("Text:  " + text);
        System.out.println("ServiceCode:  " + serviceCode);
        System.out.println("PhoneNumber:  " + phoneNumber);
        System.out.println("sessionId:  " + sessionId);
        String[] inputText = text.split("\\*");
        System.out.println("Length " + inputText.length);
        String response = "END An error occured";

        if (requestBean.getText() == null || requestBean.getText().isEmpty()) {
            response = "CON What would you want to check \n";
            response = response + "1. Account Enquiry \n";
            response = response + "2. Collection \n";
            response = response + "3. Transfer Fund \n";
            response = response + "4. Exit \n";
            return response;
        }

        if (requestBean.getText().equals("1")) {
            response = "CON Enter account number \n";
            return response;
        }

        if (requestBean.getText().startsWith("1") && inputText.length == 2) {

            AccountBean accountBean = new AccountBean();
            accountBean.setBankcode(Util.getBankCode());
            accountBean.setAccountnumber(inputText[1]);

            String accountdetail = ServiceHelper.resolveAccount(accountBean);

            if (accountdetail != null && !accountdetail.isEmpty()) {
                response = "END Account Name is " + accountdetail;
                return response;
            } else {

                response = "END Could not resolve account, please try again! ";

                return response;
            }
        }
        if (requestBean.getText().equals("2")) {
            response = "CON Enter account to debit \n";
            return response;
        }

        if (requestBean.getText().startsWith("2")) {
            AccountBean accountBean = new AccountBean();
            if (inputText.length == 2) {
                accountBean.setAccountnumber(inputText[1]);
                accountBean.setBankcode(Util.getBankCode());
                String name = ServiceHelper.resolveAccount(accountBean);
                response = "CON " + name + " \n " + "Enter amount to debit: ";
                return response;
            }

            if (inputText.length == 3) {
                try {
                    Double.parseDouble(inputText[2]);

                } catch (NumberFormatException e) {
                    LOG.error(e);
                    response = "END Invalid amount  \n";
                    return response;
                }
                accountBean.setAccountnumber(inputText[1]);
                accountBean.setAmount(inputText[2]);
                accountBean.setBankcode(Util.getBankCode());
                String name = ServiceHelper.resolveAccount(accountBean);
                if (name == null || name.isEmpty()) {
                    return "END Account cannot be resolved";
                }
                accountBean.setFirstname(name.split("\\s+")[0]);
                accountBean.setLastname(name.split("\\s+")[1]);
                String payResponse = ServiceHelper.testCollection(accountBean);

                System.out.println("payResponse: " + payResponse);
                String responsecode = new JSONObject(payResponse).getJSONObject("data").getString("responsecode");
                if (responsecode.equals("02")) {
                    String reference = new JSONObject(payResponse).getJSONObject("data").getString("transactionreference");
                    accountBean.setTransactionreference(reference);

                    asyncInsertMethod(accountBean, requestBean.getSessionId(), ussdTransactionsFacade);
                    response = "CON Enter One Time Password  \n";
                    return response;
                } else {
                    response = "END Request failed, please try again  \n";
                }
            }

            if (inputText.length == 4) {

                USSDTransactions ussdTransactions = ussdTransactionsFacade.selectTransaction(requestBean.getSessionId());
                if (ussdTransactions == null) {
                    response = "END Account OTP validation failed ";
                } else {
                    String otp = inputText[3];

                    ValidateBean otpValidation = new ValidateBean();
                    otpValidation.setMerchantid(Util.getMerchantID());
                    otpValidation.setTransactionreference(ussdTransactions.getTransactionreference());
                    otpValidation.setValidateparameter("OTP");
                    otpValidation.setValidateparametervalue(otp);

                    String validateResponse = ServiceHelper.validateCollection(otpValidation);

                    System.out.println("OTP Validation: " + validateResponse);
                    JSONObject data = new JSONObject(validateResponse).getJSONObject("data");
                    String responsecode = data.getString("responsecode");
                    String responsemessage = data.getString("responsemessage");
                    accountBean.setResponseCode(responsecode);
                    accountBean.setResponseDescription(responsemessage);
                    asyncUpdateMethod(accountBean, requestBean.getSessionId());
                    if (responsecode.equals("00")) {
                        response = "END " + responsemessage;
                    } else {
                        response = "END Transaction Failed,please try again later";
                    }
                    return response;
                }
            }
            return response;

        }

        if (requestBean.getText().equals("3")) {
            response = "CON Enter account to debit \n";
            return response;
        }

        if (requestBean.getText().startsWith("3")) {
            AccountBean accountBean = new AccountBean();

            if (inputText.length == 2) {
                accountBean.setAccountnumber(inputText[1]);
                accountBean.setBankcode(Util.getBankCode());
                String name = ServiceHelper.resolveAccount(accountBean);
                response = "CON " + name + " \n " + "Enter amount to debit: ";
                return response;
            }
            if (inputText.length == 3) {
                try {
                    Double.parseDouble(inputText[2]);

                } catch (Exception e) {
                    LOG.error(e);
                    response = "END Invalid amount  \n";
                    return response;
                }
                SingleDisburseBean singleDisburseBean = new SingleDisburseBean();
                accountBean.setAccountnumber(inputText[1]);
                accountBean.setBankcode(Util.getBankCode());
                String name = ServiceHelper.resolveAccount(accountBean);
                if (name == null || name.isEmpty()) {
                    return "END Account cannot be resolved";
                }
                singleDisburseBean.setAccountNumber(inputText[1]);
                singleDisburseBean.setAmount(inputText[2]);
                singleDisburseBean.setBankcode(Util.getBankCode());
                singleDisburseBean.setSenderName(name);
                String payResponse = ServiceHelper.testPay(singleDisburseBean);
                String responsecode = new JSONObject(payResponse).getJSONObject("data").getJSONObject("data").getString("responsecode");
                if (responsecode.equals("00")) {
                    response = "END \n" + new JSONObject(payResponse).getJSONObject("data").getJSONObject("data").getString("responsemessage");
                } else {
                    response = "END Request failed, please try again  \n";
                }
                return response;
            }
            return response;

        }

        if (requestBean.getText().startsWith("1*4")) {
            response = "END ";
            return response;
        }

        return response;
    }

    public void asyncInsertMethod(final AccountBean accountBean, final String sessionId, final USSDTransactionsFacade ussdTransactionsFacade) {
        Runnable task = new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("ussdTransactionsFacade: " + ussdTransactionsFacade);
                    ussdTransactionsFacade.insertTransaction(accountBean, sessionId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        new Thread(task, "ServiceThread").start();
    }

    public void asyncUpdateMethod(final AccountBean accountBean, final String sessionId) {
        Runnable task = new Runnable() {

            @Override
            public void run() {
                try {
                    ussdTransactionsFacade.updateTransaction(accountBean, sessionId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        new Thread(task, "ServiceThread").start();
    }

}
