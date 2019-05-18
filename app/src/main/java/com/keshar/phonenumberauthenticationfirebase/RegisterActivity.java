package com.keshar.phonenumberauthenticationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtPhoneNo;
    private Spinner spnrCountryCode;
    private Button sendBtn;
    public static final String PHONENUMBER = "com.keshar.phonenumberauthenticationfirebase.phoneNumber";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindView();
        spnrCountryCode.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spnrCountryCode.getSelectedItemPosition()];
                String number = edtPhoneNo.getText().toString().trim();
                if (number.isEmpty() || number.length() < 10) {
                    edtPhoneNo.setError("Valid Number is required.");
                    edtPhoneNo.requestFocus();
                    return;
                }
                String phoneNumber = "+" + code + number;
                Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                intent.putExtra(PHONENUMBER, phoneNumber);
                startActivity(intent);
                finish();
            }
        });
    }

    private void bindView() {
        edtPhoneNo = findViewById(R.id.edt_phone);
        spnrCountryCode = findViewById(R.id.countrySpr);
        sendBtn = findViewById(R.id.send_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
