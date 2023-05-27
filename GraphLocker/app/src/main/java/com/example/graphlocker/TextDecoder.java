package com.example.graphlocker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;

public class TextDecoder extends AppCompatActivity {

    EditText enterText, secretKey;
    TextView decryptedText;
    Button decryptBtn, copyTextBtn;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_decoder);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Text Decryption");
            actionBar.show();
        }

        enterText = findViewById(R.id.enter_decryptText_editText);
        secretKey = findViewById(R.id.enter_key);
        decryptedText = findViewById(R.id.tv_decrypted_txt);
        decryptBtn = findViewById(R.id.decrypt_btn);
        copyTextBtn = findViewById(R.id.copy_decText_btn);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void copy(View view) {
        String data = decryptedText.getText().toString().trim();
        if(!data.isEmpty()){
            ClipData temp = ClipData.newPlainText("text", data);
            clipboardManager.setPrimaryClip(temp);
            Toast.makeText(this, "Copied to clipboard!", Toast.LENGTH_SHORT).show();
        }
    }

    public void dec(View view) {
        String temp = enterText.getText().toString();
        String temp1 = secretKey.getText().toString();
        String rv = Algorithm.decrypt(temp, temp1);
        decryptedText.setText(rv);
        closeKeyboardD();
    }

    private void closeKeyboardD() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}