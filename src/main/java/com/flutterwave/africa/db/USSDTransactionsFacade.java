/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flutterwave.africa.db;

import com.flutterwave.africa.bean.AccountBean;
import com.flutterwave.africa.util.Util;
import java.util.Arrays;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author gbonjubolaamuda
 */
@Stateless
public class USSDTransactionsFacade extends AbstractFacade<USSDTransactions> {

    private static final Logger LOG = Logger.getLogger(USSDTransactionsFacade.class.getName());
    @PersistenceContext(unitName = "AfricaUSSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public USSDTransactionsFacade() {
        super(USSDTransactions.class);
    }

    public String insertTransaction(AccountBean accountBean, String sessionId) {
        USSDTransactions ussdTransactions = new USSDTransactions();

        USSDTransactions exist = null;
        try {
            TypedQuery<USSDTransactions> query = em.createNamedQuery("USSDTransactions.findBySessionId", USSDTransactions.class);
            try {
                exist = (USSDTransactions) query.setParameter("sessionId", sessionId).getSingleResult();
            } catch (NoResultException | NonUniqueResultException nre) {
                //nre.printStackTrace();
            }
            if (exist == null) {
                ussdTransactions.setAccountNumber(accountBean.getAccountnumber());
                ussdTransactions.setAmount(accountBean.getAmount());
                ussdTransactions.setBankCode(accountBean.getBankcode());
                ussdTransactions.setCountry(accountBean.getCountry());
                ussdTransactions.setCurrency(accountBean.getCurrency());
                ussdTransactions.setEmail(accountBean.getEmail());
                ussdTransactions.setFirstname(accountBean.getFirstname());
                ussdTransactions.setLastname(accountBean.getLastname());
                ussdTransactions.setNarration(accountBean.getNarration());
                ussdTransactions.setPasscode(accountBean.getPasscode());
                ussdTransactions.setTransactionreference(accountBean.getTransactionreference());
                ussdTransactions.setRequestDate(new Date());
                ussdTransactions.setSessionId(sessionId);
                em.persist(ussdTransactions);
                return Util.SUCCESS_CODE;
            } else {

                exist.setResponseCode(accountBean.getResponseCode());
                exist.setResponseDescription(accountBean.getResponseCode());
                exist.setResponseDate(new Date());
                em.merge(exist);
            }
        } catch (Exception ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()), ex);
        }
        return Util.ERROR_CODE;
    }

    public USSDTransactions selectTransaction(String sessionId) {

        USSDTransactions exist = null;

        TypedQuery<USSDTransactions> query = em.createNamedQuery("USSDTransactions.findBySessionId", USSDTransactions.class);
        try {
            exist = (USSDTransactions) query.setParameter("sessionId", sessionId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException nre) {
            //nre.printStackTrace();
        }
        return exist;
    }

    public USSDTransactions updateTransaction(AccountBean accountBean, String sessionId) {

        USSDTransactions exist = null;

        TypedQuery<USSDTransactions> query = em.createNamedQuery("USSDTransactions.findBySessionId", USSDTransactions.class);
        try {
            exist = (USSDTransactions) query.setParameter("sessionId", sessionId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException nre) {
            //nre.printStackTrace();
        }
        if (exist != null) {
            exist.setResponseCode(accountBean.getResponseCode());
            exist.setResponseDescription(accountBean.getResponseDescription());
            exist.setRequestDate(new Date());
            em.merge(exist);
        }
        return exist;
    }

}
