package com.example.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText edtUserName, edtPass;
    public String strUserName, strPass;
    public Button btnLogin, btnSignUp;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp =  findViewById(R.id.btnSignUp);
        strUserName = edtUserName.getText().toString();
        strPass = edtPass.getText().toString();

    }

    public void login(View v){
        if(v.getId() == R.id.btnLogin){
            String pass = helper.searchPass(strUserName);

            if(strPass.equals(pass)) {
                Intent intent = new Intent(MainActivity.this, Display.class);
                intent.putExtra("Username", strUserName);
                startActivity(intent);
            }
            else{
                Toast passError =  Toast.makeText(MainActivity.this, "UserName and passwords don't match!", Toast.LENGTH_SHORT);
                passError.show();
            }
        }
        if(v.getId() == R.id.btnSignUp){
            Intent i = new Intent(MainActivity.this, Signup.class);
            startActivity(i);
        }
    }
}
