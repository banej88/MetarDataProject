package com.Branko.BrankoDemo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Branko.BrankoDemo.model.Subscriptions;

public interface SubscriptionsRepository extends CrudRepository<Subscriptions, String> {
		List<Subscriptions> findBySubscribed(boolean subscribed);
		Subscriptions findByicaoCode(String icaoCode);
		List<Subscriptions> findByactive(int active);
		List<Subscriptions> findByicaoCodeContaining(String icaoCode);
}
