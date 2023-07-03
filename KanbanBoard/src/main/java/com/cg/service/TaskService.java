package com.cg.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.entity.Task;
import com.cg.exception.NoTaskFoundException;

public interface TaskService {
	
	public Task createTask(Task task);

	public List<Task> viewAll() throws NoTaskFoundException;

	public Task updateTask(@Valid Task task, long id ) throws NoTaskFoundException;

	public Task deletetask(long id) throws NoTaskFoundException;

	public Task getTaskByID(long id) throws NoTaskFoundException;

}
