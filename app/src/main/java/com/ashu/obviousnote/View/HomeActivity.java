package com.ashu.obviousnote.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ashu.obviousnote.DB.Note;
import com.ashu.obviousnote.DB.NoteDatabase;
import com.ashu.obviousnote.R;
import com.ashu.obviousnote.Utils.CommonMethods;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private Button add;
    private RecyclerView rvNotes;
    private NoteDatabase noteDatabase;
    private List<Note> noteList = new ArrayList<>();
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        rvNotes = findViewById(R.id.rvNotes);
        noteDatabase = NoteDatabase.getInstance(this);
        readData();

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(noteList, ratingModel -> {
            showNote(ratingModel);
        });
        rvNotes.setAdapter(notesAdapter);
        add.setOnClickListener(this);
    }

    private void showNote(Note ratingModel) {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setDimAmount(0.5f);
        dialog.setContentView(R.layout.abc_single_show_note);
        TextView title = dialog.findViewById(R.id.task_name);
        TextView task_details = dialog.findViewById(R.id.task_details);
        TextView task_time = dialog.findViewById(R.id.task_time);
        title.setText(ratingModel.getNote());
        task_details.setText(ratingModel.getDetails());
        task_time.setText(CommonMethods.getFormattedDateString(ratingModel.getTime()));
        dialog.show();
    }


    private void readData() {
        noteDatabase.noteDAO().getAllNotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(ratings -> {
                    noteList.clear();
                    noteList.addAll(ratings);
                    notesAdapter.notifyDataSetChanged();
                }, e -> Log.e(TAG, "error: " + e.getMessage()));
    }

    void createAddTaskDialog(View view) {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setDimAmount(0.5f);
        dialog.setContentView(R.layout.abc_dialog_layout);
        EditText title = dialog.findViewById(R.id.task_title);
        EditText details = dialog.findViewById(R.id.task_details);
        Button add = dialog.findViewById(R.id.add);
        dialog.show();
        add.setOnClickListener((v) -> {
            if (title.getText().toString().equals("") || details.getText().toString().equals("")) {
                Snackbar.make(view, "Please Add Note", Snackbar.LENGTH_SHORT).show();
                return;
            }
            Note note = new Note(title.getText().toString().trim(), details.getText().toString().trim());
            note.setNote(title.getText().toString().trim());
            note.setDetails(details.getText().toString().trim());
            note.setTime(CommonMethods.getCurrentDateTime());
            noteDatabase.noteDAO().insertNote(note);
            notesAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                createAddTaskDialog(v);
                break;
        }
    }
}
