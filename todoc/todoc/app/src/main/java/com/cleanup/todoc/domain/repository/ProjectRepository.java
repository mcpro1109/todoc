package com.cleanup.todoc.domain.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.data.dao.ProjectDao;
import com.cleanup.todoc.domain.model.Project;

import java.util.List;

public class ProjectRepository {
    //fait le lien entre le viewmodel et la base de données Project
   /* private static final ProjectRepository instance = new ProjectRepository(getInstance().projectDao);

    public static ProjectRepository getInstance() {
        return instance;
    }*/

    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }


    //récupérer la liste des projects
    //public List<Project> getProject(long projectId){return projectDao.getProject(projectId);}
    public LiveData<List<Project>> getProjects(){return projectDao.getProjects();}


}
