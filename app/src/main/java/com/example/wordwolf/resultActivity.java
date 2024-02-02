package com.example.wordwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultActivity extends AppCompatActivity {

    TextView tvWinTeam;
    TextView tvStheme;
    TextView tvWtheme;
    Button btRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvWinTeam = findViewById(R.id.tvWinTeam);
        tvStheme = findViewById(R.id.tvStheme);
        tvWtheme = findViewById(R.id.tvWtheme);
        btRetry = findViewById(R.id.btRetry);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "LightNovelPOPv2.otf");

        tvWtheme.setTypeface(customFont);
        tvStheme.setTypeface(customFont);
        tvWinTeam.setTypeface(customFont);
        btRetry.setTypeface(customFont);

        //勝敗表示
        int wNum =BattleDate.getwolfNumber();
        if(wNum != BattleDate.getHitNumber()){
            tvWinTeam.setText("人狼側("+BattleDate.getwolfNumber()+")の勝ち！");
        }else{
            tvWinTeam.setText("市民側の勝ち！");
        }
        int theme = BattleDate.getThemeNumber();

        //表示するためお題を取得
        tvStheme.setText(ThemeList.RetTheme(theme,0));
        tvWtheme.setText(ThemeList.RetTheme(theme,1));


        btRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BattleDate.Remake();
                /*
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
                */
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}