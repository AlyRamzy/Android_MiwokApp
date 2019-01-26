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

public class FamilyAct extends AppCompatActivity {

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
        numbers.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        numbers.add(new Word("mather","әṭa",R.drawable.family_mother,R.raw.family_mother));
        numbers.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        numbers.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        numbers.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        numbers.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        numbers.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        numbers.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        numbers.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        numbers.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter adapter= new WordAdapter(this,numbers,R.color.category_family);
        ListView listView =(ListView) findViewById(R.id.List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word=numbers.get(position);
                Cleaner();
                int result= audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    md = MediaPlayer.create(FamilyAct.this, word.GetAudioId());
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
