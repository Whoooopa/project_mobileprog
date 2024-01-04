package com.example.mobprogproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobprogproject.model.UserModel;
import com.example.mobprogproject.utils.AndroidUtil;
import com.example.mobprogproject.utils.FirebaseUtil;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ProfileFragment extends Fragment {

    ImageView profilePicIV;
    EditText usernameInputET;
    Button updateProfileBtn;
    ProgressBar progressBar;
    TextView logoutBtn;

    UserModel currentUserModel;
    ActivityResultLauncher<Intent> imagePickLauncher;
    Uri selectedImageUri;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data!=null && data.getData()!=null){
                            selectedImageUri = data.getData();
                            AndroidUtil.setProfilePic(getContext(),selectedImageUri,profilePicIV);
                        }
                    }
                }
                );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePicIV = view.findViewById(R.id.profile_image_view);
        usernameInputET = view.findViewById(R.id.profile_username);
        updateProfileBtn = view.findViewById(R.id.profile_update_btn);
        logoutBtn = view.findViewById(R.id.logout_btn);
        progressBar = view.findViewById(R.id.profile_progress_bar);

        getUserData();

        updateProfileBtn.setOnClickListener((v -> {
            updateBtnClick();
        }));

        logoutBtn.setOnClickListener((v)->{

            FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    FirebaseUtil.logout();
                    Intent profileToLogin = new Intent(getContext(), SplashActivity.class);
                    profileToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(profileToLogin);
                }
            });
        });



        profilePicIV.setOnClickListener((v)->{
            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512, 512)
                    .createIntent(new Function1<Intent, Unit>() {
                        @Override
                        public Unit invoke(Intent intent) {
                            imagePickLauncher.launch(intent);
                            return null;
                        }
                    });
        });

        return view;
    }

    void getUserData(){
        setInProgress(true);

        FirebaseUtil.getCurrentProfilePicStorageRef().getDownloadUrl()
                        .addOnCompleteListener(task ->{
                            if(task.isSuccessful()){
                                Uri uri = task.getResult();
                                AndroidUtil.setProfilePic(getContext(), uri, profilePicIV);
                            }
                        });
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {
            setInProgress(false);
            currentUserModel = task.getResult().toObject(UserModel.class);
            usernameInputET.setText(currentUserModel.getUsername());
        });
    }

    void updateBtnClick(){
        String newUsername = usernameInputET.getText().toString();
        if(newUsername.isEmpty() || newUsername.length()<3){
            usernameInputET.setError("Username length should be at least 3 chars");
            return;
        }
        currentUserModel.setUsername(newUsername);
        setInProgress(true);

        if(selectedImageUri!=null){
            FirebaseUtil.getCurrentProfilePicStorageRef().putFile(selectedImageUri)
                    .addOnCompleteListener(task -> {
                        updateToFirestore();
                    });
        }else{
            updateToFirestore();
        }

    }

    void updateToFirestore(){
        FirebaseUtil.currentUserDetails().set(currentUserModel)
                .addOnCompleteListener(task -> {
                    setInProgress(false);
                    if(task.isSuccessful()){
                        AndroidUtil.showToast(getContext(),"Updated successfully");
                    }else{
                        AndroidUtil.showToast(getContext(),"Updated failed");
                    }
                });
    }



    void setInProgress(boolean inProgress){

        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            updateProfileBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}