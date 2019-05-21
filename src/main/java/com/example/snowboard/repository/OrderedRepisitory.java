package com.example.snowboard.repository;

import com.example.snowboard.entities.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepisitory extends JpaRepository<Ordered,Integer> {
}
