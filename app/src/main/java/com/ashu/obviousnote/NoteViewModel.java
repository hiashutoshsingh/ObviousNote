package com.ashu.obviousnote;

import android.arch.lifecycle.ViewModel;

import androidx.lifecycle.ViewModel;

import com.ashu.obviousnote.DB.Note;
import com.example.hassanusman.things2do.DataSource;
import com.example.hassanusman.things2do.presistence.model.Task;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public class NoteViewModel extends ViewModel {

    private DataSource dataSource;

    public NoteViewModel(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public Flowable<List<Note>> getAllTasks(){
        return dataSource.getAll();
    }

    public Flowable<Note> getTask(String id){
        return dataSource.get(id);
    }

    public Completable insertTask(Note note){
        return Completable.fromAction(()->{
           dataSource.insert(task);
        });
    }

    public Completable delete(Note note){
        return Completable.fromAction(()->{
            dataSource.delete(task);
        });
    }

    public Completable update(Note note){
        return Completable.fromAction(()->{
           dataSource.update(task);
        });
    }
}
