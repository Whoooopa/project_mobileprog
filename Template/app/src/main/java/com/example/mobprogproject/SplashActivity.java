package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mobprogproject.model.UserModel;
import com.example.mobprogproject.utils.AndroidUtil;
import com.example.mobprogproject.utils.FirebaseUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(FirebaseUtil.isloggedIn() && getIntent().getExtras()!=null){

            String userId = getIntent().getExtras().getString("userId");
            FirebaseUtil.allUserColectionReference().document(userId).get()
                    .addOnCompleteListener(task -> {
                       if(task.isSuccessful()){
                           UserModel model = task.getResult().toObject(UserModel.class);
                           Intent mainIntent = new Intent(this, MainActivity.class);
                           mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                           startActivity(mainIntent);

                           Intent intent = new Intent(this, ChatActivity.class);
                           AndroidUtil.passUserModelAsIntent(intent,model);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);
                           finish();
                       }
                    });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (FirebaseUtil.isloggedIn()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                }
            }, 1000);
        }



    }
}