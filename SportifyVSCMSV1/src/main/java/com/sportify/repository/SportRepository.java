package com.sportify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.entities.Sport;

public interface SportRepository extends JpaRepository<Sport, Integer> {

}
