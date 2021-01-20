package com.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.dto.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	public Book findByIsbn(String isbn);

	public List<Book> findByUserIdNot(int userId);

	public List<Book> findByUserId(int userId);

	//public Optional<Book> findByBookIdNot(int bookId);
	
	//public Book findById(int bookId);
	
	//public List<Book> findByUserIdNotLike(int userId);

}
