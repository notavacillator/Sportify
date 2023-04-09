package com.sportify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.entities.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	
}
