package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookhut.data.UserData;
import com.example.bookhut.helper.UserDataHelper;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    UserDataHelper UHelper = new UserDataHelper(this);

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
            else if (!regisEmail.getText().toString().endsWith(".com")){
                errMessage.setText("Email Address must ends with \'.com\'!!");
            }
            else if (regisPassword.getText().toString().length() <= 4){
                errMessage.setText("Your Password must be longer than 4 characters!!");
            }
            else if (regisEmail.getText().toString().endsWith(".com") && regisPassword.getText().toString().length() > 4){
                UHelper.open();
                UserData temp = UHelper.checkEmail(regisEmail.getText().toString());
                UHelper.close();
                if (temp != null){
                    errMessage.setText("This email has already taken!!");
                }
                else {
                    UHelper.open();
                    UHelper.insertUser(regisEmail.getText().toString(), regisUsername.getText().toString(), regisPassword.getText().toString(), 0);
                    UHelper.close();
                    errMessage.setText("");
                    Toast.makeText(this, "Register Successful!!", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            }
        }
        else if (view.getId() == R.id.reLogin){
            onBackPressed();
        }
    }
}