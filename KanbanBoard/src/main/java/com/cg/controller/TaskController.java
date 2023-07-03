package com.cg.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Task;
import com.cg.exception.NoTaskFoundException;
import com.cg.service.TaskService;



@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://localhost:4200")
public class TaskController  {

	@Autowired
	private TaskService taskSer;
	
	@PostMapping("/addtask")
	public ResponseEntity<Task> add(@Valid @RequestBody Task task){
		
		return new ResponseEntity<>(taskSer.createTask(task), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Task>> getAll() throws NoTaskFoundException{
		return new ResponseEntity<>(taskSer.viewAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Task> delete(@PathVariable long id)throws NoTaskFoundException{
		return new ResponseEntity<>(taskSer.deletetask(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@Valid@RequestBody Task task, @PathVariable long id) throws NoTaskFoundException{
		return new ResponseEntity<>(taskSer.updateTask(task, id), HttpStatus.FOUND);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Task> getByID(Long id) throws NoTaskFoundException{
		return new ResponseEntity<>(taskSer.getTaskByID(id), HttpStatus.FOUND);

	}
}

