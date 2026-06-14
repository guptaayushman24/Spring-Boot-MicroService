package com.example.orderservice.repository;

import com.example.orderservice.model.OutBoxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutBoxEventRepository extends JpaRepository<OutBoxEvent,Long> {

    @Query("SELECT e FROM OutBoxEvent e WHERE e.publishedAt IS NULL")
    List<OutBoxEvent> findUnpublished();
}
