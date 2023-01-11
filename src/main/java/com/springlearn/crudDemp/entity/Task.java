package com.springlearn.crudDemp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "user_task")
public class Task {
	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "task_title")
	private String taskTitle;

	@Column(name = "task_time")
	private String taskTime;

	@Column(name = "task_date")
	private String taskDate;

	@Value("0")
	@Column(name = "is_checked")
	private String checked;

	@Column(name = "user_id")
	private int userId;

	public Task() {
	}

	public Task(String taskTitle, String taskTime, String taskDate, String checked, int userId) {
		this.taskTitle = taskTitle;
		this.taskTime = taskTime;
		this.taskDate = taskDate;
		this.checked = checked;
		this.userId = userId;
	}

	public Task(int id, String taskTitle, String taskTime, String taskDate, String checked, int userId) {
		this.id = id;
		this.taskTitle = taskTitle;
		this.taskTime = taskTime;
		this.taskDate = taskDate;
		this.checked = checked;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskTitle=" + taskTitle + ", taskTime=" + taskTime + ", taskDate=" + taskDate
				+ ", checked=" + checked + ", userId=" + userId + "]";
	}

}
