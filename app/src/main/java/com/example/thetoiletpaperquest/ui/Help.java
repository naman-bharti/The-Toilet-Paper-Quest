package com.example.thetoiletpaperquest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.thetoiletpaperquest.R;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

// This class represents the Help Activity.
public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView course_link = findViewById(R.id.about_the_authors);
        course_link.setMovementMethod(LinkMovementMethod.getInstance());

        TextView list = findViewById(R.id.resources_list);
        list.setMovementMethod(LinkMovementMethod.getInstance());
    }
}