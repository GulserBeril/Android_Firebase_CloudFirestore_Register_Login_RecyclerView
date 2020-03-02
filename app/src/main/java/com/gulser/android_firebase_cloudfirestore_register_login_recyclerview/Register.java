package com.gulser.android_firebase_cloudfirestore_register_login_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText register_name, register_surname, register_student_number, register_email, register_password, register_password_again;
    Button register_register_btn;

    FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_name = findViewById(R.id.register_name);
        register_surname = findViewById(R.id.register_surname);
        register_student_number = findViewById(R.id.register_student_number);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_password_again = findViewById(R.id.register_password_again);
        register_register_btn = findViewById(R.id.register_register_btn);

        auth = FirebaseAuth.getInstance();
    }

    public void register_register_btn_click(View view) {
        final String name = register_name.getText().toString();
        final String surname = register_surname.getText().toString();
        final String student_number = register_student_number.getText().toString();
        final String email = register_email.getText().toString();
        final String password = register_password.getText().toString();
        final String password_again = register_password.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(surname)) {
            Toast.makeText(this, "Enter surname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(student_number)) {
            Toast.makeText(this, "Enter student number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (student_number.length() != 11) {
            Toast.makeText(this, "Student number must be 11 character", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 character", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(password_again)) {
            Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", "" + name);
                    map.put("surname", "" + surname);
                    map.put("student_number", "" + student_number);
                    map.put("email", "" + email);

                    db.collection("users").document(auth.getCurrentUser().getUid().toString()).set(map);

                    Intent intent = new Intent(Register.this, HomePage.class);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
