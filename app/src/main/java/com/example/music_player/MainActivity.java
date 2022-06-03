package com.example.music_player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPause , btnPlay , btnStop ;
        btnPause =  findViewById(R.id.btnPause);
        btnPlay =  findViewById(R.id.btnPlay);
        btnStop =  findViewById(R.id.btnStop);

        //class for audio play
        MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);// different types of audio players are there in phone

        String audioPath = "android.resource://"+getPackageName()+"/raw/cat";

        Uri audioURI = Uri.parse(audioPath);
        try {
            mp.setDataSource(this,audioURI);  // to avoid runtime crashing
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mp.prepare(); // prepare audio file before starting
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mp.pause();

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mp.start();

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mp.pause();// reference will be released if we use stop() thus cant directly stop mp .
                mp.seekTo(0);

            }
        });
    }
}