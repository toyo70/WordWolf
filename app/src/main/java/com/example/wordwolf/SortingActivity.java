package com.example.wordwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SortingActivity extends AppCompatActivity {

    TextView tv1;
    TextView tvAnswer;
    TextView tvText;
    Button bt1;

    boolean ans =true;
    //お題を閉じる判定
    boolean openAns=false;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

        tv1 = findViewById(R.id.tv1);
        tvAnswer = findViewById(R.id.tvAnswer);
        bt1 = findViewById(R.id.bt1);
        tvText = findViewById(R.id.tvText);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "LightNovelPOPv2.otf");
        tv1.setTypeface(customFont);
        tvAnswer.setTypeface(customFont);
        tvText.setTypeface(customFont);
        bt1.setTypeface(customFont);

        //人数と狼判定を取得
        Intent intent = getIntent();
        int num = getIntent().getIntExtra("NUMBER", 0);
        int TNum = getIntent().getIntExtra("TNumber",0);
        int Judge = getIntent().getIntExtra("Judge",0);

        tv1.setText((num)+"番さんに\n渡してください");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2開いたら閉じる
                if(openAns){
                    finish();
                    Intent data = new Intent();
                    //ここから下おそらく意味ない
                    data.putExtra("key.canceledData", "キャンセル");
                    setResult(RESULT_CANCELED, data);
                    finish();
                }
                //1開くか確認
                if(ans) {
                    tvAnswer.setVisibility(View.VISIBLE);
                    bt1.setText("表示する");
                    ans=false;
                }else{
                    tvAnswer.setText(ThemeList.RetTheme(TNum,Judge));
                    //tvAnswer.setText("おだいひょうじ");
                    bt1.setText("画面を閉じる");
                    openAns =true;
                }
            }
        });
    }
}