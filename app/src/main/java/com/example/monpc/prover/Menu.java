package com.example.monpc.prover;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

/**
 * Created by Boukhetta on 15/03/2017.
 */

public class Menu extends Activity {

    Button start;
    Button information;
    Button about;
    Button exit;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = (Button) findViewById(R.id.start);
        information = (Button) findViewById(R.id.information);
        about = (Button) findViewById(R.id.about);
        exit = (Button) findViewById(R.id.exit);

        TextView tv = (TextView) findViewById(R.id.titre);
        CalligraphyUtils.applyFontToTextView(this, tv, "fonts/ArabicTypesetting-Regular.ttf");


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c =  view.getContext();
                Intent intent = new Intent(c, MainActivity.class);
                c.startActivity(intent);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c =  view.getContext();
                Intent intent = new Intent(c, Information.class);
                c.startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
