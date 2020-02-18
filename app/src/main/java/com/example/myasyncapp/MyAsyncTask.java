package com.example.myasyncapp;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

public class MyAsyncTask extends AsyncTask<URL, Integer, Long> {

    @Override
    protected Long doInBackground(URL... urls) {
        Log.d("****","DO IN BACKGROUND : START");

        try{
            Thread.sleep(10000);

        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        Log.d("****","DO IN BACKGROUND : END");
        return null;
    }
}
