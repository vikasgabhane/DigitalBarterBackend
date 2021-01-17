package com.db.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.repository.UserRepository;
import com.db.DigitalBarterApplication;
import com.db.dto.User;

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
	
	
	int userId;

	@GetMapping("/")
	public String helloMsg() {
		return "Hello World!.....";
	}
	
	//get user by userName
	@GetMapping("/getUser/{userName}")
	public ResponseEntity<User> getUserByName(@PathVariable String userName) {
		User user = userRepository.findByUserName(userName);
				
		return ResponseEntity.ok(user);
	}
	
	//get user by emailId
		@GetMapping("/getUserByEmail/{emailId}")
		public ResponseEntity<User> getUserByEmailId(@PathVariable String emailId) {
			User user = userRepository.findByEmailId(emailId);
					
			return ResponseEntity.ok(user);
		}
	
	//login 
	
	@PostMapping("/login")
	public List<User> cheakLogin(@RequestBody User user,HttpSession session) {
		//serviceProviderServiceDetails.setUserIdSp(((User)session.getAttribute("userid")).getUserId());
		user.setUserId(userId);
		return userRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
	}
	
	//register
	
	@PostMapping("/adduser")
	public User insertUser(@RequestBody User user,HttpSession session) {
		User user1=userRepository.save(user);
		System.out.println(user1.getUserId());
		 userId=user1.getUserId();
		//session.setAttribute("userid", user1);
		session.setAttribute("userId", userId);
		//System.out.println(((User)session.getAttribute("userid")).getUserId());
			System.out.println("60 "+session.getAttribute("userId"));
		return user1;
		
		//return userRepository.save(user);
				
	}
	
	//change Password
	
	@PutMapping("/changepassword/{userName}")
	public ResponseEntity<User> changePassword(@PathVariable String userName, @RequestBody User userDetails){
		//Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
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


}
