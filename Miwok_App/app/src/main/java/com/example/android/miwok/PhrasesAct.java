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

public class PhrasesAct extends AppCompatActivity {
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
        numbers.add(new Word("Where are you\n" +
                "going?","minto wuksus",R.raw.phrase_where_are_you_going));
        numbers.add(new Word("What is your\n" +
                "name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        numbers.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        numbers.add(new Word("How are you\n" +
                "feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        numbers.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        numbers.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        numbers.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        numbers.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        numbers.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        numbers.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        final WordAdapter adapter= new WordAdapter(this,numbers,R.color.category_phrases);
        final ListView listView =(ListView) findViewById(R.id.List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=numbers.get(position);
                Cleaner();
                int result= audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    md = MediaPlayer.create(PhrasesAct.this, word.GetAudioId());
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
