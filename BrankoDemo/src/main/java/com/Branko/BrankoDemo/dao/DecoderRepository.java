package com.Branko.BrankoDemo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Branko.BrankoDemo.model.DataDecoderObject;

public interface DecoderRepository extends CrudRepository<DataDecoderObject, String> {
	List<DataDecoderObject> findFirstByIcaoCodeOrderByIdDesc(String icaoCode);
}
