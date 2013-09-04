package com.notificationengine.todoapp.service.impl;

import com.notificationengine.todoapp.constants.Constants;
import com.notificationengine.todoapp.dao.CompletedTaskDao;
import com.notificationengine.todoapp.domain.CompletedTask;
import com.notificationengine.todoapp.service.CompletedTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service(Constants.COMPLETED_TASK_SERVICE)
@Transactional
public class CompletedTaskServiceImpl implements CompletedTaskService{

    public static Logger LOGGER = Logger.getLogger(CompletedTaskServiceImpl.class);

    @Autowired
    private CompletedTaskDao completedTaskDao;

    @Override
    @Transactional(readOnly = true)
    public CompletedTask getCompletedTaskById(Integer id) {

        LOGGER.info("Retrieve task by id with id: " + id);

        return this.completedTaskDao.getCompletedTaskById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CompletedTask> getAllTasks() {

        LOGGER.info("Retrieve all completed tasks");

        return this.completedTaskDao.getAllCompletedTasks();
    }

    @Override
    @Transactional
    public void removeCompletedTask(Integer id) {

        LOGGER.info("remove completed task with id: " + id);

        this.completedTaskDao.removeCompletedTask(id);
    }

    @Override
    @Transactional
    public void addCompletedTask(CompletedTask completedTask) {

        LOGGER.info("Add a completed task: " + completedTask);

        this.completedTaskDao.addCompletedTask(completedTask);
    }

    @Override
    @Transactional
    public void updateCompletedTask(CompletedTask completedTask) {

        LOGGER.info("Update a completed task: " + completedTask);

        this.completedTaskDao.updateCompletedTask(completedTask);
    }
}
