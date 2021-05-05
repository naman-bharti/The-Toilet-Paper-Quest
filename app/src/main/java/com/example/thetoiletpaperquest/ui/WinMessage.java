package com.example.thetoiletpaperquest.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.thetoiletpaperquest.R;

// This class represents the congratulations message after winning the game.
public class WinMessage extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v= LayoutInflater.from(getActivity()).inflate(R.layout.finished_popup,null);

        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        };
        return new AlertDialog.Builder(getActivity()).setTitle("Game Over").setView(v).setPositiveButton(android.R.string.ok,listener).create();
    }
}
