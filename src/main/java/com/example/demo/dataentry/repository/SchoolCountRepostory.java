package com.example.demo.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dataentry.modal.SchoolCMMembercount;
import com.example.demo.dataentry.modal.SchooleDataEntry;

public interface SchoolCountRepostory extends JpaRepository<SchoolCMMembercount, Integer> {

	List<SchoolCMMembercount> findBySchoolIdAndAcademicYearTo(String schoolId,String academicYearTo);
}
