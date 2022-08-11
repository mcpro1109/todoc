package com.cleanup.todoc.data.database;


import android.content.Context;
import android.util.Log;

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
                    Log.d("app", "databasecreate1");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodocDatabase.class, "MyDadabase.db")
                            .addCallback(prepopulateDatabase(context))
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase(Context context) {
        return new Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                //pour mettre les projects dans la database
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).projectDao().insertAll(Project.getAllProjects());
                    }
                });
            }

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };
    }

}
