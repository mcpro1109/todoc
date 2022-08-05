package com.cleanup.todoc.DaoTests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.LiveDataTestUtils;
import com.cleanup.todoc.data.database.TodocDatabase;
import com.cleanup.todoc.domain.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class TasksDaoTests {
    private TodocDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    LiveData<List<Task>> taskList= (LiveData<List<Task>>) database.taskDao().getAll();

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

private static Task cleanTask= new Task(3, 1 , "nettoyer la chambre", 22);

    @Test
    public void listIsEmpty() throws InterruptedException{
       Task tasks= (Task) LiveDataTestUtils.getValue(this.taskList);
        assertTrue(tasks.getName().isEmpty());
    }

    @Test
    public void createTask() throws InterruptedException{
        this.database.taskDao().insertTask(cleanTask);
        Task tasks= (Task) LiveDataTestUtils.getValue(this.taskList);
        assertEquals(1,0);
    }

    @Test
    public void deleteTask() throws  InterruptedException{
        this.database.taskDao().insertTask(cleanTask);
        Task tasks= (Task) LiveDataTestUtils.getValue(this.taskList);
        this.database.taskDao().deleteTask(tasks.getId());

        assertTrue(tasks.getName().isEmpty());

    }

}
