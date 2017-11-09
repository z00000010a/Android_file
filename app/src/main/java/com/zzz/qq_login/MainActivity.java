package com.zzz.qq_login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText et_qqnumber;
    private EditText et_login;
    private CheckBox cb_remember;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=this.getSharedPreferences("config",this.MODE_PRIVATE);

        et_qqnumber=(EditText) findViewById(R.id.et_qqnumber);
        et_login=(EditText) findViewById(R.id.et_password);
        cb_remember=(CheckBox) findViewById(R.id.cb_remember);
        restoreInfo();

    }
    public void restoreInfo(){
        String qq=sp.getString("qq","");
        String pwd=sp.getString("pwd","");
        et_qqnumber.setText(qq);
        et_login.setText(pwd);

    }
    public void login(View view){
        String number=et_qqnumber.getText().toString().trim();
        String login=et_login.getText().toString().trim();
        if (TextUtils.isEmpty(number)||TextUtils.isEmpty(login)){
            Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (cb_remember.isChecked()){
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("qq",number);
                editor.putString("pwd",login);
                editor.commit();
            }
            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        }

    }
}
