package com.cleanup.todoc.domain.repository;

import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.domain.model.Task;

import java.util.List;

public class TaskRepository {
    //fait le lien entre le viewmodel et la base de données Task

//créer une instance
    private static final TaskRepository instance = new TaskRepository(TaskDao.getInstance());

   public static TaskRepository getInstance() {
        return instance;
    }

    private final TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
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
        taskDao.delete(taskId);
    }


   /* private MutableLiveData<List<Task>> liveDataTask = new MutableLiveData<>();
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        TodocDatabase db;
        db = TodocDatabase.getInstance(application);
        taskDao = db.taskDao();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    //récupérer la liste
    public void getTask(Task task) {
        new getTask(taskDao).execute(task);
    }


    //insérer dans la liste
    public void insertTask(Task task) {
        new insertTask(taskDao).execute(task);
    }

    //supprimer de la liste
    public void deleteTask(Task task) {
        new deleteTask(taskDao).execute(task);
    }

    private static class getTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;
        private getTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.getTasks(tasks[0]);
            return null;
        }
    }

    private static class insertTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;
        private insertTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insertTask(tasks[0]);
            return null;
        }
    }

    private class deleteTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;
        private deleteTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }*/

}
