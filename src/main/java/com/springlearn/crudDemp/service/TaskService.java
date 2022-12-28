package com.springlearn.crudDemp.service;

import java.util.List;

import com.springlearn.crudDemp.entity.Task;

public interface TaskService {

	public Task saveTask(Task task);

	public List<Task> findAll();

	public List<Task> findByUserId(int userId);
}
