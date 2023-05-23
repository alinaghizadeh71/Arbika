package com.example.Kafeshahrpackage.Kafeshahr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Maliheh on 06/25/2018.
 */

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("اخبار بصورت لحظه ای");
        football.add("اخبار آنلاین");
        football.add("اخبار آفلاین");
        football.add("مدیریت اخبار");


        List<String> basketball = new ArrayList<String>();
        basketball.add("اندازه فونت");
        basketball.add("رنگ فونت");
        basketball.add("سایز فونت");
        basketball.add("استایل فونت");
        basketball.add("برجستگی فونت");

        expandableListDetail.put("سرویس خبر مهم", football);
        expandableListDetail.put("سرویس خبر فوری", football);
        expandableListDetail.put("تنظیمات عمومی", basketball);
        expandableListDetail.put("مدیریت فونت", basketball);
        expandableListDetail.put("مدیریت حافظه", basketball);
        expandableListDetail.put("مدیریت اخبار آفلاین", basketball);
        return expandableListDetail;
    }
}
