package com.example.environmental_monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText account_register, password_register, password_1_register;
    private Button bt_register;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolBar();

        View_Mapping();

        Event_Register();
    }

    private void toolBar() {
        Toolbar toolbar_Register = findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar_Register);
    }

    private void View_Mapping() {
        account_register = findViewById(R.id.account_register);
        password_register = findViewById(R.id.password_register);
        password_1_register = findViewById(R.id.password_1_register);
        bt_register = findViewById(R.id.bt_register);
    }

    private void Event_Register() {
        bt_register.setOnClickListener(v -> {
            String email1 = account_register.getText().toString();
            String pass1 = password_register.getText().toString();
            String pass2 = password_1_register.getText().toString();
            if (email1.isEmpty())
                Toast.makeText(RegisterActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            else if (pass1.isEmpty())
                Toast.makeText(RegisterActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            else if (pass2.isEmpty())
                Toast.makeText(RegisterActivity.this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            else {
                if (pass1.equals(pass2)) {
                    mAuth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(RegisterActivity.this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    });
                } else
                    Toast.makeText(RegisterActivity.this, "Nhập lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}