package com.cleanup.todoc.DaoTests;

import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.LiveDataTestUtils;
import com.cleanup.todoc.data.database.TodocDatabase;
import com.cleanup.todoc.domain.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class ProjectsDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TodocDatabase database;
    private Project[] projects= Project.getAllProjects();
    LiveData<List<Project>> projectList= (LiveData<List<Project>>) database.projectDao().getProjects();

    @Before
    public void initDb() throws Exception{
        this.database= Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(), TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception{
        database.close();
    }

@Test
    public void getProjectsTest() throws InterruptedException{

   Project projects= (Project) LiveDataTestUtils.getValue(this.projectList);
   assertTrue(projects.getName().isEmpty());
   this.database.projectDao().insertAll();

}













}
