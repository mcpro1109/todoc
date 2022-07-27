package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.domain.model.Project;
import com.cleanup.todoc.domain.model.Task;
import com.cleanup.todoc.domain.repository.ProjectRepository;
import com.cleanup.todoc.domain.repository.TaskRepository;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MainActivityViewModel extends ViewModel {

    //fait le lien entre l'activity et le repository, permet de mettre à jour l'affichage des données quand elles sont modifiées

    ProjectRepository projectRepository;
    //TaskRepository taskRepository = TaskRepository.getInstance();
    TaskRepository taskRepository;
    Executor executor;

    @Nullable
    private MutableLiveData<List<Project>> listProjects = new MutableLiveData<>(new ArrayList<>());

    public MainActivityViewModel(ProjectRepository projectRepository, TaskRepository taskRepository, Executor executor) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    //appeler les tasks
    @Nullable
    private final MutableLiveData<List<Task>> listTasks = new MutableLiveData<>(new ArrayList<>());

    /* public LiveData<List<Task>> getTasks() {
         return listTasks;
     }*/
    public LiveData<List<Task>> getListTasks() {
        return (LiveData<List<Task>>) taskRepository.getTasks();
    }

    public void createTask(Task createTask) {
        executor.execute(() -> {
            taskRepository.createTask(createTask);
        });
    }

    public void deleteTask(long taskId) {
        executor.execute(() -> {
            taskRepository.deleteTask(taskId);
        });
    }

    public void fetchTasks() {
        executor.execute(() -> {
            listTasks.setValue(taskRepository.getTasks());
        });
    }

    //projects

    /*  public void initProjects(){
          if (listProjects==null){
              listProjects= (MutableLiveData<List<Project>>) projectRepository.getProjects();
          }
      }*/
    @Nullable
    public LiveData<List<Project>> getProjects() {
        return listProjects;
    }

  /*  public void fetchDataProjects(){
        executor.execute(() ->{
            listProjects.setValue(projectRepository.getProject());
        });
    }*/

    /*private final MutableLiveData<List<Project>> projects = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Project>> getProjects() { return projects; }

    public void fetchData() {
        repository.getProjects().onFinish { projects ->
            liveData.setValue(projects)
        }
    }*/
}