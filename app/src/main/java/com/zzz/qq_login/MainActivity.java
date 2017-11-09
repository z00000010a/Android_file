package com.zzz.qq_login;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_qqnumber=(EditText) findViewById(R.id.et_qqnumber);
        et_login=(EditText) findViewById(R.id.et_password);
        cb_remember=(CheckBox) findViewById(R.id.cb_remember);
        restoreInfo();

    }
    public void restoreInfo(){
        File file=new File(this.getFilesDir(),"info.txt");
        if (file.exists() && file.length()>0){
            try {
                //找到文件，读取文件中的内容
                FileInputStream fis = new FileInputStream(file);
                //BR方法读取内容
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                //逐行读取文件内容
                String info = br.readLine();
                String qq = info.split("##")[0];
                String pwd = info.split("##")[1];
                et_qqnumber.setText(qq);
                et_login.setText(pwd);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void login(View view){
        String number=et_qqnumber.getText().toString().trim();
        String login=et_login.getText().toString().trim();
        if (TextUtils.isEmpty(number)||TextUtils.isEmpty(login)){
            Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (cb_remember.isChecked()){
                File file = new File(this.getFilesDir(),"info.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    String info = number+"##"+login;
                    fos.write(info.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        }

    }
}
