package com.springlearn.crudDemp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springlearn.crudDemp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>{

	@Modifying
	@Query("from Role")
	List<Role> getAllRole();
}
