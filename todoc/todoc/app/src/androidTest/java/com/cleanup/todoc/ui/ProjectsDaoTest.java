package com.cleanup.todoc.ui;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.data.database.TodocDatabase;
import com.cleanup.todoc.domain.model.Project;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectsDaoTest {

    private TodocDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() {
        TodocDatabase.isMemory = true;
        database = TodocDatabase.getInstance(InstrumentationRegistry.getContext());
    }

    @Test
    public void insertProjects() throws Exception {
        List<Project> projects = LiveDataTestUtil.getValue(database.projectDao().getProjects());
        assertEquals(Project.getAllProjects().length, projects.size());
    }
}

