package com.zee.zee5app.service;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.UserRepository;

public class SubscriptionService {
	
	private SubscriptionRepository repository = SubscriptionRepository.getInstance();
	
	private SubscriptionService() {
		// TODO Auto-generated constructor stub
	}
	
	private static SubscriptionService  service = null;
	
	public static SubscriptionService getInstance() {
		if(service==null)
			service = new SubscriptionService();
		return service;
	}
	
	public String addSubscription(Subscriptions subscription) {
		return this.repository.addSubscription(subscription);
	}
	
	public Subscriptions getSubscriptionById(String id) {
		return this.repository.getSubscriptionById(id);
	}
	
	public Subscriptions[] getSubscriptions() {
		return repository.getSubscriptions();
	}
	public String modifySubscriptionById(String id, String dateOfPurchase, String status, String Country,
			String paymentMode, boolean autoRenewal, String expiryDate) {
		return repository.modifySubscriptionById(id, dateOfPurchase, status, Country, paymentMode, autoRenewal, expiryDate);
	}
	
	public String deleteSubscriptionById(String id) {
		return repository.deleteSubscriptionById(id);
	}
}
