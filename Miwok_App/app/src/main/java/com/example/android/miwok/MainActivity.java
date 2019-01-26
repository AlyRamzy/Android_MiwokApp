/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        /////Dealing with Number //////////
        TextView number= (TextView) findViewById(R.id.numbers);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numberAct= new Intent(MainActivity.this,NumbersAct.class);
                startActivity(numberAct);

            }
        });
        /////Dealing with Number //////////
        TextView family= (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent FamilyAct= new Intent(MainActivity.this,FamilyAct.class);
                startActivity(FamilyAct);

            }
        });
        /////Dealing with Number //////////
        TextView color= (TextView) findViewById(R.id.colors);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorAct= new Intent(MainActivity.this,ColorsAct.class);
                startActivity(colorAct);

            }
        });
        /////Dealing with Number //////////
        TextView phrase= (TextView) findViewById(R.id.phrases);
        phrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrasesAct= new Intent(MainActivity.this,PhrasesAct.class);
                startActivity(phrasesAct);

            }
        });
    }

}
