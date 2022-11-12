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
	
		//Shows all icaoCode data from subscription table where subscribed field has value of 1
		public List<Subscriptions>  getBySubscribed() {
			return subscriptionsRepository.findBySubscribed(true);
		}
		
		//Shows icaoCode data by icaoCode
		public Subscriptions getIcaoCodeByIcaoCode(String icaoCode) {
			return subscriptionsRepository.findByicaoCode(icaoCode);
		}
		
		//Updates subscription table subscribed field from 0 to 1 by icaoCode
		public void save(Subscriptions subscriptions) {
			subscriptions = subscriptionsRepository.findByicaoCode(subscriptions.getIcaoCode());
			subscriptions.setSubscribed(true);
			subscriptionsRepository.save(subscriptions);
		}

		//Changes in subscription table subscribed field from 1 to 0 by icaoCode
		public void deleteSubscription(String icaoCode) {
			Subscriptions subscriptions = subscriptionsRepository.findByicaoCode(icaoCode);
			subscriptions.setSubscribed(false);
			subscriptionsRepository.save(subscriptions);
		}
		
		//Updates subscription table active field by icaoCode and by inserted value ( 0 or 1 )
		public void saveActive(String icaoCode,Subscriptions subscriptions) {
			int active = subscriptions.getActive();
			subscriptions = subscriptionsRepository.findByicaoCode(icaoCode);
			subscriptions.setActive(active);
			subscriptionsRepository.save(subscriptions);
		}
		
		//Search by active status for airports
		public List<Subscriptions> getByActive(int active){
			return subscriptionsRepository.findByactive(active); 
		}
		
		//Search by partial airport string
		public List<Subscriptions> getByPartial(String icaoCode){
			return subscriptionsRepository.findByicaoCodeContaining(icaoCode); 
		}
}