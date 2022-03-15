package com.example.agro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;


public class LogintagFragment extends Fragment {

    TextView forget;

    @NotEmpty
    @Email
    EditText email;

    @NotEmpty
    EditText password;

    Button login;
    float v=0;

    Validator validator;

    private FirebaseAuth mAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        validator = new Validator(this);
        validator.setValidationListener(inflater.getContext());

        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.login_fragment,container,false);

        email = root.findViewById(R.id.login_email);
        password = root.findViewById(R.id.login_password);
        forget = root.findViewById(R.id.login_recover_password);
        login = root.findViewById(R.id.login_button);

        email.setTranslationY(800);
        password.setTranslationY(800);
        forget.setTranslationY(800);
        login.setTranslationY(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forget.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();



        loginInitialize(inflater.getContext());


        return root;
    }

    private void loginInitialize(Context context) {
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && currentUser == null){
                        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mAuth.getCurrentUser();
                                        } else {
                                            Toast.makeText(context, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(context, "Something wrong, Please restart the app.", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception ex){
                    Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
