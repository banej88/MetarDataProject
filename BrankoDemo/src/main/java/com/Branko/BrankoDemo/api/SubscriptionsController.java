package com.Branko.BrankoDemo.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Branko.BrankoDemo.body.SubscribedView;
import com.Branko.BrankoDemo.model.Subscriptions;
import com.Branko.BrankoDemo.service.SubscriptionsService;


@RestController  
public class SubscriptionsController{  

	@Autowired  
	SubscriptionsService subscriptionsService;  
	
	//Shows all icaoCode data from subscription table where subscribed field has value of 1
	@GetMapping("/subscriptions")  
	private List<SubscribedView> getAllSsubscribed() {  
		List<SubscribedView> lista = new ArrayList<>();
		for(Subscriptions subscriptions : subscriptionsService.getBySubscribed()) {
			SubscribedView sv = new SubscribedView();
			sv.setIcaoCode(subscriptions.getIcaoCode());
			lista.add(sv);
		}
		return lista; 
	} 
	
	//Shows icaoCode data by icaoCode
	@GetMapping("/subscriptions/{icaoCode}")  
	private Subscriptions getIcaoCode(@PathVariable("icaoCode") String icaoCode) {  
		return subscriptionsService.getIcaoCodeByIcaoCode(icaoCode);
	} 
	
	//Updates subscription table subscribed field from 0 to 1 by icaoCode
	@PostMapping("/subscriptions")  
	private void saveSubscription(@RequestBody Subscriptions subscriptions) {  
		subscriptionsService.save(subscriptions);   
	}
	
	//Changes in subscription table subscribed field from 1 to 0 by icaoCode
	@DeleteMapping("/subscriptions/{icaoCode}")  
	private void deleteStudent(@PathVariable("icaoCode") String icaoCode) {  
		subscriptionsService.deleteSubscription(icaoCode);
	}

}  