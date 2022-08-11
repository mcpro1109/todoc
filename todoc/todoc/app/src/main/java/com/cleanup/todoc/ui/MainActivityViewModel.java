package com.cleanup.todoc.ui;

import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.domain.model.Project;
import com.cleanup.todoc.domain.model.Task;
import com.cleanup.todoc.domain.repository.ProjectRepository;
import com.cleanup.todoc.domain.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    //fait le lien entre l'activity et le repository, permet de mettre à jour l'affichage des données quand elles sont modifiées

    ProjectRepository projectRepository = ProjectRepository.getInstance();
    TaskRepository taskRepository = TaskRepository.getInstance();

    private final MutableLiveData<List<Task>> listTasks = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Task>> getTasksLiveData() {
        return listTasks;
    }

    private final MutableLiveData<List<Project>> listProject = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Project>> getProjectsLiveData() {
        return listProject;
    }

    public void createTask(Task createTask) {
        AsyncTask.execute(() -> {
            taskRepository.createTask(createTask);
            ArrayList<Task> tasks = (ArrayList<Task>) listTasks.getValue();
            tasks.add(createTask);
            listTasks.postValue(tasks);
        });
    }

   public void deleteTask(long task) {
       AsyncTask.execute(() -> {
           taskRepository.deleteTask(task);
           refreshTasks();

           //getTasksLiveData();
       });
   }

    public void refreshTasks() {
        AsyncTask.execute(() -> {
            listTasks.postValue(taskRepository.getTasks());
        });
    }


    /*public void refreshProjects() {
        AsyncTask.execute(() -> {
            listProject.postValue(projectRepository.getProjects());
        });
    }*/

    @Nullable
    public LiveData<List<Project>> getProjects() {
        return projectRepository.getProjects();
    }
}