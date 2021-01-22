package com.db.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.repository.UserRepository;
import com.db.DigitalBarterApplication;
import com.db.dto.Book;
import com.db.dto.User;
import com.db.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	//@RequestMapping("/api/v1/")
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	DigitalBarterApplication digitalBarterApplication;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	//private static final String MY_SESSION_USER_ID_CONSTANT = "MY_SESSION_IDS";
	//private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	int userId;

	@GetMapping("/")
	public String helloMsg() {
		return "Hello World!.....";
	}
	
	//get user by userName
	@GetMapping("/getUser/{userName}")
	public User getUserByName(@PathVariable String userName) {
		User user = userRepository.findByUserName(userName);
				
		return user;
	}
	
	//get user by emailId
		@GetMapping("/getUserByEmail/{emailId}")
		public ResponseEntity<User> getUserByEmailId(@PathVariable String emailId) {
			User user = userRepository.findByEmailId(emailId);
					
			return ResponseEntity.ok(user);
		}
	
	//login 
	
	@PostMapping("/login")
	public List<User> cheakLogin(@RequestBody User user) {
		user.setUserId(userId);		
      
		return userRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
	}
	
		
	
	@PostMapping("/adduser")
	public User insertUser(@RequestBody User user) {
		User user1=userRepository.save(user);
		
		return user1;
		
		
				
	}
	
	//change Password
	
	@PutMapping("/changepassword/{userName}")
	public ResponseEntity<User> changePassword(@PathVariable String userName, @RequestBody User userDetails){
		User user = userRepository.findByUserName(userName);

		user.setPassword(userDetails.getPassword());
		
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	
	//forget password
	@PostMapping("/forgetpassword")
	public String forgetPassword(@RequestBody User userDetails) throws AddressException, MessagingException, IOException {
		
		User user=userRepository.findByEmailId(userDetails.getEmailId());
		
		digitalBarterApplication.sendEmail(user);
		
        return "Mail sent successfully";
		
	}

	//Destroy Session
	 @PostMapping(value = "/destroy")
	    public void destroySession(final HttpServletRequest request) {
	        //log.info("Invaliding the session and removing the data.");
	        // Invalidate the session and this will clear the data from the configured database.
	        request.getSession().invalidate();
	       
	    }
	 
	// delete User rest api
			@DeleteMapping("/deleteuser/{userId}")
			public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int userId){
				User user = userRepository.findById(userId)
						.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + userId));
				
				userRepository.delete(user);
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				return ResponseEntity.ok(response);
			}

}
