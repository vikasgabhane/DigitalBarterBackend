package com.db.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.DigitalBarterApplication;
import com.db.dto.Book;
import com.db.dto.ExchangeRequest;
import com.db.dto.User;
import com.db.exception.ResourceNotFoundException;
import com.db.repository.BookRepository;
import com.db.repository.ExchangeRequestRepository;
import com.db.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ExchangeRequestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ExchangeRequestRepository exchangeRequestRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	DigitalBarterApplication digitalBarterApplication;
	
	
	@PostMapping("/exchangereq/{bookId}")
	public ExchangeRequest insertUser(@PathVariable int bookId,@RequestBody Book book) {
		
		System.out.println(book.getTitle());
		
		ExchangeRequest exreq = new ExchangeRequest();
		Book reqBook = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + bookId));
		
		exreq.setRequesterBookId(bookId);
		exreq.setRequesterBookAuthor(reqBook.getAuthor());
		exreq.setRequesterBookCost(reqBook.getCost());
		exreq.setRequesterBookGenre(reqBook.getGenre());
		exreq.setRequesterBookPublisher(reqBook.getPublisher());
		exreq.setRequesterBookTitle(reqBook.getTitle());
		exreq.setRequesterUserId(reqBook.getUserId());
		
		exreq.setGranterBookId(book.getBookId());
		exreq.setGranterBookAuthor(book.getAuthor());
		exreq.setGranterBookCost(book.getCost());
		exreq.setGranterBookGenre(book.getGenre());
		exreq.setGranterBookPublisher(book.getPublisher());
		exreq.setGranterBookTitle(book.getTitle());
		exreq.setGranterUserId(book.getUserId());
		
		return exchangeRequestRepository.save(exreq);
		
		//return userRepository.save(user);
				
	}
	
	//get exchange req by id rest api
	@GetMapping("/exchangereq/{userId}")
	public List<ExchangeRequest>  getRequestsByGranterUserId(@PathVariable int userId)
	{
						
		return exchangeRequestRepository.findByGranterUserId(userId);
						
	}
	
	
	//get exchange pending or send by us req by id rest api
		@GetMapping("/exchangereq1/{userId}")
		public List<ExchangeRequest>  getRequestsByRequesterUserId(@PathVariable int userId)
		{
							
			return exchangeRequestRepository.findByRequesterUserId(userId);
							
		}
		
		//get Exchange Req by id rest api
		@GetMapping("/exchangereq2/{exchangeId}")
		public ExchangeRequest  getBookById(@PathVariable int exchangeId)
		{
			ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(exchangeId)
					.orElseThrow(() -> new ResourceNotFoundException("Request not exist with id :" + exchangeId));
			
			return exchangeRequest;
			
			
		}
	
	// delete Book rest api
	@DeleteMapping("/exchangereq/{exchangeId}")
	public ResponseEntity<Map<String, Boolean>> deleteRequest(@PathVariable int exchangeId)
	{
			ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(exchangeId)
			.orElseThrow(() -> new ResourceNotFoundException("Request not exist with id :" + exchangeId));
				
			exchangeRequestRepository.delete(exchangeRequest);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
	}
			
			

}
