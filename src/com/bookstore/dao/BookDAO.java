package com.bookstore.dao;

import java.lang.annotation.Retention;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GeenericDAO<Book> {

	public BookDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdatedOn(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdatedOn(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object bookId) {
		return super.find(Book.class, bookId);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}
    public  Book findByTitle(String title) {
    	List<Book> result= super.findWithNamedQuery("Book.findByTitle","title",title);
    	if(!result.isEmpty()) {
    		return result.get(0);
    	}
    	return null;
    }
	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);
		
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}
	public List<Book> listByCategory(Integer categoryId){
		return super.findWithNamedQuery("Book.findByCategory","catId",categoryId);
	}
	public List<Book> listNewBooks(){
		Query query=entityManager.createNamedQuery("Book.listNew");
		query.setFirstResult(0);
		query.setMaxResults(4);
		return query.getResultList();
		
	}

}
