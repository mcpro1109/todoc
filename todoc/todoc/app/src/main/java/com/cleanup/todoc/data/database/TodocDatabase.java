package com.cleanup.todoc.data.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.data.dao.ProjectDao;
import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.domain.model.Project;
import com.cleanup.todoc.domain.model.Task;

import java.util.concurrent.Executors;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    public static boolean isMemory = false;
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
                    if (isMemory) {
                        INSTANCE = Room.inMemoryDatabaseBuilder(context, TodocDatabase.class)
                                .allowMainThreadQueries()
                                .build();
                        INSTANCE.clearAllTables();
                        INSTANCE.projectDao().insertAll(Project.getAllProjects());
                    } else {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodocDatabase.class, "MyDadabase.db")
                                .addCallback(prepopulateDatabase(INSTANCE, context))
                                .build();
                    }
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase(TodocDatabase database, Context context) {
        return new Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                //pour mettre les projects dans la database
                Executors.newSingleThreadScheduledExecutor().execute(() -> database.projectDao().insertAll(Project.getAllProjects()));
            }

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };
    }

}
