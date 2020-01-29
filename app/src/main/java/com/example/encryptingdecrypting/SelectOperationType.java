package com.example.encryptingdecrypting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectOperationType extends AppCompatActivity {

    private Button encryptBtn, decryptBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_operation_type);

        encryptBtn = findViewById(R.id.encryptBtnID);
        decryptBtn = findViewById(R.id.decryptBtnID);

        encryptBtnClick();
        decryptBtnClick();
    }

    private void encryptBtnClick() {
        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToOperation("Encryption");
            }
        });
    }

    private void decryptBtnClick() {
        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToOperation("Decryption");
            }
        });
    }

    private void navigateToOperation(String operationType) {
        Intent intent = new Intent(this,EncryptDecryptOperation.class);
        intent.putExtra("operationType", operationType);
        startActivity(intent);
    }
}
