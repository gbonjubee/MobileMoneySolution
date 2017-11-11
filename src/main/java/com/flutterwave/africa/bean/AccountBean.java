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
public class AccountBean {

    public AccountBean() {
    }

    private String merchantid;
    private String validateoption;//(Optional: Encrypted Validate Option)", //Can be SMS, VOICE, HWTOKEN or USSD
    private String country;//": "(Optional: Encrypted Country)",
    private String narration;//":"(Encrypted Narration)",
    private String accountnumber;//":"(Encrypted Account number to debit)",
    private String bankcode;//":"(Encrypted Bank Code)",
    private String passcode;//":"(Optional: Encrypted Passcode)", //mapped to account number and MUST be the same on subsequent calls for a specific account number
    private String amount;//":"(Encrypted Amount)",        
    private String currency;//":"(Encrypted Currency)",        
    private String firstname;//":"(Encrypted First Name)",
    private String lastname;//":"(Encrypted Last Name)",
    private String email;//":"(Encrypted Email)",
    private String transactionreference;//":"(Encrypted Transaction Reference)" //must be unique for every API call 
    private String responseCode;
    private String responseDescription;

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
     * @return the validateoption
     */
    public String getValidateoption() {
        return validateoption;
    }

    /**
     * @param validateoption the validateoption to set
     */
    public void setValidateoption(String validateoption) {
        this.validateoption = validateoption;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the narration
     */
    public String getNarration() {
        return narration;
    }

    /**
     * @param narration the narration to set
     */
    public void setNarration(String narration) {
        this.narration = narration;
    }

    /**
     * @return the accountnumber
     */
    public String getAccountnumber() {
        return accountnumber;
    }

    /**
     * @param accountnumber the accountnumber to set
     */
    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    /**
     * @return the bankcode
     */
    public String getBankcode() {
        return bankcode;
    }

    /**
     * @param bankcode the bankcode to set
     */
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    /**
     * @return the passcode
     */
    public String getPasscode() {
        return passcode;
    }

    /**
     * @param passcode the passcode to set
     */
    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the responseDescription
     */
    public String getResponseDescription() {
        return responseDescription;
    }

    /**
     * @param responseDescription the responseDescription to set
     */
    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    @Override
    public String toString() {
        return "AccountBean{" + "merchantid=" + merchantid + ", validateoption=" + validateoption + ", country=" + country + ", narration=" + narration + ", accountnumber=" + accountnumber + ", bankcode=" + bankcode + ", passcode=" + passcode + ", amount=" + amount + ", currency=" + currency + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", transactionreference=" + transactionreference + ", responseCode=" + responseCode + ", responseDescription=" + responseDescription + '}';
    }
    
    

}
