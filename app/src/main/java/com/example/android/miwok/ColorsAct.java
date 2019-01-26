package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsAct extends AppCompatActivity {
    // colors Activity test

    private MediaPlayer md;
    private MediaPlayer.OnCompletionListener WhenDone= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Cleaner();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    md.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    Cleaner();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    md.pause();
                    md.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    md.pause();
                    md.seekTo(0);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> numbers=new ArrayList<Word>();
        numbers.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        numbers.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        numbers.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        numbers.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        numbers.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        numbers.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        numbers.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        numbers.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        final  WordAdapter adapter= new WordAdapter(this,numbers,R.color.category_colors);
        final ListView listView =(ListView) findViewById(R.id.List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=numbers.get(position);
                Cleaner();
                int result= audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    md = MediaPlayer.create(ColorsAct.this, word.GetAudioId());
                    md.start();
                    md.setOnCompletionListener(WhenDone);
                }
            }
        });
    }
    private void Cleaner()
    {
        if(md!=null)
        {
            md.release();
            md=null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        Cleaner();
    }
}
