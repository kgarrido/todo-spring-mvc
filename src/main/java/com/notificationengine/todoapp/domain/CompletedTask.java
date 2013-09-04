package com.notificationengine.todoapp.domain;


import org.apache.log4j.Logger;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class CompletedTask {

    public static Logger LOGGER = Logger.getLogger(CompletedTask.class);

    private Integer id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    public CompletedTask() {
        this.completedAt = new Date();
    }

    public CompletedTask(String name) {
        this.name = name;
        this.completedAt = new Date();
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

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompletedTask{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", completedAt=").append(completedAt);
        sb.append('}');
        return sb.toString();
    }
}
