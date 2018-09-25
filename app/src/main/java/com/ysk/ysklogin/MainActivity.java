package com.ysk.ysklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView username = (TextView)findViewById(R.id.su);
        Intent intent = getIntent();//接收传值
        String user_name = intent.getStringExtra("userName");
        username.setText("欢迎："+ user_name);

    }




}
