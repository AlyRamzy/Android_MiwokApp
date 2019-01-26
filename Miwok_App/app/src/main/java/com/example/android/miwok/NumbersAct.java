package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersAct extends AppCompatActivity {
    MediaPlayer md;
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
        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("One", "lutti", R.drawable.number_one, R.raw.number_one));
        numbers.add(new Word("Two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("Ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));


        final WordAdapter adapter = new WordAdapter(this, numbers, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numbers.get(position);
                Cleaner();
                int result= audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    md = MediaPlayer.create(NumbersAct.this, word.GetAudioId());
                    md.setOnCompletionListener(WhenDone);
                    md.start();
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
