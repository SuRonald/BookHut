package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookhut.helper.UserDataHelper;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    UserDataHelper UHelper = new UserDataHelper(this);

    int accCount;
    Button regisButton;
    TextView errMessage, reLogin;
    EditText regisEmail, regisUsername, regisPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        regisEmail = findViewById(R.id.emailRegis);
        regisUsername = findViewById(R.id.usernameRegis);
        regisPassword = findViewById(R.id.passRegis);
        regisButton = findViewById(R.id.registerBtn);
        regisButton.setOnClickListener(this);
        reLogin = findViewById(R.id.reLogin);
        reLogin.setOnClickListener(this);
        errMessage = findViewById(R.id.errMsg);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerBtn){
            if (regisEmail.length() == 0 || regisUsername.length() == 0 || regisPassword.length() == 0){
                errMessage.setText("Please Insert the Necessary Field!!");
            }
            else if (regisEmail.getText().toString().endsWith(".com") && regisUsername.getText().toString().length() >= 3 && regisPassword.getText().toString().length() >= 3){
                errMessage.setText("");
                UHelper.open();
                accCount = UHelper.countUsers();
                accCount++;
                UHelper.insertUser(accCount, regisEmail.getText().toString(), regisUsername.getText().toString(), regisPassword.getText().toString(), 0);
                UHelper.close();
                Toast.makeText(this, "Register Successful!!", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
            else {
                errMessage.setText("Invalid Email Address, Username or Password!!");
            }
        }
        else if (view.getId() == R.id.reLogin){
            onBackPressed();
        }
    }
}