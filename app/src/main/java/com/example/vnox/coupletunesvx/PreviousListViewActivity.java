package com.example.vnox.coupletunesvx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PreviousListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_list_view);
        final TextView preList = (TextView)findViewById(R.id.preList);
        String constructList = new String("");
        for(int i = 0; i < DataHolder.historyList.size(); i++){
            constructList += DataHolder.historyList.get(i);
            constructList += "\n";
        }

        preList.setText(constructList);
    }
}
