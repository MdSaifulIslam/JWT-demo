package com.springlearn.crudDemp.service;

import java.util.List;
import java.util.Optional;

import com.springlearn.crudDemp.entity.Task;

public interface TaskService {

	public Task saveTask(Task task);

	public List<Task> findAll();

	public List<Task> findByUserId(int userId);
	
	public Optional<Task> findByIdAndUserId(int taskId, int userId);
	
	public void deleteTask(Task task);
	
}
