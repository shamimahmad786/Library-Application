package com.example.demo.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.analytics.modal.PgiMsColumn;
import com.example.demo.analytics.modal.PgiMsSummarized;

public interface PgiMsSummarizedRepository  extends JpaRepository<PgiMsSummarized, Integer>{

}
