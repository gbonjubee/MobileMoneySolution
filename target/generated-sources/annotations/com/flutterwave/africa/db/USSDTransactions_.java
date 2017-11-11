package com.flutterwave.africa.db;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(USSDTransactions.class)
public abstract class USSDTransactions_ {

	public static volatile SingularAttribute<USSDTransactions, String> bankCode;
	public static volatile SingularAttribute<USSDTransactions, String> country;
	public static volatile SingularAttribute<USSDTransactions, String> amount;
	public static volatile SingularAttribute<USSDTransactions, String> firstname;
	public static volatile SingularAttribute<USSDTransactions, String> sessionId;
	public static volatile SingularAttribute<USSDTransactions, String> accountNumber;
	public static volatile SingularAttribute<USSDTransactions, Integer> tid;
	public static volatile SingularAttribute<USSDTransactions, Date> responseDate;
	public static volatile SingularAttribute<USSDTransactions, String> lastname;
	public static volatile SingularAttribute<USSDTransactions, String> responseCode;
	public static volatile SingularAttribute<USSDTransactions, String> responseDescription;
	public static volatile SingularAttribute<USSDTransactions, String> narration;
	public static volatile SingularAttribute<USSDTransactions, Date> requestDate;
	public static volatile SingularAttribute<USSDTransactions, String> currency;
	public static volatile SingularAttribute<USSDTransactions, String> transactionreference;
	public static volatile SingularAttribute<USSDTransactions, String> passcode;
	public static volatile SingularAttribute<USSDTransactions, String> email;

}

