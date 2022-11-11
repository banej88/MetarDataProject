package com.Branko.BrankoDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Branko.BrankoDemo.dao.MetarRepository;
import com.Branko.BrankoDemo.model.Metar;

@Repository
public class MetarService {
	
	@Autowired
	MetarRepository metarRepository;
	
	//Saves new METAR data into metar table
	public void save(String icaoCode,Metar metar) {
		metar.setIcaoCode(icaoCode);
		metarRepository.save(metar);
	}
	
	//Retrieves latest METAR data entry from metar table by icaoCode
	public List<Metar> getmetarById(String icaoCode) {	
		return metarRepository.findFirstByIcaoCodeOrderByIdDesc(icaoCode);
	}	
}