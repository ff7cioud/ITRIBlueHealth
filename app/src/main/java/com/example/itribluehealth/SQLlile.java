package com.example.itribluehealth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLlile  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "IRTI";
    private static final int DATABASE_VERSION = 1;

    public SQLlile(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
                                              // 姓名 生日 性別
        String  Table1 = "CREATE TABLE Cheat (pt_name text no null,Birdate text no null,Sex text no null)";
                                               //類別  日期 時間  值
        String  Table2 = "CREATE TABLE BlueData (bType integer no null , bDate text no null , bTime text no null ,bValue no null)";
                                              // 日期 時間 走路喘  躺下喘 睡覺如何 腳水腫
        String  Table3 = "CREATE TABLE Questionnaire (qDate text no null , qTime text no null ,Walk text no null,Liedown text no null,Seep text no null,Edema text no null)";

        db.execSQL(Table1);
        db.execSQL(Table2);
        db.execSQL(Table3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cheat");
        db.execSQL("DROP TABLE IF EXISTS BlueData");
        db.execSQL("DROP TABLE IF EXISTS Questionnaire");
        onCreate(db);
    }
}

