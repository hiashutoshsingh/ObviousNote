package com.ashu.obviousnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    View snackView;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);


        add.setOnClickListener(this);
    }

    void createAddTaskDialog() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setDimAmount(0.5f);
        dialog.setContentView(R.layout.abc_dialog_layout);
        EditText title = dialog.findViewById(R.id.task_title);
        Button add = dialog.findViewById(R.id.add);
        dialog.show();

        add.setOnClickListener((view) -> {
            if (title.getText().toString().equals("")) {
                Snackbar.make(snackView, "Please Add Note", Snackbar.LENGTH_SHORT).show();
                return;
            }

        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                createAddTaskDialog();
                break;
        }
    }
}
