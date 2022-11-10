package com.Branko.BrankoDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Branko.BrankoDemo.dao.MetarRepository;
import com.Branko.BrankoDemo.dao.SubscriptionsRepository;
import com.Branko.BrankoDemo.model.Metar;

@Repository
public class MetarService {
	
	@Autowired
	MetarRepository metarRepository;
	@Autowired
	SubscriptionsRepository subscriptionsRepository;

	public List<Metar> getAllMetar() {
		List<Metar> metar = new ArrayList<Metar>();
		metar = (List<Metar>) metarRepository.findAll();
		return metar;
	}
	
	public void save(String icaoCode,Metar metar) {
		metar.setIcaoCode(icaoCode);
		metarRepository.save(metar);
	}
	
	public List<Metar> getmetarById(String icaoCode) {	
		return metarRepository.findFirstByIcaoCodeOrderByIdDesc(icaoCode);
	}	
}