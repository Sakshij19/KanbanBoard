package com.cg.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.KanbanBoardApplication;
import com.cg.dao.TaskRepository;
import com.cg.entity.Task;
import com.cg.exception.NoTaskFoundException;
import com.cg.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskRepository tRepo;

	@Autowired
	private ModelMapper modelMapper;
	static final Logger LOG = LogManager.getLogger(KanbanBoardApplication.class);

	@Override
	public Task createTask(Task task) {
		Task task1 = modelMapper.map(task, Task.class);
		tRepo.save(task1);
		LOG.info("task added!");
		return task;
	}

	@Override
	public List<Task> viewAll() throws NoTaskFoundException{
		LOG.info("All Tasks Found!");
		return tRepo.findAll().stream().map(task1 -> modelMapper.map(task1, Task.class))
				.collect(Collectors.toList());		
		}

	@Override
	public Task updateTask(@Valid Task task, long id) throws NoTaskFoundException {
		Optional<Task> taskUpdate = tRepo.findById(id);
		if(taskUpdate.isPresent()) {
			taskUpdate.get().setTaskId(task.getTaskId());
			taskUpdate.get().setTaskName(task.getTaskName());
			taskUpdate.get().setDescription(task.getDescription());
			taskUpdate.get().setDate(task.getDate());
			tRepo.save(taskUpdate.get());
			LOG.info("task updated!");

			return task;
		}
		
		else {
			throw new NoTaskFoundException("Task not found");
		}
		}

	@Override
	public Task deletetask(long id) throws NoTaskFoundException {
		Optional<Task> taskToDel = tRepo.findById(id);
		if(taskToDel.isPresent()) {
			tRepo.delete(taskToDel.get());
			LOG.info("task deleted!");

		}
		else
			throw new NoTaskFoundException("Task not found");
		return modelMapper.map(taskToDel, Task.class);
	}

	@Override
	public Task getTaskByID(long id) throws NoTaskFoundException {

		Optional<Task> task = tRepo.findById(id);
		if(task.isPresent()) {
			LOG.info("task found!");

			return modelMapper.map(task.get(), Task.class);
			

			
		}
		else
			throw new NoTaskFoundException("No Task found");
		

}
}
