package com.db.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exchangerequest")
public class ExchangeRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int exchangeId;
	private int granterBookId;
	private String granterBookTitle;
	private String granterBookAuthor;
	private String granterBookGenre;
	private String granterBookPublisher;
	private float granterBookCost;
	private int granterUserId;
	
	private int requesterBookId;
	private String requesterBookTitle;
	private String requesterBookAuthor;
	private String requesterBookGenre;
	private String requesterBookPublisher;
	private float requesterBookCost;
	private int requesterUserId;
	
	
	
	
	public ExchangeRequest() {
		super();
	}

	public ExchangeRequest(int granterBookId, String granterBookTitle, String granterBookAuthor,
			String granterBookGenre, String granterBookPublisher, float granterBookCost, int granterUserId,
			int requesterBookId, String requesterBookTitle, String requesterBookAuthor, String requesterBookGenre,
			String requesterBookPublisher, float requesterBookCost, int requesterUserId) {
		super();
		this.granterBookId = granterBookId;
		this.granterBookTitle = granterBookTitle;
		this.granterBookAuthor = granterBookAuthor;
		this.granterBookGenre = granterBookGenre;
		this.granterBookPublisher = granterBookPublisher;
		this.granterBookCost = granterBookCost;
		this.granterUserId = granterUserId;
		this.requesterBookId = requesterBookId;
		this.requesterBookTitle = requesterBookTitle;
		this.requesterBookAuthor = requesterBookAuthor;
		this.requesterBookGenre = requesterBookGenre;
		this.requesterBookPublisher = requesterBookPublisher;
		this.requesterBookCost = requesterBookCost;
		this.requesterUserId = requesterUserId;
	}
	
	public int getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
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
	
	
	

}
