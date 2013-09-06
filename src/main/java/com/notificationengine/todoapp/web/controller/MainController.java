package com.notificationengine.todoapp.web.controller;

import com.notificationengine.todoapp.constants.Constants;
import com.notificationengine.todoapp.domain.CompletedTask;
import com.notificationengine.todoapp.domain.Task;
import com.notificationengine.todoapp.service.CompletedTaskService;
import com.notificationengine.todoapp.service.TaskService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
public class MainController implements Serializable {

    public static Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private CompletedTaskService completedTaskService;

    @Autowired
    private Properties localSettingsProperties;

    public MainController() {

        super();

        LOGGER.info("Instantiates MainController");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listTasks() {

        Map<String, Object> myModel = new HashMap<>();

        Collection<Task> tasks = this.taskService.getAllTasks();
        Collection<CompletedTask> completedTasks = this.completedTaskService.getAllTasks();

        myModel.put(Constants.TASKS, tasks);
        myModel.put(Constants.COMPLETED_TASKS, completedTasks);

        return new ModelAndView("index", "model", myModel);

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute(value=Constants.TASK) Task task) {

        this.taskService.addTask(task);

        return "redirect:/";
    }

    @RequestMapping(value = "task-done-{id}", method = RequestMethod.GET)
    public String markTaskAsDone(@PathVariable Integer id) {

        Task task = this.taskService.getTaskById(id);

        this.taskService.removeTask(id);

        CompletedTask completedTask = new CompletedTask(task.getName());

        this.completedTaskService.addCompletedTask(completedTask);

        return "redirect:/";

    }

    @RequestMapping(value = "task-delete-{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Integer id) {

        this.taskService.removeTask(id);

        return "redirect:/";

    }

    @RequestMapping(value = "completed-task-delete-{id}", method = RequestMethod.GET)
    public String deleteCompletedTask(@PathVariable Integer id) {

        this.completedTaskService.removeCompletedTask(id);

        return "redirect:/";

    }

    @RequestMapping(value = "export-list", method = RequestMethod.GET)
    public String exportTaskList() {

        Collection<Task> tasks = this.taskService.getAllTasks();

        try {

            String filePath = this.localSettingsProperties.getProperty(Constants.TASKS_FILE);

            FileUtils.writeLines(new File(filePath), tasks);
        }
        catch(IOException ex) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
        }

        return "redirect:/";

    }

    @RequestMapping(value = "do-push", method = RequestMethod.GET)
    public String pushOnNotificationEngine() {

        JSONObject toBeSent = new JSONObject();

        toBeSent.put(Constants.TOPIC, "facturation.societe2");

        JSONObject context = new JSONObject();
        context.put(Constants.SUBJECT, "Notification from TodoApp");
        context.put(Constants.CONTENT, "This notification has been sent directly from the TodoApp");

        toBeSent.put(Constants.CONTEXT, context);

        String stringToPost = toBeSent.toString();

        final StringBuilder sb = new StringBuilder(this.localSettingsProperties.getProperty(Constants.SERVER_URL));
        sb.append(Constants.RAW_NOTIFICATION);

        String url = sb.toString();

        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(url);

        try {
            StringEntity params = new StringEntity(stringToPost);

            post.setEntity(params);

            post.addHeader("Content-Type", "application/json");

            HttpResponse response = client.execute(post);

            LOGGER.info("Notification sent : " + stringToPost);
        }
        catch(Exception ex) {

            LOGGER.error(ExceptionUtils.getFullStackTrace(ex));

            LOGGER.info("Notification not sent : " + stringToPost);
        }

        return "redirect:/";
    }


    @ModelAttribute(Constants.TASK)
    public Task getTasKObject() {
        return new Task();
    }
}
