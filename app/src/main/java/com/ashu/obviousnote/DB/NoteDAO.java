package com.ashu.obviousnote.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM Note ORDER BY time desc")
    Flowable<List<Note>> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNote(Note note);

}