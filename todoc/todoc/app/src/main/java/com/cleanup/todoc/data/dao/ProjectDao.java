package com.cleanup.todoc.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.domain.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    //@Query("SELECT * FROM projects WHERE id= :projectId")
    @Query("SELECT * FROM projects")
    //List<Project> getProject(long projectId);
    List<Project>getProjects();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Project... projects);
}
