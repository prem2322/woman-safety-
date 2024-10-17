package com.example.suraksha;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity {
    package com.example.suraksha;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

    public class loginActivity extends AppCompatActivity {

        private EditText emailInput, passwordInput;
        private Button loginButton;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            emailInput = findViewById(R.id.email_input);
            passwordInput = findViewById(R.id.password_input);
            loginButton = findViewById(R.id.login_button);

            mAuth = FirebaseAuth.getInstance();

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailInput.getText().toString().trim();
                    String password = passwordInput.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        emailInput.setError("Email is required");
                        return;
                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailInput.setError("Please enter a valid email");
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        passwordInput.setError("Password is required");
                        return;
                    }

                    loginUser(email, password);
                }
            });
        }

        private void loginUser(String email, String password) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(loginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this, DashboardActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        public void goToSignup(View view) {
            // Go to Signup activity
            startActivity(new Intent(loginActivity.this, SignupActivity.class));
        }
    }

}
