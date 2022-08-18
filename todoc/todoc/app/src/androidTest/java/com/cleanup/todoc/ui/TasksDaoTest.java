package com.cleanup.todoc.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.data.database.TodocDatabase;
import com.cleanup.todoc.domain.model.Project;
import com.cleanup.todoc.domain.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TasksDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TodocDatabase database;
    private Project[] projects=Project.getAllProjects();
    private Task testTask = new Task(45, projects[1].getId(), "laver les vitres", new Date().getTime());

    @Before
    public void before() {
        TodocDatabase.isMemory=true;
        database = TodocDatabase.getInstance(InstrumentationRegistry.getContext());
    }

    @After
    public void after(){
        database.taskDao().deleteTask(testTask.getId());
    }

    @Test
    public void taskIsNotEmpty() throws Exception {
        //car test après addAndRemoveTask test
        List<Task> tasks = LiveDataTestUtil.getValue(database.taskDao().getTasks());
        assertFalse(tasks.isEmpty());
    }

    @Test
    public void insertTask() throws Exception {
        database.taskDao().insertTask(testTask);
        List<Task> tasks = LiveDataTestUtil.getValue(database.taskDao().getTasks());
        assertTrue(tasks.size()==4);
    }

    @Test
    public void deleteTask() throws Exception {
        database.taskDao().insertTask(testTask);
        database.taskDao().deleteTask(testTask.getId());
        List<Task> tasks = LiveDataTestUtil.getValue(database.taskDao().getTasks());
        assertTrue(tasks.size()==3);
    }
}

