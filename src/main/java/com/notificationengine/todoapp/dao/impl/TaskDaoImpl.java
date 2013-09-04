package com.notificationengine.todoapp.dao.impl;

import com.notificationengine.todoapp.dao.TaskDao;
import com.notificationengine.todoapp.domain.Task;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class TaskDaoImpl implements TaskDao {

    public static Logger LOGGER = Logger.getLogger(TaskDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {

        LOGGER.info("Sets the session factory");
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Task getTaskById(Integer id) {

        LOGGER.info("Get Task with id: " + id);

        return (Task) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Task task WHERE task.id = ?")
                .setParameter(0, id)
                .uniqueResult();
    }

    @Override
    public Collection<Task> getAllTasks() {

        LOGGER.info("Retrieve all Tasks");

        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Task task")
                .list();
    }

    @Override
    public void removeTask(Integer id) {

        LOGGER.info("Remove Task with id: " + id);
        Task task = this.getTaskById(id);
        this.sessionFactory.getCurrentSession().delete(task);

    }

    @Override
    public void addTask(Task task) {

        LOGGER.info("Add Task " + task.toString() + " in database");
        this.sessionFactory.getCurrentSession().save(task);

    }

    @Override
    public void updateTask(Task task) {

        LOGGER.info("Update task: " + task.toString());

        Integer id = task.getId();
        Task taskToUpdate = this.getTaskById(id);
        taskToUpdate = task;
        this.sessionFactory.getCurrentSession().merge(taskToUpdate);

    }
}
