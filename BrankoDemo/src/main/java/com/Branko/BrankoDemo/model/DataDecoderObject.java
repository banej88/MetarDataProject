package com.Branko.BrankoDemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "decodedmetar")
public class DataDecoderObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="icaocode")
	private String icaoCode;
	
	@Column(name="datatimestamp")
	private String dataTimestamp; 
	
	@Column(name="windstrength")
	private String windStrength; 
	
	@Column(name="temperature")
	private String temperature;
	
	@Column(name="overallvisibility")
	private String overallVisibility;
	
	public String getIcaoCode() {
		return icaoCode;
	}
	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataTimestamp() {
		return dataTimestamp;
	}
	public void setDataTimestamp(String dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}
	public String getWindStrength() {
		return windStrength;
	}
	public void setWindStrength(String windStrength) {
		this.windStrength = windStrength;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getOverallVisibility() {
		return overallVisibility;
	}
	public void setOverallVisibility(String overallVisibility) {
		this.overallVisibility = overallVisibility;
	}
	
	@Override
	public String toString() {
		return "DataDecoderObject [id=" + id + ", dataTimestamp=" + dataTimestamp + ", windStrength=" + windStrength
				+ ", temperature=" + temperature + ", overallVisibility=" + overallVisibility + "]";
	}
}
