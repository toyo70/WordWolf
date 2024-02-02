package com.example.wordwolf;

public class ThemeList {

    private static String[] ThemeS = {"冬休み", "副業", "風呂掃除", "twitter", "水族館","ドラえもん","ファミレス","アルバイト面接","お年玉","ガラケー","太陽"};

    private static String[] ThemeW = {"春休み", "アルバイト", "食器洗い", "LINE", "動物園","ドラミちゃん","カフェ","就活","誕生日プレゼント","固定電話","月"};


    //手札くばり
    public static String RetTheme(int Index, int Judge) {

        //人狼なら =1
        if (Judge == 1) {
            return ThemeS[Index];
        } else {
            return ThemeW[Index];
        }
    }
    //テーマの個数取得
    public static int ThemeLengthS(){
        return ThemeS.length;
    }
    public static int ThemeLengthW(){
        return ThemeW.length;
    }

}