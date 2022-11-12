package com.Branko.BrankoDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Branko.BrankoDemo.body.SubsetView;
import com.Branko.BrankoDemo.dao.DecoderRepository;
import com.Branko.BrankoDemo.model.DataDecoderObject;
import com.Branko.BrankoDemo.model.Metar;

@Repository
public class DecoderService {

	@Autowired
	DecoderRepository decoderRepository;
	
	public void saveDecoded(String icaoCode,Metar metar) {
		String data = metar.getData();
		decoderRepository.save(decode(data));
	}
	
	public List<SubsetView> getSubset(String icaoCode){
		List<SubsetView> lista = new ArrayList<>();
		for(DataDecoderObject dataObject : decoderRepository.findFirstByIcaoCodeOrderByIdDesc(icaoCode)) {
			SubsetView sv = new SubsetView();
			sv.setTemperature(dataObject.getTemperature());
			sv.setWindStrength(dataObject.getWindStrength());
			lista.add(sv);
		}
		return lista; 
	}

	public DataDecoderObject decode(String data) {
		DataDecoderObject ddo = new DataDecoderObject();
		int counterVisibilityQueue = 0;
		int counterIcaoQueue = 0;
		
		for(String x : data.split(" ")) {	
			if(x.length()==4) {	
				if(counterIcaoQueue==0) {
					char[] array = x.toCharArray();
					int counterLen = 0;
					String icaoCode = "";
				
					for(char c : array) {
						if(Character.isAlphabetic(c)) {
							counterLen++;
						}
					}
					if(counterLen==4) {
						icaoCode = x;
						counterIcaoQueue++;
					}
					ddo.setIcaoCode(icaoCode);
				}
			}
			if(x.substring(x.length()-1, x.length()).equals("Z") && x.length()==7) {	
				String month = x.substring(0,2);
				String hours = x.substring(2,4);
				String minutes = x.substring(4,6);
				ddo.setDataTimestamp(month+" "+hours+":"+minutes+" UTC");
			}
			if(x.contains("/") && x.length()==5) {
				String temp = x.substring(0,2);
				ddo.setTemperature(temp);
			}
			if(x.contains("MPS") || x.contains("KT")) {
				String windStrength = "";
				if(x.contains("MPS")) {
					windStrength = x.substring(x.length()-5,x.length()-3)+ " MPS";
				}
				else {
					windStrength = x.substring(x.length()-4,x.length()-2)+" KT";
				}
				ddo.setWindStrength(windStrength);
			}
			if(x.length()==4){
				if(counterVisibilityQueue==0) {
					char[] array = x.toCharArray();
					int counterLen = 0;
					String visibility = "";
				
					for(char c : array) {
						if(Character.isDigit(c)) {
							counterLen++;
						}
					}
					if(counterLen==4) {
						visibility = x+" meters";
						counterVisibilityQueue++;
					}
					ddo.setOverallVisibility(visibility);
				}
			}
		}
		return ddo;
	}
}
