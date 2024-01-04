package com.example.mobprogproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;

    private void logToReg() {
        Intent loginToRegister = new Intent(LoginActivity.this, RegisterPage.class);
        startActivity(loginToRegister);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginLoginButton = findViewById(R.id.LoginLoginButton);
        TextView loginToRegisterTV = findViewById(R.id.LoginToRegisterTV);
        EditText loginEmailET = findViewById(R.id.LoginEmailET);
        EditText loginPasswordET = findViewById(R.id.LoginPasswordET);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginValidation = true;

                String email = loginEmailET.getText().toString().trim();
                String password = loginPasswordET.getText().toString().trim();

                if (email.equals("")) {
                    loginEmailET.setError("Enter email");
                    loginValidation = false;
                }

                if (password.equals("")) {
                    loginPasswordET.setError("Enter password");
                    loginValidation = false;
                }

                if (loginValidation) {
                    ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setMessage("Please wait...");
                    pd.show();
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               String userId = auth.getCurrentUser().getUid();

                               // Reference to Firestore collection "users"
                               db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                   @Override
                                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                       pd.dismiss();
                                       if (task.isSuccessful()) {
                                           DocumentSnapshot document = task.getResult();
                                           if (document.exists()) {
                                               Intent loginToHome = new Intent(LoginActivity.this, MainActivity.class);
                                               loginToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                               startActivity(loginToHome);
                                               finish();
                                           } else {
                                               Toast.makeText(LoginActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                                           }
                                       } else {
                                           Toast.makeText(LoginActivity.this, "Failed to get user data from Firestore", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                           } else {
                               pd.dismiss();
                               Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
                }
            }
        });

        loginToRegisterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logToReg();
            }
        });
    }
}