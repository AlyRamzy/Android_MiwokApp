package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aly on 4/14/2018.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int  actColourId ;
    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects,int colourId) {
        super(context, 0, objects);
        actColourId=colourId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ListItemView = convertView;
        if(ListItemView==null){//for making new List_item if there is no main one to change its data
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Word CurrentWord=getItem(position);// getting the current word in the arraylist
        ImageView imageView = (ImageView) ListItemView.findViewById(R.id.image);
        if(CurrentWord.hasImage()) {// special case phrases Activity (Image View)

            imageView.setImageResource(CurrentWord.GetId());
            imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            imageView.setVisibility(View.GONE);

        }
        /////////////////////////////////// Edidting The Text
        TextView EnglishTextView =(TextView) ListItemView.findViewById(R.id.english);
        EnglishTextView.setText(CurrentWord.GetEnglish());
        TextView MioukTextView =(TextView) ListItemView.findViewById(R.id.miowk);
        MioukTextView.setText(CurrentWord.GetMiowk());
        ///////////////////////////////////////////////////////
        View TextViewContainer = ListItemView.findViewById(R.id.Text_Container);// Finding The Vertical LinearLayout
        int color= ContextCompat.getColor(getContext(),actColourId);// Finding The Color With This Id
         TextViewContainer.setBackgroundColor(color);

        return ListItemView;
    }
}
