package com.cg.entity;



import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="task_table")
public class Task {

	@Id
	@GeneratedValue	
	@ApiModelProperty(readOnly = true)
	private long taskId;
	private String taskName;
	private String description;
	private LocalDate date;
	
	public Task() {}
	
	public Task(long taskId, String taskName, String description, LocalDate date) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.date = date;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
	
}
