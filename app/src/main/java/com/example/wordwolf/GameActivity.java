package com.example.wordwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


//timer import
import java.text.SimpleDateFormat;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    TextView tvSet;
    //TextView tvSet = (TextView) layout.findViewById(R.id.tvSet);
    TextView tv2;
    Button btStart;
    Button btNext;
    //int i=0;//+3して計算


    //タイマー表示用書式(例:12.3秒)
    private final SimpleDateFormat timerFormat = new SimpleDateFormat("mm:ss.S");
    //タイマーカウント用ハンドラー生成
    private final Handler timerHandler = new Handler();
    //タイマー用カウンタ, 秒間隔(ミリ秒)
    private int count, interval;

    //ボタンロック用
    private boolean startRock=true;
    //人数タイマー
    private int time;

    boolean talkend = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //int member = getIntent().getIntExtra("MEMBER", 0);

        //人数の情報、お題番号を取得
        Intent intent = getIntent();
        int member = BattleDate.getPlayMember();
        //int TNumber = intent.getIntExtra("ThemeNumber",0);
        //int member = BattleDate.getPlayMember();
        int TNumber = BattleDate.getThemeNumber();


        Typeface customFont = Typeface.createFromAsset(getAssets(), "LightNovelPOPv2.otf");

        tvSet = findViewById(R.id.tvSet);
        //TextView (TextView)view.findViewById(R.id.tvSet);
        //TextView tvSet = (TextView) layout.findViewById(R.id.tvSet);
        tv2 = findViewById(R.id.tv2);
        btStart = findViewById(R.id.btStart);
        btNext = findViewById(R.id.btNext);

        tvSet.setTypeface(customFont);
        tv2.setTypeface(customFont);
        btNext.setTypeface(customFont);
        btStart.setTypeface(customFont);


        btNext.setEnabled(false);

        if(BattleDate.getExBattle()) {//延長戦なら
            int extime;
            tv2.setText(("[延長戦]\n人数:" + (member) + "人"));
            if(member >5){
                time = (5 * 60 * 10);
            }else if(member > 3) {
                time = (3 * 60 * 10);
            }else{
                time = (2 * 60 * 10);
            }
        }else{
            tv2.setText(("人数:" + (member) + "人"));
            time = (member * 60 * 10);
        }
        //count = 0;
        count = time;
        //カウントアップ間隔（0.1秒）設定
        interval = 100;
        tvSet.setText(timerFormat.format(count*interval));

        if(!BattleDate.getExBattle()) {
            //人狼を決定
            Random ra = new Random();
            int WNumber = ra.nextInt(member)+1;

            Context context =getApplicationContext();
            //Toast.makeText(context , member+":"+WNumber, Toast.LENGTH_LONG).show();


            BattleDate.newWolfNumber(WNumber);

            //順に人数分展開
            for (int i = member; i > 0; i--) {
                intent = new Intent(getApplication(), SortingActivity.class);
                intent.putExtra("NUMBER", i);
                intent.putExtra("TNumber", TNumber);
                if (WNumber == i) {
                    intent.putExtra("Judge", 1);//人狼なら1
                } else {
                    intent.putExtra("Judge", 0);
                }
                startActivity(intent);

            }
        }

        //タイマースタートストップ
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startRock) {
                    startRock =false;
                    timerHandler.post(runnable);
                    btNext.setEnabled(false);
                }else{
                    startRock=true;
                    //ストップボタン処理
                    timerHandler.removeCallbacks(runnable);
                    btNext.setEnabled(true);
                }
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(talkend) {
                    btNext.setText("話し合いを終了します\nよろしいですか?");
                    talkend=false;
                }else{
                    Intent intent = new Intent(getApplication(), VoteActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    //タイマーハンドラ用Runnable
    private final Runnable runnable = new Runnable() {
        //タイマー処理
        @Override
        public void run() {
            //ここからカウントアップ処理記述
            count--;    //1 = 0.1秒
            tvSet.setText(timerFormat.format(count * interval));
            timerHandler.postDelayed(this, interval);
            if(count==0){
                startRock=true;
                timerHandler.removeCallbacks(runnable);
                btStart.setEnabled(false);
                btNext.setEnabled(true);
            }
        }
    };
}