package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Subscriptions {
	private String dateOfPurchase;
	private String status;
	private String Country;
	private String paymentMode;
	private boolean autoRenewal;
	private String expiryDate;
	private String id;
	
}
