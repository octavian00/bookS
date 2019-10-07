package com.bookstore.dao;

import java.util.List;


import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GeenericDAO<Category> {

	public CategoryDAO() {
	
	}

	@Override
	public Category create(Category category) {
		return super.create(category);
	}

	@Override
	public Category update(Category t) {
		return super.update(t);
	}

	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
		
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}
	public Category findByName(String name) {
		List<Category> categoryList=super.findWithNamedQuery("Category.findByName","name",name);
		if(categoryList!=null && categoryList.size()>0) {
			return categoryList.get(0);
		}
		return null;
	}

}
