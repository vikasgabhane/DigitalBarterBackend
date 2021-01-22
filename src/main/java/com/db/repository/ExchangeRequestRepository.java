package com.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.dto.ExchangeRequest;

@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Integer>{

	List<ExchangeRequest> findByGranterUserId(int userId);

	List<ExchangeRequest> findByRequesterUserId(int userId);

}
