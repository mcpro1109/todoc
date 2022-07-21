package com.cleanup.todoc.domain.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.data.dao.ProjectDao;
import com.cleanup.todoc.domain.model.Project;

public class ProjectRepository {
    //fait le lien entre le viewmodel et la base de données Project
    private static final ProjectRepository instance = new ProjectRepository(getInstance().projectDao);

    public static ProjectRepository getInstance() {
        return instance;
    }

    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }


    //récupérer la liste des projects
    public LiveData<Project> getProjects(long projectId){return this.projectDao.getProject(projectId);}



 /*   private MutableLiveData<List<Project>> liveDataProject = new MutableLiveData<>();
    private LiveData<List<Project>> allProjects;

    public ProjectRepository(Application application) {
        TodocDatabase db;
        db = TodocDatabase.getInstance(application);
        projectDao = db.projectDao();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public void getProject(Project project) {
        new InsertProject(projectDao).execute(project);
    }

    private static class InsertProject extends AsyncTask<Project, Void, Void> {
        private ProjectDao projectDao;
        private InsertProject(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }
        @Override
        protected Void doInBackground(Project... projects) {
            projectDao.getProject(projects[0]);
            return null;
        }
    }*/
}
