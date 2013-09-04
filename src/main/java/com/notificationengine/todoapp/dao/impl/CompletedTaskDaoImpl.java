package com.notificationengine.todoapp.dao.impl;

import com.notificationengine.todoapp.dao.CompletedTaskDao;
import com.notificationengine.todoapp.domain.CompletedTask;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CompletedTaskDaoImpl implements CompletedTaskDao{

    public static Logger LOGGER = Logger.getLogger(CompletedTaskDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {

        LOGGER.info("Set session factory");
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CompletedTask getCompletedTaskById(Integer id) {
        LOGGER.info("Get Task with id: " + id);

        return (CompletedTask) this.sessionFactory.getCurrentSession()
                .createQuery("FROM CompletedTask completedTask WHERE completedTask.id = ?")
                .setParameter(0, id)
                .uniqueResult();
    }

    @Override
    public Collection<CompletedTask> getAllCompletedTasks() {

        LOGGER.info("Retrieve all Tasks");

        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM CompletedTask completedTask")
                .list();
    }

    @Override
    public void removeCompletedTask(Integer id) {

        LOGGER.info("Remove Task with id: " + id);
        CompletedTask completedTask = this.getCompletedTaskById(id);
        this.sessionFactory.getCurrentSession().delete(completedTask);
    }

    @Override
    public void addCompletedTask(CompletedTask completedTask) {

        LOGGER.info("Add Task " + completedTask.toString() + " in database");
        this.sessionFactory.getCurrentSession().save(completedTask);
    }

    @Override
    public void updateCompletedTask(CompletedTask completedTask) {

        LOGGER.info("Update task: " + completedTask.toString());

        Integer id = completedTask.getId();
        CompletedTask completedTaskToUpdate = this.getCompletedTaskById(id);
        completedTaskToUpdate = completedTask;
        this.sessionFactory.getCurrentSession().merge(completedTaskToUpdate);
    }
}
