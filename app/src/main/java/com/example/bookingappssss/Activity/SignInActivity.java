package com.example.bookingappssss.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookingappssss.R;
import com.example.bookingappssss.databinding.ActivitySignInBinding;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

public class SignInActivity extends BaseActivity {
    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_sign_in);

        setVariable();
    }
    private void setVariable() {
        binding.btnSignIn.setOnClickListener(v -> {
            String email = binding.emailEdt.getText().toString();
            String password= binding.passwordEdt.getText().toString();

            if(!email.isEmpty() && !password.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(SignInActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else {
                Toast.makeText(SignInActivity.this, "Please fill email and password", Toast.LENGTH_SHORT).show();
            }
        });

        binding.txtSignUp.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            Toast.makeText(SignInActivity.this, "OK", Toast.LENGTH_LONG).show();
        });
    }
}