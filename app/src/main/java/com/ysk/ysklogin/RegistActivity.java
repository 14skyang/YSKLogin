package com.ysk.ysklogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

                //判断用户注册是否输入了密码和用户名
                //首先学会如何判断EditText中内容为空，要包含trim（去掉首部空格）
                if (username.getText().length()==0||password.getText().length() == 0){
                    Toast.makeText(RegistActivity.this,"用户名和密码不能为空" , Toast.LENGTH_SHORT).show();
                }else {

                    //判断是否已经注册了相同用户名
                    boolean flag = false;
                    List<User> users = DataSupport.findAll(User.class);
                    for(User user : users){
                        if(username.equals(user.getUsername())){
                            flag = true;
                        }
                    }

                    if(flag) {
                        Toast.makeText(RegistActivity.this,"该用户名已经注册",Toast.LENGTH_SHORT).show();
                    }else {
                        //新建LitePal数据库
                        Connector.getDatabase();
                        User user = new User();
                        String strusername=username.getText().toString();
                        String strpassword=password.getText().toString();
                        //存储用户名密码
                        user.setUsername(strusername);
                        user.setPassword(strpassword);
                        user.save();
                        Toast.makeText(RegistActivity.this,"创建用户成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }



                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this,LoginActivity.class);
                startActivity(intent);
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




