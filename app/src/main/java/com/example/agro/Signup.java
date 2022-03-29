package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agro.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class Signup extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty
    private EditText registerName;

    @NotEmpty
    @Email
    private EditText registerEmail;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText registerPassword;

    @ConfirmPassword
    private EditText registerRetypePassword;

    private TextView registerBackLogin;

    private Button registerSignupButton;

    private Validator validator;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initProcess();
    }

    private void initProcess() {
        registerName=findViewById(R.id.registerName);
        registerEmail=findViewById(R.id.registerEmail);
        registerPassword=findViewById(R.id.registerPassword);
        registerRetypePassword=findViewById(R.id.registerPasswordConfirmation);

        registerBackLogin=findViewById(R.id.registerBackLogin);
        registerBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registerSignupButton=findViewById(R.id.registerButton);
        registerSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        validator = new Validator(this);
        validator.setValidationListener(this);

        mAuth = FirebaseAuth.getInstance();

        loading = new ProgressDialog(Signup.this);
        loading.setMessage("Loading..");
        loading.setTitle("Please wait, Registration in progress.");

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onValidationSucceeded() {
        loading.show();
        mAuth.createUserWithEmailAndPassword(registerEmail.getText().toString(), registerPassword.getText().toString() ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                User user = new User(registerName.getText().toString(), registerEmail.getText().toString());
                mDatabase.child("users").child(task.getResult().getUser().getUid()).setValue(user);

                registerName.setText("");
                registerEmail.setText("");
                registerPassword.setText("");
                registerRetypePassword.setText("");

                loading.hide();

                Toast.makeText(Signup.this, "Registration complete. Please login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            if (view instanceof EditText) {
                ((EditText) view).setError(error.getCollatedErrorMessage(Signup.this));
            }
        }
    }
}