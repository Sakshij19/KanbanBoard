package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
