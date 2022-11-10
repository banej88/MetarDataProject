package com.Branko.BrankoDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Branko.BrankoDemo.dao.SubscriptionsRepository;
import com.Branko.BrankoDemo.model.Subscriptions;

@Repository
public class SubscriptionsService {
	
		@Autowired
		SubscriptionsRepository subscriptionsRepository;
	
		public List<Subscriptions>  getBySubscribed() {
			return subscriptionsRepository.findBySubscribed(true);
		}
		
		public Subscriptions getIcaoCodeByIcaoCode(String icaoCode) {
			return subscriptionsRepository.findByicaoCode(icaoCode);
		}
		
		public void save(Subscriptions subscriptions) {
			subscriptions = subscriptionsRepository.findByicaoCode(subscriptions.getIcaoCode());
			subscriptions.setSubscribed(true);
			subscriptionsRepository.save(subscriptions);
		}
		
		public void deleteSubscription(String icaoCode) {
		Subscriptions subscriptions = subscriptionsRepository.findByicaoCode(icaoCode);
		subscriptions.setSubscribed(false);
		subscriptionsRepository.save(subscriptions);
		}
}