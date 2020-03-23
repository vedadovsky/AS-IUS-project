package com.example.ius;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import static com.example.ius.R.id.emailTxtForgotPass;

public class ForgotPassword extends AppCompatActivity {
TextView email;
FirebaseAuth firebaseAuth;
boolean check;
TextView username;
ProgressBar bar;

    //Send button//
    public void send(View view){
     String emailS = email.getText().toString();
     String usernameS = username.getText().toString();
        firebaseAuth = FirebaseAuth.getInstance();
        bar.setVisibility(View.VISIBLE);

        //Ako nesta ne moze
        if(!isConnected()){
            new AlertDialog.Builder(this).setIcon(R.drawable.ic_internet).setTitle("Internet Connection Alert").setMessage("Please Check your Internet Connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
        if(usernameS.length()<=2){
            username.setError("Username must be 3 or more characters long");
            username.requestFocus();
            bar.setVisibility(View.INVISIBLE);
            return;
        }
        if(emailS.isEmpty()){
            email.setError("Please enter valid Email");
            bar.setVisibility(View.INVISIBLE);
            email.requestFocus();
            return;
        }
        //Kad radi//

        firebaseAuth.sendPasswordResetEmail(emailS).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPassword.this, "Password sent to your email", Toast.LENGTH_LONG).show();
                    bar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(ForgotPassword.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    bar.setVisibility(View.INVISIBLE);
                }
            }}

        );}

    //Clickable image easter egg//
    public void ius(View view){
        Toast.makeText(this, "Your future now!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        bar = findViewById(R.id.progressBar);
        email = findViewById(R.id.emailTxtForgotPass);
        username = findViewById(R.id.userNameTxt);
    }
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }


}
