package com.Branko.BrankoDemo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Branko.BrankoDemo.body.SubsetView;
import com.Branko.BrankoDemo.model.Metar;
import com.Branko.BrankoDemo.service.DecoderService;

@RestController
public class DecoderController {
	
	@Autowired  
	DecoderService decoderService;  
		
		//Saves json input and decodes metar data to decodedmetar table 
		@PostMapping("/airport/{icaoCode}/METAR/decode")  
		private void saveDecoded(@PathVariable("icaoCode") String icaoCode, @RequestBody Metar metar) {  
			decoderService.saveDecoded(icaoCode, metar);
		}
		
		//Gets view that shows wind strength and temperature
		@GetMapping("/airport/{icaoCode}/METAR/subsetView")  
		private List<SubsetView> getAllSsubscribed(@PathVariable("icaoCode") String icaoCode) {  
			return decoderService.getSubset(icaoCode);
		} 
}
