package com.example.agenda.model.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.agenda.model.dao.AgendaDao;
import com.example.agenda.model.entity.Agenda;

import static com.example.agenda.AgendaApplication.threadExecutor;

@Database(entities = {Agenda.class}, version = 1, exportSchema = false)
public abstract class AgendaDb extends RoomDatabase {

    public abstract AgendaDao agendaDao();

    private static volatile AgendaDb INSTANCE;

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            threadExecutor.execute(() -> {

                AgendaDao dao = INSTANCE.agendaDao();
                dao.deleteAll();

            });
        }
    };

    public static AgendaDb getDatabase(final Context c) {
        if (INSTANCE == null) {
            synchronized (AgendaDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(c.getApplicationContext(),
                            AgendaDb.class, "dbagenda").addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
