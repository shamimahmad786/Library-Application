package com.example.demo.dataentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.dataentry.modal.NcertAchiventSurvey;

public interface NcertAchiventSurveyRepostory extends JpaRepository<NcertAchiventSurvey, Integer> {

}
