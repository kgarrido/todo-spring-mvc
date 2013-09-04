package com.notificationengine.todoapp.service;


import com.notificationengine.todoapp.domain.CompletedTask;

import java.util.Collection;

public interface CompletedTaskService {

    public CompletedTask getCompletedTaskById(Integer id);

    public Collection<CompletedTask> getAllTasks();

    public void removeCompletedTask(Integer id);

    public void addCompletedTask(CompletedTask completedTask);

    public void updateCompletedTask(CompletedTask completedTask);
}
