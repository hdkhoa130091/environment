package com.example.environmental_monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingActivity extends AppCompatActivity {
    private final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private EditText tempMin1, tempMax1, humiMin1, humiMax1, mqMax1, lustMax1;
    private EditText tempMin2, tempMax2, humiMin2, humiMax2, mqMax2, lustMax2;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MapView();

        RealtimeData();

        SaveSettings();
    }

    private void MapView() {
        tempMin1 = findViewById(R.id.edit_temp_min1);
        tempMax1 = findViewById(R.id.edit_temp_max1);
        humiMin1 = findViewById(R.id.edit_humi_min1);
        humiMax1 = findViewById(R.id.edit_humi_max1);
        mqMax1 = findViewById(R.id.edit_co2_max1);
        lustMax1 = findViewById(R.id.edit_pm_max1);

        tempMin2 = findViewById(R.id.edit_temp_min2);
        tempMax2 = findViewById(R.id.edit_temp_max2);
        humiMin2 = findViewById(R.id.edit_humi_min2);
        humiMax2 = findViewById(R.id.edit_humi_max2);
        mqMax2 = findViewById(R.id.edit_co2_max2);
        lustMax2 = findViewById(R.id.edit_pm_max2);

        btnSave = findViewById(R.id.btn_save_settings);
    }

    private void RealtimeData() {
        mData.child("Setting_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Settings setting1 = snapshot.getValue(Settings.class);

                assert setting1 != null;

                tempMin1.setText(String.valueOf(setting1.tMin));
                tempMax1.setText(String.valueOf(setting1.tMax));
                humiMin1.setText(String.valueOf(setting1.hMin));
                humiMax1.setText(String.valueOf(setting1.hMax));
                mqMax1.setText(String.valueOf(setting1.mqMax));
                lustMax1.setText(String.valueOf(setting1.dustMax));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("Setting_2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Settings setting2 = snapshot.getValue(Settings.class);

                assert setting2 != null;

                tempMin2.setText(String.valueOf(setting2.tMin));
                tempMax2.setText(String.valueOf(setting2.tMax));
                humiMin2.setText(String.valueOf(setting2.hMin));
                humiMax2.setText(String.valueOf(setting2.hMax));
                mqMax2.setText(String.valueOf(setting2.mqMax));
                lustMax2.setText(String.valueOf(setting2.dustMax));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SaveSettings() {
        btnSave.setOnClickListener(v -> {
            float tMin1 = Float.parseFloat(tempMin1.getText().toString());
            float tMax1 = Float.parseFloat(tempMax1.getText().toString());
            float hMin1 = Float.parseFloat(humiMin1.getText().toString());
            float hMax1 = Float.parseFloat(humiMax1.getText().toString());
            float coMax1 = Float.parseFloat(mqMax1.getText().toString());
            float pmMax1 = Float.parseFloat(lustMax1.getText().toString());

            float tMin2 = Float.parseFloat(tempMin2.getText().toString());
            float tMax2 = Float.parseFloat(tempMax2.getText().toString());
            float hMin2 = Float.parseFloat(humiMin2.getText().toString());
            float hMax2 = Float.parseFloat(humiMax2.getText().toString());
            float coMax2 = Float.parseFloat(mqMax2.getText().toString());
            float pmMax2 = Float.parseFloat(lustMax2.getText().toString());

            Settings settingsData1 = new Settings(tMin1, tMax1, hMin1, hMax1, coMax1, pmMax1);
            Settings settingsData2 = new Settings(tMin2, tMax2, hMin2, hMax2, coMax2, pmMax2);

            mData.child("Setting_1").setValue(settingsData1);

            mData.child("Setting_2").setValue(settingsData2);

            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}