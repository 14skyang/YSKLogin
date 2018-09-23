package com.ysk.ysklogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class RegistActivity extends AppCompatActivity {

    private String strusername;
    private String strpassword;

    private String u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将一个正在创建的活动添加到活动管理器中
        ActivityCollector.addActivity(this);
        setContentView(R.layout.regist_layout);

        Button yes = (Button) findViewById(R.id.YES);
        Button no =(Button) findViewById(R.id.NO);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText)findViewById(R.id.username);
                EditText password = (EditText)findViewById(R.id.password);

                strusername=username.getText().toString();
                strpassword=password.getText().toString();

                Log.e("aaa", strusername);

                //判断用户注册是否输入了密码和用户名
                //首先学会如何判断EditText中内容为空，要包含trim（去掉首部空格）
                if (TextUtils.isEmpty(strusername)){
                    Toast.makeText(RegistActivity.this,"用户名和密码不能为空" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strpassword)){
                    Toast.makeText(RegistActivity.this,"用户名和密码不能为空" , Toast.LENGTH_SHORT).show();
                    return;
                }
                //新建LitePal数据库
                Connector.getDatabase();
                List<User> users = DataSupport.select("username").find(User.class);
                for(User user : users){
                    if (strusername.equals(user.getUsername())){
                        Toast.makeText(RegistActivity.this,"该用户名已经注册",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //存储用户名密码
                    user.setUsername(strusername);
                    user.setPassword(strpassword);
                    user.save();
                    Toast.makeText(RegistActivity.this,"创建用户成功",Toast.LENGTH_SHORT).show();
                    finish();
                }

                }




        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    //表明一个要销毁的活动从活动管理器里移除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }




}




