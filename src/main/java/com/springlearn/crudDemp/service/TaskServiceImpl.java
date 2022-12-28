package com.springlearn.crudDemp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlearn.crudDemp.dao.TaskRepository;
import com.springlearn.crudDemp.entity.Task;

@Service		
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> findByUserId(int userId) {
		return taskRepository.findByUserId(userId);
	}

	
}
