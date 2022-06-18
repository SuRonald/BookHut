package com.example.bookhut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bookhut.data.UserData;
import com.example.bookhut.helper.UserDataHelper;

import static com.example.bookhut.DataVault.currUser;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    UserDataHelper UHelper = new UserDataHelper(this);

    TextView userName, userID, userEmail;
    EditText edtName, edtEmail;
    Button saveName, saveEmail, btnLogout, btnDelete;
    View formName, formEmail, dispName, dispEmail;
    LinearLayout home, history, profile;
    Intent movePage;

    Integer flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        init();

        dispName.setOnClickListener(v -> {
            formName.setVisibility(View.VISIBLE);
            dispName.setVisibility(View.INVISIBLE);
        });

        saveName.setOnClickListener(v -> {
            String newName = edtName.getText().toString();
            if (newName.length() != 0){
                UHelper.open();
                UHelper.updateName(currUser.getUserID(), newName);
                UHelper.close();
                currUser.setUserName(newName);
                userName.setText(currUser.getUserName());
            }
            formName.setVisibility(View.INVISIBLE);
            dispName.setVisibility(View.VISIBLE);
        });

        dispEmail.setOnClickListener(v -> {
            formEmail.setVisibility(View.VISIBLE);
            dispEmail.setVisibility(View.INVISIBLE);
        });

        saveEmail.setOnClickListener(v -> {
            String newEmail = edtEmail.getText().toString();
            flag = 1;
            if (newEmail.length() != 0){
                UHelper.open();
                UserData temp = UHelper.checkEmail(newEmail);
                if (temp != null){
                    edtEmail.setError("This email has already taken!");
                    flag = 0;
                }
                if (flag == 1){
                    UHelper.updateEmail(currUser.getUserID(), newEmail);
                    UHelper.close();
                    currUser.setUserEmail(newEmail);
                    userEmail.setText(currUser.getUserEmail());
                }

            }
            if (flag == 1){
                formEmail.setVisibility(View.INVISIBLE);
                dispEmail.setVisibility(View.VISIBLE);
            }
        });

        btnLogout.setOnClickListener(v -> {
            Intent moveLogin = new Intent (this, MainActivity.class);
            startActivity(moveLogin);
            this.finish();
        });

        btnDelete.setOnClickListener(v -> {
            AlertDialog builder = new AlertDialog.Builder(ProfilePage.this)
                    .setTitle("Delete Account")
                    .setMessage("Are you sure to delete your account?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            UHelper.open();
                            UHelper.deleteUser(currUser.getUserID());
                            Intent delete = new Intent(ProfilePage.this, MainActivity.class);
                            startActivity(delete);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        });

    }

    void init(){
        userID = findViewById(R.id.userID);
        userID.setText(currUser.getUserID() + "");
        userName = findViewById(R.id.userName);
        userName.setText(currUser.getUserName());
        userEmail = findViewById(R.id.userEmail);
        userEmail.setText(currUser.getUserEmail());

        edtName = findViewById(R.id.newName);
        edtEmail = findViewById(R.id.newEmail);

        saveName = findViewById(R.id.btnSaveName);
        saveEmail = findViewById(R.id.btnSaveEmail);
        btnLogout = findViewById(R.id.btnLogout);
        btnDelete = findViewById(R.id.btnDelete);

        dispName = findViewById(R.id.editName);
        dispEmail = findViewById(R.id.editEmail);
        formName = findViewById(R.id.formName);
        formEmail = findViewById(R.id.formEmail);

        home = findViewById(R.id.homePack);
        home.setOnClickListener(this);
        history = findViewById(R.id.historyPack);
        history.setOnClickListener(this);
        profile = findViewById(R.id.userPack);
        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.homePack){
            movePage = new Intent(view.getContext(), HomePage.class);
            startActivity(movePage);
        }
        else if (view.getId() == R.id.historyPack){
            movePage = new Intent(view.getContext(), HistoryPage.class);
            startActivity(movePage);
        }
        else if (view.getId() == R.id.userPack){
            movePage = new Intent(view.getContext(), ProfilePage.class);
            startActivity(movePage);
        }
    }
}