package com.example.busticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class user_login extends AppCompatActivity {
    EditText username, password;
    TextView dsp_msg;
    Button btnLogin;
    DBhelper myDB;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        dsp_msg = findViewById(R.id.dsp_msg);

        btnLogin = findViewById(R.id.btnLogin);

        myDB = new DBhelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(user_login.this, "Please enter the Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    int id = 0;
                    cursor = myDB.getUserId(user);
                    if (cursor.moveToFirst()) {
                        id = cursor.getShort(0);
                    }
                    cursor.close();
                    cursor = myDB.getPermission(id);
                    if (cursor.moveToFirst()) {
                        id = cursor.getShort(0);
                    }
                    cursor.close();

                    if (id == 1) {
                        String role="";
                        cursor = myDB.getUserRole(user);
                        if (cursor.moveToFirst()) {
                            role = cursor.getString(0);
                        }
                        cursor.close();
                        
                        if (role.equals("admin")){
                            Boolean result = myDB.checkusernamePassword(user, pass);
                            if (result == true) {
                                Toast.makeText(user_login.this, "You are Logged in as admin", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(user_login.this, admin_panel.class);
                                intent.putExtra("uname", user);
                                startActivity(intent);
                            } else {
                                Toast.makeText(user_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                username.setText("");
                                password.setText("");
                            }
                        }else if (role.equals("user")){
                            Boolean result = myDB.checkusernamePassword(user, pass);
                            if (result == true) {
                                Toast.makeText(user_login.this, "You are Logged in as user", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(user_login.this, user_panel.class);
                                intent.putExtra("uname", user);
                                startActivity(intent);
                            } else {
                                Toast.makeText(user_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                username.setText("");
                                password.setText("");
                            }
                        }else if (role.equals("company")){
                            Boolean result = myDB.checkusernamePassword(user, pass);
                            if (result == true) {
                                Toast.makeText(user_login.this, "You are Logged in as company", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(user_login.this, company_panel.class);
                                intent.putExtra("uname", user);
                                startActivity(intent);
                            } else {
                                Toast.makeText(user_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                username.setText("");
                                password.setText("");
                            }
                        }
                    }else {
                        dsp_msg.setText("Permission not Granted by Admin");
                    }
                }
            }
        });
    }
}