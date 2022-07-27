package com.cleanup.todoc.domain.repository;

import android.app.Application;

import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.data.database.TodocDatabase;
import com.cleanup.todoc.domain.model.Task;

import java.util.List;

public class TaskRepository {
    //fait le lien entre le viewmodel et la base de données Task

//créer une instance
    //private static final TaskRepository instance = new TaskRepository(TaskDao.getInstance());

   /*public static TaskRepository getInstance() {
        return instance;
    }*/


    private TaskDao taskDao;
    private List<Task> allTasks;

  /*  private TaskRepository(TaskDao taskDao) {

        this.taskDao = taskDao;

    }*/
    private TaskRepository(Application application){
        TodocDatabase database= TodocDatabase.getInstance(application);
        taskDao=database.taskDao();
        allTasks=taskDao.getAll();
    }

    //get
    public List<Task> getTasks() {
        return this.taskDao.getAll();
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
