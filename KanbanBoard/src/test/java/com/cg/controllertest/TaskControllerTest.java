package com.cg.controllertest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.controller.TaskController;
import com.cg.entity.Task;
import com.cg.exception.NoTaskFoundException;
import com.cg.service.TaskService;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
	@Mock
	private TaskService tSer;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private TaskController tCont;
	
	//project variable declared
	private Task task;
	//list is made
	private List<Task> taskList;
	
	
	@BeforeEach
	private void initEach() {
		task = new Task();
		task.setTaskId(10);
		task.setTaskName("C++");
		task.setDescription("Updated Project");
		task.setDate(LocalDate.now());
		
		taskList=new ArrayList<>();
		taskList.add(task);
	}
	
	
	@Test
	void addTask() throws NoTaskFoundException{
		when(tSer.createTask(task)).thenReturn(task);
		ResponseEntity<Task> response = tCont.add(task);
		//System.out.println(response.getStatusCode());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void updateTaskTest() throws NoTaskFoundException {
		task.setDescription("Task Done");
		Mockito.when(tSer.updateTask(task, 10)).thenReturn(task);
		ResponseEntity<Task> response = tCont.update(task, 10);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());

	}
	
	@Test
	void findAllTaskTest() throws NoTaskFoundException{
		Mockito.when(tSer.viewAll()).thenReturn(taskList);
		ResponseEntity<List<Task>> response = tCont.getAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}
	
	@Test
	void testFindById() throws NoTaskFoundException{
		when(tSer.getTaskByID(10)).thenReturn(task).thenAnswer(i -> tSer.getTaskByID(10));
		ResponseEntity<Task> response = tCont.getByID((long) 10);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());		
	}
	
	
	
	

	@Test
	void deleteProjectTest() throws NoTaskFoundException {
			when(tSer.deletetask(10)).thenReturn(task);
			ResponseEntity<Task> response = tCont.delete(10);
			assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	
}
