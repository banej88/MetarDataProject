package com.Branko.BrankoDemo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Branko.BrankoDemo.model.Metar;
import com.Branko.BrankoDemo.service.MetarService;

@RestController  
public class MetarController{  

	@Autowired  
	MetarService metarService;  
	
	//Returns all METAR data
	@GetMapping("/airport/Metar")  
	private List<Metar> getAllMetar() {  
		return metarService.getAllMetar();
	}
	
	//Adds new METAR data from file path as new row
	//No check to see if icao exists
	@PostMapping("/airport/{icaoCode}/METAR")  
	private void saveMetar(@PathVariable("icaoCode") String icaoCode, @RequestBody Metar metar) {  
		metarService.save(icaoCode, metar);
	}
	
	//Shows last METAR data inserted for given icao
	@GetMapping("/airport/{icaoCode}/METAR")  
	private List<Metar> getAllMetar(@PathVariable("icaoCode") String icaoCode){  
		return metarService.getmetarById(icaoCode);
	}

}  