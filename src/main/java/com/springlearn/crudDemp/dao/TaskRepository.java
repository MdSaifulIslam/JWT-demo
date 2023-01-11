package com.springlearn.crudDemp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springlearn.crudDemp.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByUserId(int userId);
	
	Optional<Task> findByIdAndUserId(int taskId, int userId);
}
