package com.compare.jpa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.compare.jpa.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);
}
