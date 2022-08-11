package com.cleanup.todoc.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.domain.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    //créer instance avec room
    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    //permet de récupérer la liste
    @Query("SELECT * FROM tasks WHERE project_id = :projectId ")
    List<Task> getTasksWithProjectId(long projectId);

    //ajouter dans la liste
    @Insert
    long insertTask(Task task);

    //supprimer
    @Query("DELETE FROM tasks WHERE id= :taskId")
    int deleteTask(long taskId);
   /* @Delete
    void deleteTask(Task task);*/
}
