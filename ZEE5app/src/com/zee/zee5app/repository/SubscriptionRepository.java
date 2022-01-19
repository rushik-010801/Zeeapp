package com.zee.zee5app.repository;

import com.zee.zee5app.dto.Subscriptions;

public class SubscriptionRepository {
	private Subscriptions[] subscriptions = new Subscriptions[10];
	private int count = -1;
	
	public SubscriptionRepository() {
		// TODO Auto-generated constructor stub
	}
	
	public String addSubscription(Subscriptions subscription) {
		if(count == subscriptions.length - 1) {
			Subscriptions[] temp = new Subscriptions[subscriptions.length * 2];
			System.arraycopy(subscriptions, 0, temp, 0, subscriptions.length);
			subscriptions = temp;
		}
		subscriptions[++count] = subscription;
		return "success";
	}
	
	public Subscriptions[] getSubscriptions() {
		return subscriptions;
	}
	
	public Subscriptions getSubscriptionById(String id) {
		for(Subscriptions sub : subscriptions) {
			if(sub != null && sub.getId().equals(id)) {
				return sub;
			}
		}
		return null;
	}
	
	public String modifySubscriptionById(String id, String dateOfPurchase, String status, String Country,
							String paymentMode, boolean autoRenewal, String expiryDate) {
		for(Subscriptions sub : subscriptions) {
			if(sub != null && sub.getId().equals(id)) {
				sub.setDateOfPurchase(dateOfPurchase);
				sub.setStatus(status);
				sub.setCountry(Country);
				sub.setAutoRenewal(autoRenewal);
				sub.setExpiryDate(expiryDate);
				sub.setPaymentMode(paymentMode);
				return "Success";
			}
		}
		return "Fail";
	}
	
	public String deleteSubscriptionById(String id) {
		for(int i = 0; i < subscriptions.length; i++) {
			if(subscriptions[i] != null && subscriptions[i].getId().equals(id)) {
				for(int j = i + 1; j < subscriptions.length; i++, j++) {
					subscriptions[i] = subscriptions[j]; 
				}
				subscriptions[i] = null;
				return "Success";
			}
		}
		return "Fail";
	}
	
	private static SubscriptionRepository subrepo;
	public static SubscriptionRepository getInstance() {
		if(subrepo == null) {
			subrepo = new SubscriptionRepository();
		}
		return subrepo;
	}
	
}
