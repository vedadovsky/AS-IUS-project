package com.example.ius;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public TextView email;
    public TextView password;
    FirebaseAuth firebaseAuth;
    boolean check;
    ProgressDialog dialog;
    FirebaseUser user;

    //Validation//
    public void signIn(View view){
        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        String passwordS = password.getText().toString();
        String emailS = email.getText().toString();

        if(!isConnected()){
            new AlertDialog.Builder(this).setIcon(R.drawable.ic_internet).setTitle("Internet Connection Alert").setMessage("Please Check your Internet Connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }

        if(emailS.isEmpty()){
            email.setError("Please enter valid Email");
            email.requestFocus();
            return;
        }
        if(passwordS.length()<=5){
            password.setError("Password must be 6 or more characters long");
            password.requestFocus();
            return;
        }


        //Kad radi//
        dialog.setMessage("Logging in");
        dialog.show();

        firebaseAuth.signInWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(MainActivity.this,MainPub.class));
                        dialog.dismiss();
                }
                else {
                        Toast.makeText(MainActivity.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage() ,Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });
    }

    //Registracija clickable word//
    public void registracijaMetoda(View view){
        openRegistration();
    }

    public void openRegistration() {
        Intent intent = new Intent(this,Registracija.class);
        startActivity(intent);
    }

    //Forgot password clickable word//
    public void zabPass(View view){

        openZabPass();
    }
    public void openZabPass() {
        Intent intent = new Intent(this,ForgotPassword.class);
        startActivity(intent);
    }

    //Clickable image easter egg//
    public void ius(View view){
        Toast.makeText(this, "Your future now!", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);

       if(!isConnected()){
           new AlertDialog.Builder(this).setIcon(R.drawable.ic_internet).setTitle("Internet Connection Alert").setMessage("Please Check your Internet Connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   finish();
               }
           }).show();
       }

    }
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}

