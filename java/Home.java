package com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Home extends AppCompatActivity {
    ImageView image;
    ImageButton btn_play,btn_pause,btn_next,btn_pre;
    TextView songName,Singer,txt_start_time,txt_end_time;
    MediaPlayer mediaPlayer;
    SeekBar music_progress;
    Runnable runnable;
    private Handler handler = new Handler();
    int currentIndex = 0;
    int oTime =0, sTime =0, eTime =0, fTime = 5000, bTime = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //initilize

        btn_play = findViewById(R.id.btn_play);
        btn_next = findViewById(R.id.btn_next);
        btn_pre = findViewById(R.id.btn_pre);

        songName = findViewById(R.id.SongName);
        Singer = findViewById(R.id.singerName);
        image = findViewById(R.id.imageSwitcher);
        music_progress = (SeekBar)findViewById(R.id.music_progress);
        //song storage & images

        ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0,R.raw.aarumkaanaathinnen);
        songs.add(1,R.raw.bodyguard_machilammakkucha);
        songs.add(2,R.raw.bodyguardenneyanoatho);
        songs.add(3,R.raw.bodyguard_qarikathayaro);
        songs.add(4,R.raw.joseph_poomuthole);
        songs.add(5,R.raw.kozhi_chinkara);
        songs.add(6,R.raw.luca_neeyillaneram);
        songs.add(7,R.raw.oru_adara_love_forever_friend);

        mediaPlayer =MediaPlayer.create(getApplicationContext(),songs.get(currentIndex));
        mediaPlayer.start();
        songTexts();

    btn_play.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                btn_play.setImageResource(R.drawable.play);
            }else{
                mediaPlayer.start();
                btn_play.setImageResource(R.drawable.pause);
            }
            songTexts();

            if(oTime == 0){
                music_progress.setMax(eTime);
                oTime = 1;
            }
            music_progress.setProgress(sTime);
            handler.postDelayed(UpdateSongTime, 100);
        }
    });
    btn_next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mediaPlayer!=null){
                btn_play.setImageResource(R.drawable.pause);
            }
            if(currentIndex < songs.size()-1){
                currentIndex++;
            }else {
                currentIndex = 0;
            }
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer =MediaPlayer.create(getApplicationContext(),songs.get(currentIndex));
            mediaPlayer.start();
            songTexts();
        }
    });
    btn_pre.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mediaPlayer!=null){
                btn_play.setImageResource(R.drawable.play);
            }
            if(currentIndex > 0 ){
                currentIndex--;
            }else {
                currentIndex = songs.size()-1;
            }
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer =MediaPlayer.create(getApplicationContext(),songs.get(currentIndex));
            mediaPlayer.start();
            songTexts();
        }
    });
    }
    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mediaPlayer.getCurrentPosition();
//            start_time.setText(String.format("%d min, %d sec",
//                    TimeUnit.MILLISECONDS.toMinutes(sTime),
//                    TimeUnit.MILLISECONDS.toSeconds(sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
            music_progress.setProgress(sTime);
            handler.postDelayed(this, 100);
        }
    };
    private void songTexts() {
       if(currentIndex == 0){
           songName.setText("first Song first Song");
           Singer.setText("first Singer");
           image.setImageResource(R.drawable.first);
       }
        if(currentIndex == 1){
            songName.setText("second Song second Song");
            Singer.setText("second Singer");
            image.setImageResource(R.drawable.second);
        }
        if(currentIndex == 2){
            songName.setText("third Song third Song");
            Singer.setText("third Singer");
            image.setImageResource(R.drawable.third);
        }
        if(currentIndex == 3){
            songName.setText("Fourth Song first Song");
            Singer.setText("Fourth Singer");
            image.setImageResource(R.drawable.four);
        }
        if(currentIndex == 4){
            songName.setText("Fifth Song second Song");
            Singer.setText("Fifth Singer");
            image.setImageResource(R.drawable.five);
        }
        if(currentIndex == 5){
            songName.setText("Sixth Song third Song");
            Singer.setText("Sixth Singer");
            image.setImageResource(R.drawable.six);
        }
        if(currentIndex == 6){
            songName.setText("Seventh Song second Song");
            Singer.setText("Seventh Singer");
            image.setImageResource(R.drawable.seven);
        }
        if(currentIndex == 7){
            songName.setText("Eight Song third Song");
            Singer.setText("Eight Singer");
            image.setImageResource(R.drawable.five);
        }
    }

}