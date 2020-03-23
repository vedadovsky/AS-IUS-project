package com.example.ius;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static java.lang.Thread.sleep;

public class ProfileFragment extends Fragment {
    private ProgressDialog dialog;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile,container,false);
        ImageView edit = v.findViewById(R.id.editText);
        final TextView username1 = v.findViewById(R.id.changeUsername);
        final String usernameS1 = username1.getText().toString();
        final TextView password1 = v.findViewById(R.id.changePass);
        final String passwordS1 = password1.getText().toString();
        final TextView comP1 = v.findViewById(R.id.changePassConfirm);
        final String comSP1 = comP1.getText().toString();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameS1.length()<=2){
                    username1.setError("Username must be 3 or more characters long");
                    username1.requestFocus();
                    return;
                }
                if(passwordS1.length()<=5){
                    password1.setError("Password must be 6 or more characters long");
                    password1.requestFocus();
                    return;
                }
                if(comSP1.isEmpty()){
                    comP1.setError("Please Confirm password");
                    comP1.requestFocus();
                    return;
                }
                if(!(passwordS1.equals(comSP1))){
                    comP1.setError("The Confirm Password confirmation does not match");
                    comP1.requestFocus();
                    return;
                }
                dialog.setMessage("Editing profile...");
                dialog.show();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        });

        return v;
    }
}
