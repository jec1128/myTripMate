package com.example.tripmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tripmate.User.LoginActivity;

public class FirstAuthActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro2);

        if(SaveSharedPreference.getUserName(FirstAuthActivity.this).length() == 0) {
            // call Login Activity
            intent =  new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            this.finish();

        } else {
            // Call Next Activity
            intent =  new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("STD_NUM", SaveSharedPreference.getUserName(this).toString());
            startActivity(intent);
            this.finish();
        }
    }

}
