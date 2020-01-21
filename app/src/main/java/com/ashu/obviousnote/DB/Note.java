package com.ashu.obviousnote.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "note")
    private String note;
    @ColumnInfo(name = "time")
    public Date time;

    public Note(String note) {
        this.note = note;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}