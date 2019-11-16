package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GeenericDAO<Customer> {

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterOn(new Date());
		return super.create(customer);
	}

	@Override
	public Customer get(Object id) {
		return super.find(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
		
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}

	@Override
	public Customer update(Customer customer) {
		customer.setRegisterOn(new Date());
		return super.update(customer);
	}
	public Customer findByEmail(String email) {
		List <Customer> listCustomers=super.findWithNamedQuery("Customer.findByEmail", "email", email);
		if(!listCustomers.isEmpty()) {
			return listCustomers.get(0);
		}
		return null;
	} 
	public Customer checkLogin(String email,String password) {
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("email",email);
		parameters.put("pass",password);
		List<Customer> listCustomers=super.findWithNamedQuery("Customer.checkLogin",parameters);
		if(!listCustomers.isEmpty()) {
			return listCustomers.get(0);
		}
		return null;
	}
}
