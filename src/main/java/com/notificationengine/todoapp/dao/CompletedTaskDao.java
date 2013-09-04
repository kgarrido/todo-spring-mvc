package com.notificationengine.todoapp.dao;

import com.notificationengine.todoapp.domain.CompletedTask;
import com.notificationengine.todoapp.domain.Task;

import java.util.Collection;

public interface CompletedTaskDao {

    public CompletedTask getCompletedTaskById(Integer id);

    public Collection<CompletedTask> getAllCompletedTasks();

    public void removeCompletedTask(Integer id);

    public void addCompletedTask(CompletedTask completedTask);

    public void updateCompletedTask(CompletedTask completedTask);

}
