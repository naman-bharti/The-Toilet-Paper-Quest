package com.example.thetoiletpaperquest.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thetoiletpaperquest.R;
import com.example.thetoiletpaperquest.model.storeManager;

// This class represents the Main Menu Activity.
public class MainActivity extends AppCompatActivity {
    public static MediaPlayer SONG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        storeManager.getInstance(4,6,6);

        SONG=MediaPlayer.create(MainActivity.this,R.raw.game_music);
        SONG.setLooping(true);
        SONG.setVolume((float)0.1,(float)0.1);
        SONG.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playButton = findViewById(R.id.mm_play);
        playButton.setBackgroundResource(R.drawable.play_button);
        playButton.setPadding(0,0,0,0);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GameScreen.class);
                startActivity(intent);
            }
        });

        Button optionsButton = findViewById(R.id.mm_options);
        optionsButton.setBackgroundResource(R.drawable.options_button);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Options.class);
                startActivity(intent);
            }
        });

        Button helpButton = findViewById(R.id.mm_help);
        helpButton.setBackgroundResource(R.drawable.help_button);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Help.class);
                startActivity(intent);
            }
        });
        Button musicButton=findViewById(R.id.music);
        musicButton.setPadding(0,0,0,0);
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SONG.isPlaying()){
                    SONG.pause();
                }
                else{
                    SONG.start();
                }
            }
        });
    }
}