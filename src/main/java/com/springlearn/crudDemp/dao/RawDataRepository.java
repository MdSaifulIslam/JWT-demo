package com.springlearn.crudDemp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springlearn.crudDemp.entity.RawData;

@Repository
public interface RawDataRepository extends JpaRepository<RawData, Integer> {
	
	Optional<RawData> findByUserId(int userId);
}
