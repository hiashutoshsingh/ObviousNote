package com.ashu.obviousnote.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ashu.obviousnote.Utils.TimeStampConverter;

import java.util.Date;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "note")
    private String note;
    @ColumnInfo(name = "details")
    private String details;
    @ColumnInfo(name = "time")
    @TypeConverters({TimeStampConverter.class})
    public Date time;

    public Note(String note, String details) {
        this.note = note;
        this.details = details;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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