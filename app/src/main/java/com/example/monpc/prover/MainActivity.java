package com.example.monpc.prover;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.glomadrian.codeinputlib.CodeInput;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import static android.R.attr.max;
import java.util.Arrays;

import uk.co.chrisjenx.calligraphy.CalligraphyUtils;


public class MainActivity extends Activity implements TextWatcher, View.OnClickListener {


    TextView proverb;
    int SCORE = 10;
    TextView score;
    Button valider;
    EditText answer;
    ImageView help;
    int randomNum, randomNum1, randomNum2;
    ImageView change;

    LovelyCustomDialog dialog;
    View addDialog ;

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b16, b17, b18;
    Button b[] = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b16, b17, b18};
    int isb[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    Score sc;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main);




       // Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/AXTRL___0.ttf");
        TextView tv = (TextView) findViewById(R.id.proverb);
        CalligraphyUtils.applyFontToTextView(this, tv, "fonts/ArabicTypesetting-Regular.ttf");

//        tv.setTypeface(tf);

        sc = new Score(0);


        score = (TextView) findViewById(R.id.score);
        b[0] = (Button) findViewById(R.id.b0);
        b[1] = (Button) findViewById(R.id.b1);
        b[2] = (Button) findViewById(R.id.b2);
        b[3] = (Button) findViewById(R.id.b3);
        b[4] = (Button) findViewById(R.id.b19);
        b[5] = (Button) findViewById(R.id.b5);
        b[6] = (Button) findViewById(R.id.b6);
        b[7] = (Button) findViewById(R.id.b7);
        b[8] = (Button) findViewById(R.id.b8);
        b[9] = (Button) findViewById(R.id.b18);
        b[10] = (Button) findViewById(R.id.b10);
        b[11] = (Button) findViewById(R.id.b11);
        b[12] = (Button) findViewById(R.id.b12);
        b[13] = (Button) findViewById(R.id.b13);
        b[14] = (Button) findViewById(R.id.b14);
        b[15] = (Button) findViewById(R.id.b17);
        b[16] = (Button) findViewById(R.id.b16);
        for (int k=0;k<b.length;k++){
            b[k].setOnClickListener(this);
        }
        proverb = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        help = (ImageView) findViewById(R.id.help);
        change = (ImageView) findViewById(R.id.change);

        randomNum = 0 + (int) (Math.random() * getResources().getStringArray(R.array.questions).length);
        String prov = getResources().getStringArray(R.array.questions)[randomNum];
        String ansr = getResources().getStringArray(R.array.answers)[randomNum];


        proverb.setText(prov);
        answer.addTextChangedListener(this);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.subScore(10);
                score.setText(sc.getScore() + "");
                String prov = getResources().getStringArray(R.array.questions)[randomNum];
                String ansr = getResources().getStringArray(R.array.answers)[randomNum];
                addDialog = getLayoutInflater().inflate(R.layout.adddialog, null);
                final TextView nom = (TextView) addDialog.findViewById(R.id.nomdialog);
                final EditText value = (EditText) addDialog.findViewById(R.id.value);
                nom.setText(prov);
                value.setText(ansr);
                dialog = new LovelyCustomDialog(view.getContext(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.darkRed)
                        .setView(addDialog)
                        .setTitle("مساعدة")
                        .setCancelable(false);
                dialog.show();
                Button ok = (Button) addDialog.findViewById(R.id.ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO
                        dialog.dismiss();
                    }
                });
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.subScore(5);
                score.setText(sc.getScore() + "");
                randomNum = 0 + (int) (Math.random() * getResources().getStringArray(R.array.questions).length);
                String prov = getResources().getStringArray(R.array.questions)[randomNum];
                String ansr = getResources().getStringArray(R.array.answers)[randomNum];
                proverb.setText(prov);
                answer.setText("");
                answer.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ansr.length()) });
                changeAlpha(ansr);
            }
        });

        changeAlpha(ansr);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        findViewById(R.id.b15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               answer.setText(answer.getText().toString().substring(0,answer.getText().length()-1));
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String ansr = getResources().getStringArray(R.array.answers)[randomNum];
        if (answer.getText().toString().equals(ansr)) {
            randomNum = 0 + (int) (Math.random() * getResources().getStringArray(R.array.questions).length);
            String prov = getResources().getStringArray(R.array.questions)[randomNum];
            ansr = getResources().getStringArray(R.array.answers)[randomNum];
            proverb.setText(prov);
            answer.setText("");
            answer.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ansr.length()) });
            sc.addScore(SCORE);
            score.setText(sc.getScore() + "");
            changeAlpha(ansr);
        }
    }

    public void changeAlpha(String ansr) {
        int numbersNeeded=18;
        int size = 16;
        int randomnum[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for(int i = 0; i <= size; i++) {
            list.add(i);
        }
        Random rand = new Random();
        int k =0;
        while(list.size() > 0) {
            int index = rand.nextInt(list.size());
            randomnum[k] = list.remove(index);
            System.out.println("Selected: "+randomnum[k]);
            k++;
        }
        int j=0;
        for (j = 0; j < ansr.length(); j++) {
            try {
                b[randomnum[j]].setText(ansr.toCharArray()[j] + "");
                isb[randomnum[j]] = 1;
            }catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        for (j=ansr.length(); j < 17; j++) {
            randomNum1 = 0 + (int) (Math.random() * isb.length);
            b[randomnum[j]].setText(getResources().getStringArray(R.array.alphabet)[randomNum1]);
            isb[randomnum[j]] = 1;
        }
    }

    @Override
    public void onClick(View view) {
        for(int i=0;i<b.length;i++){
            if(view==b[i]){
                String ans = answer.getText().toString();
                String result = ans+b[i].getText().toString();
                answer.setText(result);
            }
        }
    }

}
