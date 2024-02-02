package com.example.wordwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HowtoplayActivity extends AppCompatActivity {

    TextView tvHowto;
    Button btBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoplay);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "LightNovelPOPv2.otf");

        btBack = findViewById(R.id.btBack);
        tvHowto = findViewById(R.id.tvHowto);
        btBack.setTypeface(customFont);
        tvHowto.setTypeface(customFont);


        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}