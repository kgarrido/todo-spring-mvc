package com.notificationengine.todoapp.web.controller;

import com.notificationengine.todoapp.constants.Constants;
import com.notificationengine.todoapp.domain.CompletedTask;
import com.notificationengine.todoapp.domain.Task;
import com.notificationengine.todoapp.service.CompletedTaskService;
import com.notificationengine.todoapp.service.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController implements Serializable {

    public static Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private CompletedTaskService completedTaskService;

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


    @ModelAttribute(Constants.TASK)
    public Task getTasKObject() {
        return new Task();
    }
}
