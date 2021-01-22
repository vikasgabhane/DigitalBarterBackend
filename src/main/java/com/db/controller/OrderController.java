package com.db.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.db.dto.Order;
import com.db.dto.User;
import com.db.exception.ResourceNotFoundException;
import com.db.repository.BookRepository;
import com.db.repository.ExchangeRequestRepository;
import com.db.repository.OrderRepository;
import com.db.repository.UserRepository;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
	
	@Autowired
	DigitalBarterApplication digitalBarterApplication;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ExchangeRequestRepository exchangeRequestRepository;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	
	
	@GetMapping("/order/{exchangeId}")
	public  ResponseEntity<Order> placeOrder(@PathVariable int exchangeId) {
		
		
		Order order = new Order();
		ExchangeRequest exreq = exchangeRequestRepository.findById(exchangeId)
				.orElseThrow(() -> new ResourceNotFoundException("Exchange Request not exist with id :" + exchangeId));
		
		order.setRequesterBookId(exreq.getRequesterBookId());
		order.setRequesterBookAuthor(exreq.getRequesterBookAuthor());
		order.setRequesterBookCost(exreq.getRequesterBookCost());
		order.setRequesterBookGenre(exreq.getRequesterBookGenre());
		order.setRequesterBookPublisher(exreq.getRequesterBookPublisher());
		order.setRequesterBookTitle(exreq.getRequesterBookTitle());
		order.setRequesterUserId(exreq.getRequesterUserId());
		
		User u1 = userRepository.findById(exreq.getRequesterUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + exreq.getRequesterUserId()));
		
		order.setRequesterUserName(u1.getUserName() );
		order.setRequesterUserMobileNo(u1.getMobileNo() );
		order.setRequesterUserEmailId( u1.getEmailId() );
		order.setRequesterUserCity( u1.getCity() );
		order.setRequesterUserPincode( u1.getPincode() );
		
		
		order.setGranterBookId(exreq.getGranterBookId());
		order.setGranterBookAuthor(exreq.getGranterBookAuthor());
		order.setGranterBookCost(exreq.getGranterBookCost());
		order.setGranterBookGenre(exreq.getGranterBookGenre());
		order.setGranterBookPublisher(exreq.getGranterBookPublisher());
		order.setGranterBookTitle(exreq.getGranterBookTitle());
		order.setGranterUserId(exreq.getGranterUserId());
		
		User u2 = userRepository.findById(exreq.getGranterUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + exreq.getGranterUserId()));
		
		order.setGranterUserName(u2.getUserName() );
		order.setGranterUserMobileNo( u2.getMobileNo());
		order.setGranterUserEmailId( u2.getEmailId() );
		order.setGranterUserCity( u2.getCity() );
		order.setGranterUserPincode( u2.getPincode() );
		
		sendOrderEmail(order);
		
		Order newOrder = orderRepository.save(order);
		return ResponseEntity.ok(newOrder);
		
		//return userRepository.save(user);
				
	}
	
	public void sendOrderEmail(Order order) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(order.getRequesterUserEmailId());

        msg.setSubject("Digital Barter Exchange Confirmation!!!!");
        msg.setText("Hello,Thank You For using Digital Barter,"
        		+ "\n\tplease find the details of the exchange that has been agreed by both of the users. :-"
        		+ "\n"
        		+ "Contact Details :- "
        		+ "\nName : "+order.getGranterUserName()+
        		"\nEmail : "+order.getGranterUserEmailId()+
        		"\nPhone No : "+order.getGranterUserMobileNo()+
        		"\nAddress : "+order.getGranterUserCity()+
        		"\nPincode : "+order.getGranterUserPincode()+
        		"\n\nBook Details :- "
        		+ "\nBook : "+order.getGranterBookTitle()+
        		"\nAuthor : "+order.getGranterBookAuthor()+
        		"\nPublisher : "+order.getGranterBookPublisher()+
        		"\n\nIn exchange for your book, details are as follows :-"
        		+ "\nBook : "+order.getRequesterBookTitle()+
        		"\nAuthor : "+order.getRequesterBookAuthor()+
        		"\nPublisher : "+order.getRequesterBookPublisher()+
        		"\n\n\nRegards,"
        		+ "\nDigital Barter"
        		);

        
        SimpleMailMessage msg1 = new SimpleMailMessage();
        msg1.setTo(order.getGranterUserEmailId());

        msg1.setSubject("Digital Barter Exchange Confirmation!!!!");
        msg1.setText("Hello,Thank You For using Digital Barter,"
        		+ "\n\tplease find the details of the exchange that has been agreed by both of the users. :-"
        		+ "\n"
        		+ "Contact Details :- "
        		+ "\nName : "+order.getRequesterUserName()+
        		"\nEmail : "+order.getRequesterUserEmailId()+
        		"\nPhone No : "+order.getRequesterUserMobileNo()+
        		"\nAddress : "+order.getRequesterUserCity()+
        		"\nPincode : "+order.getRequesterUserPincode()+
        		"\n\nBook Details :- "
        		+ "\nBook : "+order.getRequesterBookTitle()+
        		"\nAuthor : "+order.getRequesterBookAuthor()+
        		"\nPublisher : "+order.getRequesterBookPublisher()+
        		"\n\nIn exchange for your book, details are as follows :-"
        		+ "\nBook : "+order.getGranterBookTitle()+
        		"\nAuthor : "+order.getGranterBookAuthor()+
        		"\nPublisher : "+order.getGranterBookPublisher()+
        		"\n\n\nRegards,"
        		+ "\nDigital Barter"
        		);

        javaMailSender.send(msg);
        javaMailSender.send(msg1);
		
	}
	
	// delete Book rest api
		@DeleteMapping("/order/{exchangeId}")
		public ResponseEntity<Map<String, Boolean>> deleteBooksAndRequest(@PathVariable int exchangeId)
		{
				ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(exchangeId)
				.orElseThrow(() -> new ResourceNotFoundException("Request not exist with id :" + exchangeId));
				
				Book book1 = bookRepository.findById(exchangeRequest.getGranterBookId())
						.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + exchangeRequest.getGranterBookId()));
				
				Book book2 = bookRepository.findById(exchangeRequest.getRequesterBookId())
						.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + exchangeRequest.getRequesterBookId()));
					
				bookRepository.delete(book1);
				bookRepository.delete(book2);
				exchangeRequestRepository.delete(exchangeRequest);
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				return ResponseEntity.ok(response);
		}
			

}
