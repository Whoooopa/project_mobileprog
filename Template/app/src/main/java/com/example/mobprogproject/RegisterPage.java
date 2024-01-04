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
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterPage extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseFirestore db;
    ProgressDialog pd;
    EditText usernameET, emailET, passwordET, confirmPasswordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button registerRegisterButton = findViewById(R.id.RegisterRegisterButton);
        TextView registerToLoginTV = findViewById(R.id.RegisterToLoginTV);

        usernameET = findViewById(R.id.RegisterUsernameET);
        emailET = findViewById(R.id.RegisterEmailET);
        passwordET = findViewById(R.id.RegisterPasswordET);
        confirmPasswordET = findViewById(R.id.RegisterConfirmPasswordET);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        registerToLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerToLogin = new Intent(RegisterPage.this, LoginActivity.class);
                startActivity(registerToLogin);
            }
        });

        registerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(RegisterPage.this);
                pd.setMessage("Please wait..");

                pd.show();

                String username = usernameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();

                if(username.trim().equals("")){
                    Toast.makeText(RegisterPage.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                } else if(password.length() < 6){
                    Toast.makeText(RegisterPage.this, "Password must have more than 5 characters", Toast.LENGTH_SHORT).show();
                } else if(!confirmPassword.equals(password)){
                    Toast.makeText(RegisterPage.this, "Password does not match", Toast.LENGTH_SHORT).show();
                } else {
                    register(username, email, password);
                }
            }
        });

    }

    private void register(String username, String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            DocumentReference userRef = db.collection("users").document(userid);

                            HashMap<String, Object> user = new HashMap<>();
                            user.put("userId", userid);
                            user.put("username", username);
                            user.put("timestamp", Timestamp.now());
                            user.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/instagram-clone-cb95e.appspot.com/o/placehodler.jpeg?alt=media&token=76601a29-7968-442f-a58c-f5d97aa3fd14");

                            userRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        pd.dismiss();
                                        Intent registerToHome = new Intent(RegisterPage.this, MainActivity.class);
                                        registerToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(registerToHome);
                                    }
                                }
                            });
                        } else {
                            pd.dismiss();
                            Toast.makeText(RegisterPage.this, "You can't register with these email and password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}