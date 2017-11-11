/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flutterwave.africa.bean;

/**
 *
 * @author gbonjubolaamuda
 */
public class ValidateBean {

public ValidateBean(){ }

private String merchantid;
private String validateparametervalue;
private String validateparameter;
private String transactionreference;

    /**
     * @return the merchantid
     */
    public String getMerchantid() {
        return merchantid;
    }

    /**
     * @param merchantid the merchantid to set
     */
    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    /**
     * @return the validateparametervalue
     */
    public String getValidateparametervalue() {
        return validateparametervalue;
    }

    /**
     * @param validateparametervalue the validateparametervalue to set
     */
    public void setValidateparametervalue(String validateparametervalue) {
        this.validateparametervalue = validateparametervalue;
    }

    /**
     * @return the validateparameter
     */
    public String getValidateparameter() {
        return validateparameter;
    }

    /**
     * @param validateparameter the validateparameter to set
     */
    public void setValidateparameter(String validateparameter) {
        this.validateparameter = validateparameter;
    }

    /**
     * @return the transactionreference
     */
    public String getTransactionreference() {
        return transactionreference;
    }

    /**
     * @param transactionreference the transactionreference to set
     */
    public void setTransactionreference(String transactionreference) {
        this.transactionreference = transactionreference;
    }



}
