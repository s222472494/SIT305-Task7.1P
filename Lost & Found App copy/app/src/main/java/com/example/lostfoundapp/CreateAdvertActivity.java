package com.example.lostfoundapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText etName, etPhone, etDescription, etDate, etLocation;
    Button btnSave;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        radioGroup = findViewById(R.id.radioGroupType);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etLocation = findViewById(R.id.etLocation);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String desc = etDescription.getText().toString();
                String date = etDate.getText().toString();
                String location = etLocation.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || desc.isEmpty() || date.isEmpty() || location.isEmpty()) {
                    Toast.makeText(CreateAdvertActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean inserted = db.insertAdvert(type, name, phone, desc, date, location);
                if (inserted) {
                    Toast.makeText(CreateAdvertActivity.this, "Advert saved", Toast.LENGTH_SHORT).show();
                    finish(); // go back to main
                } else {
                    Toast.makeText(CreateAdvertActivity.this, "Error saving advert", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
