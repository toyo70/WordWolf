package com.example.wordwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button btStart;
    Button btHowto;
    
    TextView TvSt;
    Spinner spMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "LightNovelPOPv2.otf");

        spMember = (Spinner)findViewById(R.id.spMember);
        btStart = findViewById(R.id.btStart);
        btHowto = findViewById(R.id.btHowto);
        TvSt = findViewById(R.id.TvSt);

        btHowto.setTypeface(customFont);
        btStart.setTypeface(customFont);
        TvSt.setTypeface(customFont);
        //spMember.setTypeface(customFont);

        // Spinnerオブジェクトを取得

        Context context = getApplicationContext();


        // 選択されているアイテムを取得
        //String item = (String)spMember.getSelectedItem();

        // お題の長さを取得
        int TSLength,TWLength;
        TSLength=ThemeList.ThemeLengthS();
        TWLength=ThemeList.ThemeLengthW();


        //お題の長さが異なる場合終了
        if(TSLength != TWLength){
            Toast.makeText(context , "エラーが発生しました\n管理者にお問い合わせください\n(ErrorCode: MainAC:55)", Toast.LENGTH_LONG).show();
            finish();
        }


        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random ra = new Random();
                int ThemeNumber = ra.nextInt(TSLength);

                //人数の情報を取得
                int idx = spMember.getSelectedItemPosition();

                //DBに登録
                BattleDate.newPlayMember(idx+3);
                BattleDate.newThemeNumber(ThemeNumber);


                //Gameに飛ばす +3して人数を合わせる
                Intent intent = new Intent(getApplication(), GameActivity.class);
                intent.putExtra("MEMBER", idx+3);
                //intent.putExtra("ThemeNumber", ThemeNumber);
                startActivity(intent);
                finish();
            }
        });

        btHowto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), HowtoplayActivity.class);
                startActivity(intent);
            }
        });



    }
}