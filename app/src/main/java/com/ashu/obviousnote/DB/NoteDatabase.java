package com.ashu.obviousnote.DB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static final String DB_NAME = "NotesDb";
    private static NoteDatabase instance;
    public abstract NoteDAO noteDAO();

    public synchronized static NoteDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, NoteDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}