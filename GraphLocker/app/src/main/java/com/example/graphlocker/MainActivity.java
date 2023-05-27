package com.example.graphlocker;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button encrypt, decrypt, locker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encrypt = findViewById(R.id.encryption_button);
        decrypt = findViewById(R.id.decrypted_button);
        locker = findViewById(R.id.locker_button);

        encrypt.setOnClickListener(view -> {
            Intent enc = new Intent(MainActivity.this, TextEncoder.class);
            startActivity(enc);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        decrypt.setOnClickListener(view -> {
            Intent dec = new Intent(MainActivity.this, TextDecoder.class);
            startActivity(dec);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        locker.setOnClickListener(view -> {
            Intent lock = new Intent(MainActivity.this, LockerMaker.class);
            startActivity(lock);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}