package com.bookstore.entity;
// Generated Jul 11, 2019 9:52:17 PM by Hibernate Tools 5.2.12.Final

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Book generated by hbm2java
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Book.findAll",query="SELECT b FROM Book b"),
	@NamedQuery(name="Book.findByTitle",query="SELECT b FROM Book b WHERE b.title=:title"),
	@NamedQuery(name="Book.countAll",query="SELECT COUNT(*) FROM Book b"),
	@NamedQuery(name = "Book.countByCategory", query = "SELECT COUNT(b) FROM Book b "
			+ "WHERE b.category.categoryId = :catId"),
	@NamedQuery(name = "Book.findByCategory", query = "SELECT b FROM Book b JOIN "
			+ "Category c ON b.category.categoryId = c.categoryId AND c.categoryId=:catId"),
	@NamedQuery(name="Book.listNew",query="SELECT b FROM Book b ORDER BY b.publishDate DESC"),
	@NamedQuery(name="Book.search",query="SELECT b FROM Book b WHERE b.title LIKE '%' || :keyword || '%'"+
	"OR b.author LIKE '%' || :keyword || '%' OR b.desciption LIKE '%' || :keyword || '%'")
})
@Table(name = "book", catalog = "bookstoredb")
public class Book implements java.io.Serializable {

	
	private int bookId;
	private Category category;
	private String title;
	private String author;
	private String desciption;
	private String isbn;
	private byte[] image;
	private  String base64Image;
	private Float price;
	private Date publishDate;
	private Date lastUpdatedOn;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public Book() {
	}

	public Book(int bookId, Category category, String title, String author, String desciption) {
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.author = author;
		this.desciption = desciption;
	}

	public Book(int bookId, Category category, String title, String author, String desciption, String isbn,
			byte[] image, Float price, Date publishDate, Date lastUpdatedOn, Set<Review> reviews,
			Set<OrderDetail> orderDetails) {
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.author = author;
		this.desciption = desciption;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedOn = lastUpdatedOn;
		this.reviews = reviews;
		this.orderDetails = orderDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "title", nullable = false, length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "author", nullable = false, length = 45)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "desciption", nullable = false, length = 16777215)
	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	@Column(name = "isbn", length = 20)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "price", precision = 12, scale = 0)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_on", length = 19)
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@Transient
	public String getBase64Image() {
		this.base64Image= Base64.getEncoder().encodeToString(this.image);
		return this.base64Image;
	}
	@Transient
	public void setBase64Image(String base64Image) {
		this.base64Image=base64Image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId != other.bookId)
			return false;
		return true;
	}
	
}
