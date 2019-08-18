package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GeenericDAO<Users> {


	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	public Users create(Users user) {
		return super.create(user);
	}
	@Override
	public Users update(Users t) {
		
		return super.update(t);
	}

	@Override
	public Users get(Object userId) {
	
		return super.find(Users.class,userId);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public void delete(Object userId) {
		super.delete(Users.class, userId);
		
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Users.countAll");
		
	}
	public Users findByEmail(String email) {
		List<Users>listResult=super.findWithNamedQuery("Users.findByEmail","email",email);
		if(listResult!=null &&listResult.size()>0) {
			return listResult.get(0);
		}
		return null;
	}

}
