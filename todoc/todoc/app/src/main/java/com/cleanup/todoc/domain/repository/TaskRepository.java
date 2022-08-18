package com.cleanup.todoc.domain.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.TodocApp;
import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.data.database.TodocDatabase;
import com.cleanup.todoc.domain.model.Task;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao = TodocDatabase.getInstance(TodocApp.app).taskDao();

    private static TaskRepository instance;

    public static TaskRepository getInstance() {
        if(instance == null) instance = new TaskRepository();
        return instance;
    }

    //get
    public LiveData< List<Task>> getTasks() {
        return taskDao.getTasks();
    }

    //create
    public void createTask(Task task){
        taskDao.insertTask(task);
    }

    //delete
    public void deleteTask(long taskId){
        taskDao.deleteTask(taskId);
    }

}
