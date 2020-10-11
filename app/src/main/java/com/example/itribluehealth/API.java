package com.example.itribluehealth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class API {

    public static String calculateTime(Date time, int day) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DATE, -day);
        //cal.add(Calendar.MONTH, -day); 月數
        // cal.add(Calendar.YEAR, -day); //年份
        String newTime = sdf.format(cal.getTime());
        return newTime;
    }
}
