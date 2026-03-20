package com.example.environmental_monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView txt_register, txt_forgot;
    private Button bt_login;
    private EditText account_logIn, password_logIn;
    private final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View_Mapping();

        Realtime_Account();

        Event_Register();

        Event_Forgot();

        Event_Login();
    }

    private void View_Mapping() {
        txt_register = findViewById(R.id.txt_register);
        bt_login = findViewById(R.id.bt_login);
        account_logIn = findViewById(R.id.account_logIn);
        password_logIn = findViewById(R.id.password_logIn);
        txt_forgot = findViewById(R.id.txt_forgot);
    }

    private void Realtime_Account() {
        mData.child("Account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Account account = snapshot.getValue(Account.class);

                assert account != null;
                account_logIn.setText(account.email);
                password_logIn.setText(account.pass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void Event_Register() {
        txt_register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void Event_Forgot() {
        txt_forgot.setOnClickListener(view -> {
            String email1 = account_logIn.getText().toString();
            // Kiểm tra xem đã nhập email ch
            if (email1.isEmpty())
                Toast.makeText(LoginActivity.this, "Vui lòng nhập địa chỉ mail", Toast.LENGTH_SHORT).show();
            else {
                mAuth.sendPasswordResetEmail(email1).addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        Toast.makeText(LoginActivity.this, "Đã gửi mã xác nhận, vui lòng check mail", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(LoginActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void Event_Login() {
        bt_login.setOnClickListener(view -> {
            String email1 = account_logIn.getText().toString();
            String pass1 = password_logIn.getText().toString();

            // Kiểm tra xem đã nhập email và password chưa
            if (email1.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập địa chỉ Email", Toast.LENGTH_SHORT).show();
            } else if (pass1.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập Password", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email1, pass1).addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {
                        Account account_1 = new Account(email1, pass1, 1);
                        mData.child("Account").setValue(account_1);

                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        Intent intent_Service = new Intent(LoginActivity.this, MyService.class);
                        startService(intent_Service);

                    } else {
                        Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}