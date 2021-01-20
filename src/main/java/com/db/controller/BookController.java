package com.db.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.dto.Book;
import com.db.dto.User;
import com.db.exception.ResourceNotFoundException;
import com.db.repository.BookRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookController {
	
	
	@Autowired
	private BookRepository bookRepository;
	
	//get all books
	@GetMapping("/books")
	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
		
		
		
	}
	//create book rest api
	@PostMapping("/books/{userId}")
	public Book createBook(@PathVariable int userId,@RequestBody Book book) {
		
		 book.setUserId(userId);
		 return bookRepository.save(book);
	}
	
	//get book by id rest api
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Optional<Book>>  getBookById(@PathVariable int bookId)
	{
		
		Optional<Book> book = bookRepository.findById(bookId);
		
		
		return ResponseEntity.ok(book);
		
	}
	
	//update employee rest api
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable int bookId,@RequestBody Book bookDetails)
	{
		
		//Book book = bookRepository.findById(bookId);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + bookId));
		book.setTitle(bookDetails.getTitle());
		book.setUserId(book.getUserId());
		book.setAuthor(bookDetails.getAuthor());
		book.setPublisher(bookDetails.getPublisher());
		book.setGenre(bookDetails.getGenre());
		book.setIsbn(bookDetails.getIsbn());
		book.setCost(bookDetails.getCost());
		
		Book updatedBook= bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
		
	}
	
	//get book by id rest api
		@GetMapping("/books1/{userId}")
		public List<Book>  getBookNotByUserId(@PathVariable int userId)
		{
			/*User user = new User();
			//List<Book> book = new ArrayList<Book>();
			//book = bookRepository.findByUserId(userId);
			List<String> userIds = (List<String>) session.getAttribute("MY_SESSION_USER_ID");
			if(userIds == null) {
				userIds = new ArrayList<>();
			}
			book..addAttribute("sessionId",userIds);
			*/
			return bookRepository.findByUserIdNot(userId);
			
		}

		//get book by id rest api
		@GetMapping("/books2/{userId}")
		public List<Book>  getBookByUserId(@PathVariable int userId)
		{
					
			return bookRepository.findByUserId(userId);
					
		}
		
		// delete Book rest api
		@DeleteMapping("/books/{bookId}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int bookId){
			Book book = bookRepository.findById(bookId)
					.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + bookId));
			
			bookRepository.delete(book);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	
}
