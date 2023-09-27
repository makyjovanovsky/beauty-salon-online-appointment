package com.example.salonbella.repository;

import com.example.salonbella.entity.OrderEntity;
import com.example.salonbella.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUser(UserEntity user);


}
