package com.cleanup.todoc.domain.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.TodocApp;
import com.cleanup.todoc.data.dao.ProjectDao;
import com.cleanup.todoc.data.database.TodocDatabase;
import com.cleanup.todoc.domain.model.Project;

import java.util.List;

public class ProjectRepository {

    private final ProjectDao projectDao = TodocDatabase.getInstance(TodocApp.app).projectDao();

    private static ProjectRepository instance;

    public static ProjectRepository getInstance() {
        if(instance == null) instance = new ProjectRepository();
        return instance;
    }

    public LiveData< List<Project>> getProjects(){ return projectDao.getProjects(); }
}
