package com.example.demo.dataentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.dataentry.modal.NcertTextBookDetails;

public interface NcertTextBookDetailsRepostory extends JpaRepository<NcertTextBookDetails, Integer>{

}
