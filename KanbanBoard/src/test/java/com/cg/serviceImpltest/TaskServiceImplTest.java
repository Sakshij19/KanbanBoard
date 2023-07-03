package com.cg.serviceImpltest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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
class TaskServiceImplTest {

	@Mock
	private TaskService tSer;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private TaskController tCont;
	
	//task variable declared
	private Task task;
	//list is made
	private List<Task> taskList;
	
	@BeforeEach
	private void initEach() {
		task = new Task();
		task.setTaskId(10);
		task.setTaskName("Java");
		task.setDescription("New Project");
		task.setDate(LocalDate.now());
		
		taskList=new ArrayList<>();
		taskList.add(task);
	}
	
	@Test
	void addTaskTest() throws NoTaskFoundException{
		Mockito.when(tSer.createTask(task)).thenReturn(task);
		ResponseEntity<Task> res = tCont.add(task);
		//System.out.println(res.getStatusCode());
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
	}
	
	
	@Test
	void findAllTaskTest() throws NoTaskFoundException{
		Mockito.when(tSer.viewAll()).thenReturn(taskList);
		assertEquals(tSer.viewAll(), taskList);
	}
	
	@Test
	void testFindById() throws NoTaskFoundException{
		when(tSer.getTaskByID(10)).thenReturn(task).thenAnswer(i -> tSer.getTaskByID(10));
		assertEquals(task, tSer.getTaskByID(10));
		//System.out.println(pro);
		
	}
	
	
	@Test
	void updateTaskTest() throws NoTaskFoundException{
		task.setTaskName("New Task");
		
		   Mockito.when(tSer.updateTask(task, 10)).thenReturn(task);
		   Assertions.assertEquals(task, tSer.updateTask(task, 10));
		   //System.out.println(task);
	}
	@Test
	void deleteTaskTest() throws NoTaskFoundException{
		when(tSer.deletetask(10)).thenReturn(task);
		ResponseEntity<Task> response = tCont.delete(10);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	
	}
	
	
}

