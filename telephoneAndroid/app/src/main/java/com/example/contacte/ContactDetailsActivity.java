package com.example.contacte;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {

    TextView phoneNumberText;
    Button callBtn, smsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        phoneNumberText = findViewById(R.id.phoneNumber);
        callBtn = findViewById(R.id.callBtn);
        smsBtn = findViewById(R.id.smsBtn);

        String phoneNumber = getIntent().getStringExtra("phone");

        phoneNumberText.setText("Numéro : " + phoneNumber);

        callBtn.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        smsBtn.setOnClickListener(v -> {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(Uri.parse("smsto:" + phoneNumber));
            startActivity(smsIntent);
        });
    }
}