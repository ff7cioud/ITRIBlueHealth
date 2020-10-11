package com.example.itribluehealth;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQL {
    private static String DATABASE_TABLE1 = "Cheat";
    private static String DATABASE_TABLE2 = "BlueData";
    private static String DATABASE_TABLE3 = "Questionnaire";
    private SQLiteDatabase db;
    private  SQLlile dbHelper;

    //一定要呼叫
    public  SQL(Context context) {
        dbHelper = new SQLlile(context);
    }

    private  void  OpenSQL(){
        db = dbHelper.getWritableDatabase();
    }

    private  void CloseSQL(){
        db.close();
    }
    // 姓名 生日 性別
    public void InserChart(String sName,String sBrint,String sSex){

        OpenSQL();
        ContentValues cv = new ContentValues();
        cv.put("pt_name", sName);
        cv.put("Birdate", sBrint);
        cv.put("Sex", sSex);
        db.insert(DATABASE_TABLE1, null, cv);
        CloseSQL();
    }
    //類別  日期 時間  值
    public  void  InserBlueData(int ibType,String sDate,String sTime ,String sValue){
        OpenSQL();
        ContentValues cv = new ContentValues();
        cv.put("bType", ibType);
        cv.put("bDate", sDate);
        cv.put("bTime", sTime);
        cv.put("bValue",sValue);
        db.insert(DATABASE_TABLE2, null, cv);
        CloseSQL();
    }
    // 日期 時間 走路喘  躺下喘 睡覺如何 腳水腫
    public  void  InserQuestionnaire(String qDate,String qTime,String Walk ,String Liedown,String Seep,String Edema){

        OpenSQL();
        ContentValues cv = new ContentValues();
        cv.put("qDate", qDate);
        cv.put("qTime", qTime);
        cv.put("Walk", Walk);
        cv.put("Liedown",Liedown);
        cv.put("Seep",Seep);
        cv.put("Edema",Edema);
        db.insert(DATABASE_TABLE3, null, cv);
        CloseSQL();

    }

    public List<String> SelectChart() {
        OpenSQL();
        String[] colNames;
        String str = "";
        String sql = "Select * from "+DATABASE_TABLE1;
        Cursor c = db.rawQuery(sql, null);
        colNames = c.getColumnNames();
        // 顯示欄位名稱 // 姓名 生日 性別
        List<String> listsql = new ArrayList<>();

        str +="姓名,生日,性別";
        listsql.add(str);
        c.moveToFirst();  // 第1筆
        // 顯示欄位值

        for (int i = 0; i < c.getCount(); i++) {
            str = "";
            str += c.getString(0) + ",";
            str += c.getString(1) + ",";
            str += c.getString(2) ;

            listsql.add(str);
            c.moveToNext();  // 下一筆
        }
        CloseSQL();
        return  listsql;
    }

    public List<String> SelectBlueData(int itype) {
        OpenSQL();
        String[] colNames;
        String str = "";
        String sql = "Select * from "+DATABASE_TABLE2;
        if (itype != 0){
            sql += " where bType = "+itype;
        }
        Cursor c = db.rawQuery(sql, null);
        colNames = c.getColumnNames();
        // 顯示欄位名稱 //類別  日期 時間  值
        List<String> listsql = new ArrayList<>();
        str +="類別,日期,時間,值";
        listsql.add(str);
        c.moveToFirst();  // 第1筆
        // 顯示欄位值

        for (int i = 0; i < c.getCount(); i++) {
            str = "";
            str += c.getString(0) + ",";
            str += c.getString(1) + ",";
            str += c.getString(2) + ",";
            str += c.getString(3) ;
            listsql.add(str);
            c.moveToNext();  // 下一筆
        }
        CloseSQL();
        return  listsql;
    }

    public List<String> SelectQuestionnaire() {
        OpenSQL();
        String[] colNames;
        String str = "";
        String sql = "Select * from "+DATABASE_TABLE3;

        Cursor c = db.rawQuery(sql, null);
        colNames = c.getColumnNames();
        // 顯示欄位名稱 // 日期 時間 走路喘  躺下喘 睡覺如何 腳水腫
        List<String> listsql = new ArrayList<>();
        str +="日期,時間,走路喘,躺下喘,睡覺如何,腳水腫";
        listsql.add(str);
        c.moveToFirst();  // 第1筆
        // 顯示欄位值
        for (int i = 0; i < c.getCount(); i++) {
            str = "";
            str += c.getString(0) + ",";
            str += c.getString(1) + ",";
            str += c.getString(2) + ",";
            str += c.getString(3) + ",";
            str += c.getString(4) + ",";
            str += c.getString(5) ;
            listsql.add(str);
            c.moveToNext();  // 下一筆
        }
        CloseSQL();
        return  listsql;
    }

    public void DeleteDatatoDay(int iday) throws Exception {
        Date date = new Date();
        String sDate = API.calculateTime(date,iday);

        OpenSQL();
        db.execSQL("DELETE FROM "+DATABASE_TABLE2 +" WHERE bDate > " + sDate);
        db.execSQL("DELETE FROM "+DATABASE_TABLE3 +" WHERE qDate > " + sDate);
        CloseSQL();

    }

}

