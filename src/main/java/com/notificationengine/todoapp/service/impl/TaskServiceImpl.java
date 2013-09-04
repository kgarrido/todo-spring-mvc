package com.notificationengine.todoapp.service.impl;


import com.notificationengine.todoapp.constants.Constants;
import com.notificationengine.todoapp.dao.TaskDao;
import com.notificationengine.todoapp.domain.Task;
import com.notificationengine.todoapp.service.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service(Constants.TASK_SERVICE)
@Transactional
public class TaskServiceImpl implements TaskService {

    public static Logger LOGGER = Logger.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskDao taskDao;

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Integer id) {

        LOGGER.info("Get Task with id " + id);

        return this.taskDao.getTaskById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Task> getAllTasks() {

        LOGGER.info("Retrieve all tasks");

        return this.taskDao.getAllTasks();
    }

    @Override
    @Transactional
    public void removeTask(Integer id) {

        LOGGER.info("Delete task with id " + id);

        this.taskDao.removeTask(id);
    }

    @Override
    @Transactional
    public void addTask(Task task) {

        LOGGER.info("Add task " + task.toString());

        this.taskDao.addTask(task);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {

        LOGGER.info("Update task " + task.toString());

        this.taskDao.updateTask(task);
    }

}
