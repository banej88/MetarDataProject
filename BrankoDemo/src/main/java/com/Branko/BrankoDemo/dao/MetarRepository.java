package com.Branko.BrankoDemo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Branko.BrankoDemo.model.Metar;

public interface MetarRepository extends CrudRepository<Metar, String> {
	List<Metar> findFirstByIcaoCodeOrderByIdDesc(String icaoCode);
}
