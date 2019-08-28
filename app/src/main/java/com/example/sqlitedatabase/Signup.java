package com.example.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText edtName, edtEmail, edtUsername, edtPass, edtPassConf;
    String strName, strEmail, strUsername, strPass, strPassConf;
    Button btnSignUp;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUp(View view){
        if(view.getId() == R.id.btnSignUp){
            edtName =  findViewById(R.id.edtName);
            edtEmail =  findViewById(R.id.edtEmail);
            edtUsername =  findViewById(R.id.edtUserName);
            edtPass = findViewById(R.id.edtPass);
            edtPassConf =  findViewById(R.id.edtCPass);

            strName = edtName.getText().toString();
            strEmail =  edtEmail.getText().toString();
            strUsername = edtUsername.getText().toString();
            strPass = edtPass.getText().toString();
            strPassConf = edtPassConf.getText().toString();

            if(!strPass.equals(strPassConf)){
                Toast pass =  Toast.makeText(Signup.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else{
                Contact c = new Contact();
                c.setName(strName);
                c.setEmail(strEmail);
                c.setUname(strUsername);
                c.setPass(strPass);

                helper.insertContact(c);
            }
        }
    }
}
