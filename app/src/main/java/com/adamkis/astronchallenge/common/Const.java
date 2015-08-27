package com.adamkis.astronchallenge.common;

import android.net.Uri;

/**
 * Created by adam on 8/25/15.
 */
public class Const {


    public static String ATTENDEES_KEY = "attendees_key";

    public static String LOG_TAG = "AstronChallenge";

    public static int AGE_LIMIT_WORKER = 20;
    public static int AGE_LIMIT_RETIRED = 60;

//    http://mash1.astron.hu:23985/recruiting/attendees

    public static String buildSearchUrl(){

        return "http://mash1.astron.hu:23985/recruiting/attendees";


    }
}
