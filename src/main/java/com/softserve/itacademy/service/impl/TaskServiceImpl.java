package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        if (task == null) {
            throw new NullEntityReferenceException("Task can not be null");
        }
        return taskRepository.save(task);
    }

    @Override
    public Task readById(long id) {
        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("task - " + id + " not found");
        }
    }

    @Override
    public Task update(Task task) {
        if (task == null) {
            throw new NullEntityReferenceException("task can not be null");
        }
        Task oldTask = readById(task.getId());
        if (oldTask == null) {
            throw new EntityNotFoundException("task - " + task.getId() + " not found");
        }
        return taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        Task task = readById(id);
        if (task == null) {
            throw new EntityNotFoundException("task - " + id + " not found");
        }
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        List<Task> tasks = taskRepository.getByTodoId(todoId);
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }
}
