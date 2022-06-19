//NOVANDY SALIM - 2440017454

//RONALD SUMICHAEL SUNAN - 2440003745

//SAMUEL CHRISTOPHER - 2401962964

package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookhut.helper.BookDataHelper;
import com.example.bookhut.helper.UserDataHelper;

import static com.example.bookhut.DataVault.bookList;
import static com.example.bookhut.DataVault.currUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    UserDataHelper UHelper = new UserDataHelper(this);
    BookDataHelper BHelper = new BookDataHelper(this);

    Button loginButton;
    EditText emailInput, passInput;
    TextView errMessage, reRegis;
    Intent movePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this);
        reRegis = findViewById(R.id.reRegis);
        reRegis.setOnClickListener(this);
        emailInput = findViewById(R.id.emailLgn);
        passInput = findViewById(R.id.passLgn);
        errMessage = findViewById(R.id.errMsg);

        if (bookList.size() == 0){
            BHelper.open();
            bookList = BHelper.viewBooks();
            BHelper.close();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginBtn){
            if (emailInput.length() == 0 || passInput.length() == 0) {
                errMessage.setText("Email and Passwordd can't be empty!!");
            }
            else {
                UHelper.open();
                currUser = UHelper.viewUser(emailInput.getText().toString(), passInput.getText().toString());
                UHelper.close();
                if (currUser == null){
                    errMessage.setText("Invalid Email Address or Password!!");
                }
                else {
                    movePage = new Intent(this, HomePage.class);
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(movePage);
                }
            }
        }
        else if (view.getId() == R.id.reRegis){
            errMessage.setText("");
            movePage = new Intent(this, RegisterPage.class);
            startActivity(movePage);
        }
    }
}