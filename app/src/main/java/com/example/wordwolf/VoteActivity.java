package com.example.wordwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity {

    TextView tvText;
    Button btVote;
    RadioGroup RadioG;
    RadioButton Rb1;
    RadioButton Rb2;
    RadioButton Rb3;
    RadioButton Rb4;
    RadioButton Rb5;
    RadioButton Rb6;
    RadioButton Rb7;
    RadioButton Rb8;
    RadioButton Rb9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        tvText = findViewById(R.id.tvText);
        btVote = findViewById(R.id.btVote);
        RadioG = findViewById(R.id.RadioG);
        Rb1 = findViewById(R.id.Rb1);//2131361800
        Rb2 = findViewById(R.id.Rb2);//2131361801
        Rb3 = findViewById(R.id.Rb3);//2131361802
        Rb4 = findViewById(R.id.Rb4);//2131361803
        Rb5 = findViewById(R.id.Rb5);//2131361804
        Rb6 = findViewById(R.id.Rb6);//2131361805
        Rb7 = findViewById(R.id.Rb7);//2131361806
        Rb8 = findViewById(R.id.Rb8);//2131361807
        Rb9 = findViewById(R.id.Rb9);//2131361808 //RB9はサドンデス

        Typeface customFont = Typeface.createFromAsset(getAssets(), "LightNovelPOPv2.otf");
        btVote.setTypeface(customFont);
        Rb1.setTypeface(customFont);
        Rb2.setTypeface(customFont);
        Rb3.setTypeface(customFont);
        Rb4.setTypeface(customFont);
        Rb5.setTypeface(customFont);
        Rb6.setTypeface(customFont);
        Rb7.setTypeface(customFont);
        Rb8.setTypeface(customFont);
        Rb9.setTypeface(customFont);
        tvText.setTypeface(customFont);


        Context context = getApplicationContext();

        int  member = BattleDate.getPlayMember();
        switch (member){
            case 3:
                Rb4.setVisibility(View.INVISIBLE);
            case 4:
                Rb5.setVisibility(View.INVISIBLE);
            case 5:
                Rb6.setVisibility(View.INVISIBLE);
            case 6:
                Rb7.setVisibility(View.INVISIBLE);
            case 7:
                Rb8.setVisibility(View.INVISIBLE);
        }
        if(BattleDate.getExBattle()){
            Rb9.setEnabled(false);
        }

        btVote.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                int checkedId = RadioG.getCheckedRadioButtonId();

                if (checkedId != -1) {
                    // 選択されているラジオボタンの取得
                    RadioButton RadioB = (RadioButton) findViewById(checkedId);// (Fragmentの場合は「getActivity().findViewById」にする)

                    //当たったメンバーを格納
                    int hitId = CNum(RadioB.getId());
                    BattleDate.newHitNumber(hitId);
                    //Toast.makeText(context, hitId+"番",Toast.LENGTH_SHORT).show();

                    if(hitId != 9) {
                        Intent intent = new Intent(getApplication(), resultActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //延長戦
                        BattleDate.changeExBattle();
                        Intent intent = new Intent(getApplication(), GameActivity.class);
                        //人数-1,分の延長戦を開始する
                        intent.putExtra("MEMBER", BattleDate.getPlayMember()-1);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    // 何も選択されていない場合の処理
                    Toast.makeText(context,"選択してください",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public int CNum(int num) {
        int ret=-2;
        switch (num) {
            case 2131361800:
                ret= 0;
            break;
            case 2131361801:
                ret= 1;
            break;
            case 2131361802:
                ret= 2;
            break;
            case 2131361803:
                ret= 3;
            break;
            case 2131361804:
                ret= 4;
            break;
            case 2131361805:
                ret= 5;
            break;
            case 2131361806:
                ret= 6;
            break;
            case 2131361807:
                ret= 7;
            break;
            case 2131361808:
                ret= 8;
            break;
        }
        return ret+1;
    }
}
