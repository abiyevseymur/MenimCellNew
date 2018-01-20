package com.example.seymur.azercell2;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seymur.azercell2.R;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static android.R.attr.phoneNumber;

public class SendSMSTariff extends AppCompatActivity {

    String Send_SMS = "SEND_SMS";
    String Deliver_SMS = "DELIVER_SMS";

    Intent send_intent =  new Intent(Send_SMS);
    Intent deliver_intent = new Intent(Deliver_SMS);

    PendingIntent send_pi,deliver_pi;

    Button buttonTarifcancel;
    Button buttonTarif;
    String Numb;
    String TextSMS = " ";
    Bundle bundle;
    TextView messageTittle;
    String messageBundle;
    String MIXPANEL_TOKEN = "5908ccdb281d509b82825cb12f81f7a8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_smstariff);
        send_pi = PendingIntent.getBroadcast(SendSMSTariff.this,0,send_intent,0);
        deliver_pi = PendingIntent.getBroadcast(SendSMSTariff.this,0,send_intent,0);

         bundle = getIntent().getExtras();
        Numb= bundle.getString("first");
        TextSMS= bundle.getString("second");
        messageBundle = bundle.getString("messageTittle");
//         TextSMS = bundle.getString("second");

        messageTittle = (TextView)findViewById(R.id.youSure) ;
        messageTittle.setText(messageBundle);

        buttonTarif = (Button) findViewById(R.id.SendSmstarrif);
        buttonTarif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(Numb,null,TextSMS,send_pi,deliver_pi);
                Toast.makeText(getApplicationContext(), R.string.accepted,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        buttonTarifcancel = (Button) findViewById(R.id.SendSmstarrifCancel);
        buttonTarifcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    };

/*    public void NumbAndMessage(String number,String message){
        Numb.setText(number);
        TextSMS.setText(message);
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(sentReciever,new IntentFilter(Send_SMS));
        registerReceiver(deliverReciever,new IntentFilter(Deliver_SMS));
        Log.d(TAG, "smsDialog onResume");
    };

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(sentReciever);
        unregisterReceiver(deliverReciever);
        Log.d(TAG, "smsDialog onStop");
    }
    BroadcastReceiver sentReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MixpanelAPI mixpanel =
                    MixpanelAPI.getInstance(context, MIXPANEL_TOKEN);
            String phone = String.valueOf(BalanceMenu.PhoneNumber());
            switch (getResultCode()){
                case Activity.RESULT_OK:

                    Toast.makeText(context, R.string.Succes,Toast.LENGTH_LONG).show();
                    mixpanel.getPeople().identify(phone);
                    mixpanel.identify(phone);

                        try {
                        JSONObject props = new JSONObject();
                        props.put("SendSmsTo", Numb);
                        props.put("SMStext", TextSMS);
                        mixpanel.track("Message Sended", props);
                    }
                    catch (JSONException e) {
                        Log.e("MYAPP", "Unable to add properties to JSONObject", e);
                    }
                    break;
                default:
                    Toast.makeText(context, R.string.Error,Toast.LENGTH_LONG).show();
                    try {
                        JSONObject propss = new JSONObject();
                        propss.put("Error","Any Error SMS");
                        mixpanel.track("Error",propss);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }

        }
    };
    BroadcastReceiver deliverReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()){
                case Activity.RESULT_OK:
                    Toast.makeText(context, R.string.Succes,Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(context,R.string.Error,Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };
    final String TAG = "cycle";
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"smsDialog onStart");
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "smsDialog onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "smsDialog onPause");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "smsDialog onDestroy");
    }
}