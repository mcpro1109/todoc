package com.cleanup.todoc.data.database;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.domain.repository.ProjectRepository;
import com.cleanup.todoc.domain.repository.TaskRepository;
import com.cleanup.todoc.ui.MainActivityViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final Executor executor;
    private static ViewModelFactory factory;


    public static ViewModelProvider.Factory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(factory.projectRepository, factory.taskRepository, factory.executor);
                }
            }
        }
        return null;
    }

    public ViewModelFactory(ProjectRepository projectRepository, TaskRepository taskRepository, Executor executor) {
        this.projectRepository =projectRepository;
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //permet de déclarer les viewsmodels
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(projectRepository, taskRepository, executor);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
