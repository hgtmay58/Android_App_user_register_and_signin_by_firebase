package com.example.myapplication_1121_dxxx_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent it=this.getIntent();
        String accs=it.getStringExtra("acc");
        String pwds=it.getStringExtra("pwd");
        String usid=it.getStringExtra("userid");
        TextView t1=(TextView) findViewById(R.id.textView4);
        t1.setText(accs+"\n"+pwds+"\n"+usid);
    }
}