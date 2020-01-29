package com.example.encryptingdecrypting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class EncryptDecryptOperation extends AppCompatActivity {

    private Button submitBtn;

    private EditText resultEt, inputStringEt;

    private String operationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_decrypt_operation);

        submitBtn = findViewById(R.id.submitBtnID);
        resultEt = findViewById(R.id.resultEtID);
        inputStringEt = findViewById(R.id.inputStringEtID);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        operationType = getIntent().getStringExtra("operationType");

        setTitle(operationType);

        inputStringEt.addTextChangedListener(textWatcher);

        submitBtnClick();
    }

    private void submitBtnClick() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = inputStringEt.getText().toString();
                if (!inputString.isEmpty())
                    if (operationType.equals("Decryption")) {
                        resultEt.setText(decrypt(inputString));
                    } else {
                        resultEt.setText(encrypt(inputString));
                    }
            }
        });
    }


    private String decrypt(String input) {

        StringBuilder decryptString = new StringBuilder();

        StringBuilder temp = new StringBuilder();
        int freq = 0;

        for (int i = 0; i < input.length(); ) {
            temp = new StringBuilder();
            freq = 0;

            while (i < input.length() && input.charAt(i) >= 'a' && input.charAt(i) <= 'z' || input.charAt(i) == ' ') {
                temp.append(input.charAt(i));
                i++;
            }

            while (i < input.length() && input.charAt(i) >= '1' && input.charAt(i) <= '9') {
                freq = freq * 10 + input.charAt(i) - '0';
                i++;
            }

            for (int j = 1; j <= freq; j++)
                decryptString.append(temp);
        }

        if (freq == 0)
            decryptString.append(temp);

        return decryptString.toString();
    }


    public String encrypt(String input) {
        StringBuilder builder = new StringBuilder();
        int length = input.length();
        for (int i = 0; i < length; i++) {

            int count = 1;
            while (i < length - 1 && input.charAt(i) == input.charAt(i + 1)) {
                count++;
                i++;
            }

            builder.append(input.charAt(i)).append(count);
        }
        return builder.toString();
    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            resultEt.setText("");
            String inputString = inputStringEt.getText().toString();

            if (inputString.isEmpty()) {
                submitBtn.setBackgroundResource(R.drawable.button_background_disable);
            } else {
                submitBtn.setBackgroundResource(R.drawable.button_background);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void print(String msg) {
        Log.d("encryptDecryptOperation", msg);
    }
}
