package com.example.busticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class user_signup extends AppCompatActivity {
    EditText username,password,repassword,mobile,email;
    Button btnsignUp,btnsignIn;
    DBhelper myDB;
    String item[] = {"admin","user","company"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    String ur = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);

        autoCompleteTextView = findViewById(R.id.auto_complete_textview);
        adapterItems = new ArrayAdapter<String>(this,R.layout.user_role_list_item,item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ur = parent.getItemAtPosition(position).toString();
            }
        });

        btnsignUp = findViewById(R.id.btnSignUp);
        btnsignIn = findViewById(R.id.btnSignIn);

        myDB = new DBhelper(this);

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean emailValidate = emailValidator(email);
                if (emailValidate==false){
                    Toast.makeText(user_signup.this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                }else if(!mobile.getText().toString().matches("[0-9]{10}")){
                    Toast.makeText(user_signup.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }else if (!ur.trim().equals("admin")&&!ur.trim().equals("user")&&!ur.trim().equals("company")){
                    Toast.makeText(user_signup.this, "Role Should be admin, user or company", Toast.LENGTH_LONG).show();
                }
                else{
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String repass = repassword.getText().toString();
                    String mob = mobile.getText().toString();
                    String em = email.getText().toString();

                    if (user.equals("")||pass.equals("")||repass.equals("")||mob.equals("")||em.equals("")||ur.equals("")){
                        Toast.makeText(user_signup.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (pass.equals(repass)){
                            Boolean usercheckResult = myDB.checkusername(user);
                            if (usercheckResult==false){
                                int total_user = myDB.getTotalUsers();
                                Boolean regResult;
                                if (total_user==0){
                                    if (ur.trim().equals("admin")){
                                        regResult = myDB.insertData(user,pass,mob,em,ur);
                                        if (regResult==true){
                                            Cursor cursor = myDB.getUserId(user);
                                            if (cursor.moveToFirst()){
                                                myDB.insertPermission(cursor.getShort(0),1);
                                            }
                                            Toast.makeText(user_signup.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(user_signup.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(user_signup.this, "First time Role Should be Only admin", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    regResult = myDB.insertData(user,pass,mob,em,ur);
                                    if (regResult==true){
                                        Cursor cursor = myDB.getUserId(user);
                                        if (cursor.moveToFirst()){
                                            myDB.insertPermission(cursor.getShort(0),0);
                                        }
                                        Toast.makeText(user_signup.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(user_signup.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                Toast.makeText(user_signup.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(user_signup.this, "Password not Matched", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),user_login.class);
                startActivity(intent);
            }
        });
    }
    public Boolean emailValidator(EditText etMail) {
        String emailToText = etMail.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true;
        } else {
            return false;
        }
    }
}