package com.notificationengine.todoapp.domain;


import org.apache.log4j.Logger;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    public static Logger LOGGER = Logger.getLogger(Task.class);

    private Integer id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Task() {
        super();

        this.createdAt = new Date();
    }

    public Task(String name) {
        super();

        this.name = name;

        this.createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
