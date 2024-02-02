package com.example.wordwolf;

import java.util.Random;

public class BattleDate {
    private static int wolfNumber;
    private static int playMember;
    private static int themeNumber = -1;
    private static int hitNumber;
    private static boolean exBattle =false;//突入したらtrue

    static Random random = new Random();

    //人狼を登録
    public static void newWolfNumber(int num){
        wolfNumber = num;
    }
    public static int getwolfNumber(){
        return wolfNumber;
    }

    //プレイ人数を登録
    public static void newPlayMember(int num){
        playMember = num;
    }
    public static int getPlayMember(){
        return playMember;
    }

    //お題番号を登録
    public static void newThemeNumber(int num){
        if(num!=themeNumber){
            themeNumber = num;
        }else{
            do {
                themeNumber = random.nextInt(ThemeList.ThemeLengthS());
            }while (num!=themeNumber);
        }
    }
    public static int getThemeNumber(){
        return themeNumber;
    }

    //当てられた番号を登録
    public static void newHitNumber(int num){
        hitNumber=num;
    }
    public static int getHitNumber(){
        return hitNumber;
    }

    //延長戦突入判定
    public static void changeExBattle(){
        if(!exBattle){
            exBattle=true;
        }
    }
    public static boolean getExBattle(){
        return exBattle;
    }

    //再戦処理
    public static void Remake(){
        exBattle = false;
        hitNumber = -1;
    }
}
