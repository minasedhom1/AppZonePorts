package com.example.lenovo.appzoneports;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button name_btn,port_bnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_btn= (Button) findViewById(R.id.name_btn);
        port_bnt= (Button) findViewById(R.id.port_btn);

        name_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Name is clicked",Toast.LENGTH_SHORT).show();
                Log.d("MSG","Name is clicked");
            }
        });

        port_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog nagDialog = new Dialog(MainActivity.this);
                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nagDialog.setContentView(R.layout.input_dialog);
                final EditText phone_tv = (EditText) nagDialog.findViewById(R.id.phone_tv);
                Button submit_btn= (Button) nagDialog.findViewById(R.id.submit_btn);
                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      /*  TelephonyManager tMgr = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                        String mPhoneNumber = tMgr.getLine1Number();*/                           //get your cell number to send sms to yourself.
                         sendSMS(phone_tv.getText().toString(),"Whatever your OTP code is");    //Since I don't have the Backend  and OTP sms gateway Part.
                         nagDialog.dismiss();
                    }
                });
                nagDialog.show();
            }
        });

    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
