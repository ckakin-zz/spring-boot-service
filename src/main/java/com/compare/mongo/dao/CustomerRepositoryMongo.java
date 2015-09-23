package com.compare.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compare.mongo.domain.Customer;

public interface CustomerRepositoryMongo extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);

}
