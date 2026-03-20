package com.example.environmental_monitoring;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private TextView txt_node1_temp, txt_node1_humi, txt_node1_co2, txt_node1_dust;
    private TextView txt_node2_temp, txt_node2_humi, txt_node2_co2, txt_node2_dust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_node1_temp = findViewById(R.id.txt_node1_temp);
        txt_node1_humi = findViewById(R.id.txt_node1_humi);
        txt_node1_co2 = findViewById(R.id.txt_node1_co2);
        txt_node1_dust = findViewById(R.id.txt_node1_dust);
        txt_node2_temp = findViewById(R.id.txt_node2_temp);
        txt_node2_humi = findViewById(R.id.txt_node2_humi);
        txt_node2_co2 = findViewById(R.id.txt_node2_co2);
        txt_node2_dust = findViewById(R.id.txt_node2_dust);

        FloatingActionButton btn_settings_fab = findViewById(R.id.btn_settings_fab);

        mData.child("Node_1").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Data data1 = snapshot.getValue(Data.class);

                assert data1 != null;
                txt_node1_temp.setText("Nhiệt độ: " + data1.temperature + " °C");
                txt_node1_humi.setText("Độ ẩm: " + data1.humidity + " %");
                txt_node1_co2.setText("CO₂: " + data1.mq135 + " ppm");
                txt_node1_dust.setText("Bụi: " + data1.dust_density + " mg/m³");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("Node_2").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Data data2 = snapshot.getValue(Data.class);

                assert data2 != null;
                txt_node2_temp.setText("Nhiệt độ: " + data2.temperature + " °C");
                txt_node2_humi.setText("Độ ẩm: " + data2.humidity + " %");
                txt_node2_co2.setText("CO₂: " + data2.mq135 + " ppm");
                txt_node2_dust.setText("Bụi: " + data2.dust_density + " mg/m³");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_settings_fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        });
    }
}