package com.cleanup.todoc.data.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.data.dao.ProjectDao;
import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.domain.model.Project;
import com.cleanup.todoc.domain.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    //Singleton : permet d'avoir une seule instance pour modifier la database
    private static volatile TodocDatabase INSTANCE;

    //DAO
    public abstract ProjectDao projectDao();
    

    public abstract TaskDao taskDao();


    //Instance
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodocDatabase.class, "MyDadabase.db")
                            //.addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

   /* private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

        };


    }*/
}
