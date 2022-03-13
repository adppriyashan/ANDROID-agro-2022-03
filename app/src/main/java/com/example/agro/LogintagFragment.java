package com.example.agro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class LogintagFragment extends Fragment {

    TextView forget;
    EditText email,password;
    Button login;
    float v=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.login_fragment,container,false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        forget = root.findViewById(R.id.forget);
        login = root.findViewById(R.id.button);

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
        return root;
    }
}
