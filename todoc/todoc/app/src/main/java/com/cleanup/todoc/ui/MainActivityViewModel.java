package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.domain.model.Task;
import com.cleanup.todoc.domain.repository.ProjectRepository;
import com.cleanup.todoc.domain.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MainActivityViewModel extends ViewModel {
    //fait le lien entre l'activity et le repository, permet de mettre à jour l'affichage des données quand elles sont modifiées

    ProjectRepository projectRepository = ProjectRepository.getInstance();
    TaskRepository taskRepository = TaskRepository.getInstance();
    private final Executor executor;


    public MainActivityViewModel(ProjectRepository projectRepository, TaskRepository taskRepository, Executor executor) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    //appeler les tasks
    private final MutableLiveData<List<Task>> tasks = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void createTask(long id, long projectId, String name, long creationTimestamp) {
        executor.execute(() -> {
            taskRepository.createTask(new Task(id, projectId, name, creationTimestamp));
        });
    }

    public void deleteTask(long taskId) {
        executor.execute(() -> {
            taskRepository.deleteTask(taskId);
        });
    }

public  void fetchTasks(){
        executor.execute(() -> {
            tasks.setValue(taskRepository.getTasks());
        });
}


    //projects
  /*  public LiveData<Project> getProjects() {
        return this.allProjects;
    }*/



    /*private final MutableLiveData<List<Project>> projects = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Project>> getProjects() { return projects; }

    public void fetchData() {
        repository.getProjects().onFinish { projects ->
            liveData.setValue(projects)
        }
    }*/
}