/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flutterwave.africa.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gbonjubolaamuda
 */
@Entity
@Table(name = "USSD_Transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "USSDTransactions.findAll", query = "SELECT u FROM USSDTransactions u")
    , @NamedQuery(name = "USSDTransactions.findByTid", query = "SELECT u FROM USSDTransactions u WHERE u.tid = :tid")
    , @NamedQuery(name = "USSDTransactions.findByAccountNumber", query = "SELECT u FROM USSDTransactions u WHERE u.accountNumber = :accountNumber")
    , @NamedQuery(name = "USSDTransactions.findByAmount", query = "SELECT u FROM USSDTransactions u WHERE u.amount = :amount")
    , @NamedQuery(name = "USSDTransactions.findByBankCode", query = "SELECT u FROM USSDTransactions u WHERE u.bankCode = :bankCode")
    , @NamedQuery(name = "USSDTransactions.findByCountry", query = "SELECT u FROM USSDTransactions u WHERE u.country = :country")
    , @NamedQuery(name = "USSDTransactions.findByNarration", query = "SELECT u FROM USSDTransactions u WHERE u.narration = :narration")
    , @NamedQuery(name = "USSDTransactions.findByPasscode", query = "SELECT u FROM USSDTransactions u WHERE u.passcode = :passcode")
    , @NamedQuery(name = "USSDTransactions.findByCurrency", query = "SELECT u FROM USSDTransactions u WHERE u.currency = :currency")
    , @NamedQuery(name = "USSDTransactions.findByFirstname", query = "SELECT u FROM USSDTransactions u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "USSDTransactions.findByLastname", query = "SELECT u FROM USSDTransactions u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "USSDTransactions.findByEmail", query = "SELECT u FROM USSDTransactions u WHERE u.email = :email")
    , @NamedQuery(name = "USSDTransactions.findByTransactionreference", query = "SELECT u FROM USSDTransactions u WHERE u.transactionreference = :transactionreference")
    , @NamedQuery(name = "USSDTransactions.findByResponseCode", query = "SELECT u FROM USSDTransactions u WHERE u.responseCode = :responseCode")
    , @NamedQuery(name = "USSDTransactions.findByResponseDescription", query = "SELECT u FROM USSDTransactions u WHERE u.responseDescription = :responseDescription")
    , @NamedQuery(name = "USSDTransactions.findByRequestDate", query = "SELECT u FROM USSDTransactions u WHERE u.requestDate = :requestDate")
    , @NamedQuery(name = "USSDTransactions.findByResponseDate", query = "SELECT u FROM USSDTransactions u WHERE u.responseDate = :responseDate")
    , @NamedQuery(name = "USSDTransactions.findBySessionId", query = "SELECT u FROM USSDTransactions u WHERE u.sessionId = :sessionId")})
public class USSDTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Integer tid;
    @Size(max = 45)
    @Column(name = "account_number")
    private String accountNumber;
    @Size(max = 45)
    @Column(name = "amount")
    private String amount;
    @Size(max = 5)
    @Column(name = "bank_code")
    private String bankCode;
    @Size(max = 5)
    @Column(name = "country")
    private String country;
    @Size(max = 45)
    @Column(name = "narration")
    private String narration;
    @Size(max = 45)
    @Column(name = "passcode")
    private String passcode;
    @Size(max = 5)
    @Column(name = "currency")
    private String currency;
    @Size(max = 50)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "transactionreference")
    private String transactionreference;
    @Size(max = 5)
    @Column(name = "response_code")
    private String responseCode;
    @Size(max = 50)
    @Column(name = "response_description")
    private String responseDescription;
    @Column(name = "request_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Column(name = "response_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    @Size(max = 45)
    @Column(name = "session_id")
    private String sessionId;

public USSDTransactions(){ }

    public USSDTransactions(Integer tid) {
        this.tid = tid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransactionreference() {
        return transactionreference;
    }

    public void setTransactionreference(String transactionreference) {
        this.transactionreference = transactionreference;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tid != null ? tid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof USSDTransactions)) {
            return false;
        }
        USSDTransactions other = (USSDTransactions) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flutterwave.africa.db.USSDTransactions[ tid=" + tid + " ]";
    }

}
