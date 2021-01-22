package com.db.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private int granterBookId;
	private String granterBookTitle;
	private String granterBookAuthor;
	private String granterBookGenre;
	private String granterBookPublisher;
	private float granterBookCost;
	private int granterUserId;
	private String granterUserName;
	private long granterUserMobileNo;
	private String granterUserEmailId;
	private String granterUserCity;
	private int granterUserPincode;
	
	private int requesterBookId;
	private String requesterBookTitle;
	private String requesterBookAuthor;
	private String requesterBookGenre;
	private String requesterBookPublisher;
	private float requesterBookCost;
	private int requesterUserId;
	private String requesterUserName;
	private long requesterUserMobileNo;
	private String requesterUserEmailId;
	private String requesterUserCity;
	private int requesterUserPincode;
	
	
	
	
	public Order() {
		super();
	}
	public Order(int granterBookId, String granterBookTitle, String granterBookAuthor, String granterBookGenre,
			String granterBookPublisher, float granterBookCost, int granterUserId, String granterUserName,
			long granterUserMobileNo, String granterUserEmailId, String granterUserCity, int granterUserPincode,
			int requesterBookId, String requesterBookTitle, String requesterBookAuthor, String requesterBookGenre,
			String requesterBookPublisher, float requesterBookCost, int requesterUserId, String requesterUserName,
			long requesterUserMobileNo, String requesterUserEmailId, String requesterUserCity,
			int requesterUserPincode) {
		super();
		this.granterBookId = granterBookId;
		this.granterBookTitle = granterBookTitle;
		this.granterBookAuthor = granterBookAuthor;
		this.granterBookGenre = granterBookGenre;
		this.granterBookPublisher = granterBookPublisher;
		this.granterBookCost = granterBookCost;
		this.granterUserId = granterUserId;
		this.granterUserName = granterUserName;
		this.granterUserMobileNo = granterUserMobileNo;
		this.granterUserEmailId = granterUserEmailId;
		this.granterUserCity = granterUserCity;
		this.granterUserPincode = granterUserPincode;
		this.requesterBookId = requesterBookId;
		this.requesterBookTitle = requesterBookTitle;
		this.requesterBookAuthor = requesterBookAuthor;
		this.requesterBookGenre = requesterBookGenre;
		this.requesterBookPublisher = requesterBookPublisher;
		this.requesterBookCost = requesterBookCost;
		this.requesterUserId = requesterUserId;
		this.requesterUserName = requesterUserName;
		this.requesterUserMobileNo = requesterUserMobileNo;
		this.requesterUserEmailId = requesterUserEmailId;
		this.requesterUserCity = requesterUserCity;
		this.requesterUserPincode = requesterUserPincode;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getGranterBookId() {
		return granterBookId;
	}
	public void setGranterBookId(int granterBookId) {
		this.granterBookId = granterBookId;
	}
	public String getGranterBookTitle() {
		return granterBookTitle;
	}
	public void setGranterBookTitle(String granterBookTitle) {
		this.granterBookTitle = granterBookTitle;
	}
	public String getGranterBookAuthor() {
		return granterBookAuthor;
	}
	public void setGranterBookAuthor(String granterBookAuthor) {
		this.granterBookAuthor = granterBookAuthor;
	}
	public String getGranterBookGenre() {
		return granterBookGenre;
	}
	public void setGranterBookGenre(String granterBookGenre) {
		this.granterBookGenre = granterBookGenre;
	}
	public String getGranterBookPublisher() {
		return granterBookPublisher;
	}
	public void setGranterBookPublisher(String granterBookPublisher) {
		this.granterBookPublisher = granterBookPublisher;
	}
	public float getGranterBookCost() {
		return granterBookCost;
	}
	public void setGranterBookCost(float granterBookCost) {
		this.granterBookCost = granterBookCost;
	}
	public int getGranterUserId() {
		return granterUserId;
	}
	public void setGranterUserId(int granterUserId) {
		this.granterUserId = granterUserId;
	}
	public String getGranterUserName() {
		return granterUserName;
	}
	public void setGranterUserName(String granterUserName) {
		this.granterUserName = granterUserName;
	}
	public long getGranterUserMobileNo() {
		return granterUserMobileNo;
	}
	public void setGranterUserMobileNo(long granterUserMobileNo) {
		this.granterUserMobileNo = granterUserMobileNo;
	}
	public String getGranterUserEmailId() {
		return granterUserEmailId;
	}
	public void setGranterUserEmailId(String granterUserEmailId) {
		this.granterUserEmailId = granterUserEmailId;
	}
	public String getGranterUserCity() {
		return granterUserCity;
	}
	public void setGranterUserCity(String granterUserCity) {
		this.granterUserCity = granterUserCity;
	}
	public int getGranterUserPincode() {
		return granterUserPincode;
	}
	public void setGranterUserPincode(int granterUserPincode) {
		this.granterUserPincode = granterUserPincode;
	}
	public int getRequesterBookId() {
		return requesterBookId;
	}
	public void setRequesterBookId(int requesterBookId) {
		this.requesterBookId = requesterBookId;
	}
	public String getRequesterBookTitle() {
		return requesterBookTitle;
	}
	public void setRequesterBookTitle(String requesterBookTitle) {
		this.requesterBookTitle = requesterBookTitle;
	}
	public String getRequesterBookAuthor() {
		return requesterBookAuthor;
	}
	public void setRequesterBookAuthor(String requesterBookAuthor) {
		this.requesterBookAuthor = requesterBookAuthor;
	}
	public String getRequesterBookGenre() {
		return requesterBookGenre;
	}
	public void setRequesterBookGenre(String requesterBookGenre) {
		this.requesterBookGenre = requesterBookGenre;
	}
	public String getRequesterBookPublisher() {
		return requesterBookPublisher;
	}
	public void setRequesterBookPublisher(String requesterBookPublisher) {
		this.requesterBookPublisher = requesterBookPublisher;
	}
	public float getRequesterBookCost() {
		return requesterBookCost;
	}
	public void setRequesterBookCost(float requesterBookCost) {
		this.requesterBookCost = requesterBookCost;
	}
	public int getRequesterUserId() {
		return requesterUserId;
	}
	public void setRequesterUserId(int requesterUserId) {
		this.requesterUserId = requesterUserId;
	}
	public String getRequesterUserName() {
		return requesterUserName;
	}
	public void setRequesterUserName(String requesterUserName) {
		this.requesterUserName = requesterUserName;
	}
	public long getRequesterUserMobileNo() {
		return requesterUserMobileNo;
	}
	public void setRequesterUserMobileNo(long requesterUserMobileNo) {
		this.requesterUserMobileNo = requesterUserMobileNo;
	}
	public String getRequesterUserEmailId() {
		return requesterUserEmailId;
	}
	public void setRequesterUserEmailId(String requesterUserEmailId) {
		this.requesterUserEmailId = requesterUserEmailId;
	}
	public String getRequesterUserCity() {
		return requesterUserCity;
	}
	public void setRequesterUserCity(String requesterUserCity) {
		this.requesterUserCity = requesterUserCity;
	}
	public int getRequesterUserPincode() {
		return requesterUserPincode;
	}
	public void setRequesterUserPincode(int requesterUserPincode) {
		this.requesterUserPincode = requesterUserPincode;
	}
	
	

}
