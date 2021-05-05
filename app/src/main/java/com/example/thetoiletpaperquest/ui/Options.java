package com.example.thetoiletpaperquest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.thetoiletpaperquest.R;
import com.example.thetoiletpaperquest.model.storeManager;

// This class represents the Options Activity.
public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createRadioSize();
        createRadioTP();
    }

    private void createRadioSize() {
        RadioGroup group = findViewById(R.id.board_size);
        int[] rows = getResources().getIntArray(R.array.get_rows);
        int[] columns = getResources().getIntArray(R.array.get_columns);

        for (int i = 0; i < rows.length; i++) {
            final int row = rows[i];
            final int column = columns[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.cross, row, column));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeManager.getInstance().setNumberOfColumns(column);
                    storeManager.getInstance().setNumberOfRows(row);
                }
            });
            group.addView(button);
        }
    }

    private void createRadioTP() {
        RadioGroup group = findViewById(R.id.showTP);
        int[] num = getResources().getIntArray(R.array.get_TP);

        for (int i = 0; i < num.length; i++) {
            final int number = num[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.blank, number));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeManager.getInstance().setNumberOfStoresWithTPs(number);
                }
            });
            group.addView(button);
        }
    }

}