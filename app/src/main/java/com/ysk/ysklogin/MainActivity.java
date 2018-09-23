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
        //将正在创建的活动添加到活动管理器中
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_main);
        TextView username = (TextView)findViewById(R.id.su);

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("userName");
        username.setText("欢迎："+ user_name);

    }
    //表明一个要销毁的活动从活动管理器里移除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }



}
