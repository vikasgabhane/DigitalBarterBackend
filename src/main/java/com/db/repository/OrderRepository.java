package com.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.dto.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
