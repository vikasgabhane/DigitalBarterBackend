package com.db.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		//serviceProviderServiceDetails.setUserIdSp(((User)session.getAttribute("userid")).getUserId());
		user.setUserId(userId);
		
		
		
		 // Get the notes from request session.
       /* List<String> userIds = (List<String>) request.getSession().getAttribute("MY_SESSION_USER_ID_CONSTANT");
 
        // Check if notes is present in session or not.
        if (userIds == null) {
            //log.info("No user Id are fetch from the session object. Setting the value in the session object.");
            userIds = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_USER_ID", userIds);
        }
 
        userIds.add(String.valueOf(user1.get(0).getUserId()));
        request.getSession().setAttribute("MY_SESSION_USER_ID", userIds);
        */
		
      
		return userRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
	}
	
		/*
		@PostMapping("/login")
		public User cheakLogin(@RequestBody User user,HttpSession session) {
			//serviceProviderServiceDetails.setUserIdSp(((User)session.getAttribute("userid")).getUserId());
			user.setUserId(userId);
			User user1 = userRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
			return  user1;
		}
		*/
	/*
	 * @PostMapping("/login")
	public boolean cheakLogin(@RequestBody User user,HttpSession session) {
		//serviceProviderServiceDetails.setUserIdSp(((User)session.getAttribute("userid")).getUserId());
		
		user.setUserId(userId);
		 List<User> user1 = userRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
		 //return user1.get(0);
		 if(user1.get(0).getUserName()!="null")
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}*/
	
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

	//Destroy Session
	 @PostMapping(value = "/destroy")
	    public void destroySession(final HttpServletRequest request) {
	        //log.info("Invaliding the session and removing the data.");
	        // Invalidate the session and this will clear the data from the configured database.
	        request.getSession().invalidate();
	       
	    }

}
