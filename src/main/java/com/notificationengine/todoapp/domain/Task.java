package com.notificationengine.todoapp.domain;


import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

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

        LocalDate jodaCreatedAt = new LocalDate(this.createdAt.getTime());

        final StringBuilder sb = new StringBuilder("Task: ");
        sb.append(name);
        sb.append(" created on ").append(jodaCreatedAt.toString());
        sb.append("\n");
        return sb.toString();
    }
}
