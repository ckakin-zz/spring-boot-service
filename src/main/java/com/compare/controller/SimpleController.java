package com.compare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compare.jdbc.domain.Customer;
import com.compare.jpa.dao.CustomerRepository;

@RestController
public class SimpleController {
	private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	CustomerRepository repository;

	@RequestMapping("/")
	public String home() {
		log.debug("home");
		return "Hello Docker World v3";
	}

	@RequestMapping("customers")
	public List<Object> listCustomers() {
		log.info("Querying for customer records where first_name = 'Josh':");

		List<Object> customers = jdbcTemplate.query(
				"SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")));

		customers.forEach(customer -> log.info(customer.toString()));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (com.compare.jpa.domain.Customer customer : repository.findAll()) {
			log.info(customer.toString());
		}

		return customers;
	}
}
