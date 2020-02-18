package com.example.myasyncapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.example.myasyncapp.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private Button mtButton;
    private Button mtButton2;
    private Context mContext;
    public TextView mTw;
    final private String APIURL = "https://swapi.co/api/";
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        mtButton = findViewById(R.id.myButton);
        mtButton2 = findViewById(R.id.myButton2);
        mContext = this;
        client  = new OkHttpClient();
        mtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GetPeople("1");
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        mtButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"CA MARCHE",Toast.LENGTH_LONG).show();

            }
        });
    }

    public void GetPeople(String ppNum) throws Exception {
        String url = APIURL + "people/"+ppNum;
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {


            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run() {
                            try {
                                mTw = findViewById(R.id.prompt);
                                mTw.setText(responseBody.string());
                            }catch(Exception e){}
                        }
                    });
                }
            }
        });
    }
}
