package com.example.busticketbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class paymentModule extends AppCompatActivity implements PaymentResultListener {
    TextView amt;
    Button pay;
    String amount ="",seat_no="",trip_date="",bid="",uid="",un="";
    DBhelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_module);
        amt=findViewById(R.id.amt);
        pay=findViewById(R.id.pay);
        myDB=new DBhelper(this);

        Intent intent=getIntent();
        amount=intent.getStringExtra("price");
        seat_no=intent.getStringExtra("seat_no");
        trip_date=intent.getStringExtra("date");
        bid=intent.getStringExtra("B_ID");
        uid=intent.getStringExtra("uid");
        un=intent.getStringExtra("uname");

        amt.setText("Your Payable Amount:"+amount);

        Checkout.preload(paymentModule.this);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment(Float.parseFloat(amount));
            }
        });
    }
    public void startPayment(float price){
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_ORq8v7QCiKfdvy");

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","Gujarat Vidyapith Payment");
            jsonObject.put("description","If you like this app Buy me a coffee");
            jsonObject.put("theme.color","#3399cc");
            jsonObject.put("currency","INR");
            jsonObject.put("amount",price*100);

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled",true);
            retryObj.put("max_count",4);

            jsonObject.put("retry",retryObj);
            checkout.open(paymentModule.this,jsonObject);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Something went wrong "+e, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onPaymentSuccess(String s) {
        Boolean insSeat;
        insSeat = myDB.insertSeat(bid,trip_date,seat_no);
        if (insSeat==true){
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            myDB.insertTrans(trip_date,bid,seat_no,amount,uid,un);
            Intent intent = new Intent(paymentModule.this,user_panel.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}