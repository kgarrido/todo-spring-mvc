package com.notificationengine.todoapp.dao;


import com.notificationengine.todoapp.domain.Task;

import java.util.Collection;

public interface TaskDao {

    public Task getTaskById(Integer id);

    public Collection<Task> getAllTasks();

    public void removeTask(Integer id);

    public void addTask(Task task);

    public void updateTask(Task task);
}
