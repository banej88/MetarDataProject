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
	
	//Shows icao code column only for all subscribed airports
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
	
	//Shows icao code details for inputed airport
	@GetMapping("/subscriptions/{icaoCode}")  
	private Subscriptions getIcaoCode(@PathVariable("icaoCode") String icaoCode) {  
		return subscriptionsService.getIcaoCodeByIcaoCode(icaoCode);
	} 
	
	//Changes value in table subscriptions from 0 to 1 to represent that subscription has been made
	@PostMapping("/subscriptions")  
	private void saveSubscription(@RequestBody Subscriptions subscriptions) {  
		subscriptionsService.save(subscriptions);   
	}
	
	//Changes value in table subscriptions from 1 to 0 to represent that user is no longer subscribed to the airport
	@DeleteMapping("/subscriptions/{icaoCode}")  
	private void deleteStudent(@PathVariable("icaoCode") String icaoCode) {  
		subscriptionsService.deleteSubscription(icaoCode);
	}

}  