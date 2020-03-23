package com.example.ius;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Registracija extends AppCompatActivity {
TextView username;
TextView email;
TextView password;
TextView comPassword;
private ProgressDialog dialog;
FirebaseAuth firebaseAuth;
private boolean check;
static int returner =0;
//Dugme registracije//
    public void register(View view){

        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        final String usernameS = username.getText().toString();
        final String emailS = email.getText().toString();
        String passwordS = password.getText().toString();
        String comPasswordS = comPassword.getText().toString();
        Query userQuery = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("username").equalTo(usernameS);




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
           return;
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
       if(comPasswordS.isEmpty()){
           comPassword.setError("Please Confirm password");
           comPassword.requestFocus();
            return;
        }
       if(!(passwordS.equals(comPasswordS))){
           comPassword.setError("The Confirm Password confirmation does not match");
           comPassword.requestFocus();
           return;
       }

       firebaseAuth.fetchSignInMethodsForEmail(emailS).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
           @Override
           public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
               check = !task.getResult().getSignInMethods().isEmpty();

           }
       });
        if(check){
            email.setError("Email already exists");
            email.requestFocus();
            dialog.dismiss();
            return;
        }

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()>0) {
                 returner=1;
                }
                else{
                    returner=0;
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

if(returner==1){
    username.setError("Username already exists");
    username.requestFocus();
}
else{
       //Kad moze
        dialog.setMessage("Registering User...");
       dialog.show();

        firebaseAuth.createUserWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()) {
                                       String user_id = firebaseAuth.getCurrentUser().getUid();
                                       DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("User").child(user_id);
                                       Map newPost = new HashMap();
                                       newPost.put("username",usernameS);
                                       newPost.put("email",emailS);
                                       current_user_db.setValue(newPost);

                                       Toast.makeText(Registracija.this, "Registered successfully, Please verify your email", Toast.LENGTH_SHORT).show();
                                       dialog.dismiss();
                                       startActivity(new Intent(Registracija.this,MainActivity.class));
                                   }
                               }
                           });
               }
               else {
                   Toast.makeText(Registracija.this, "An error occurred please try again", Toast.LENGTH_SHORT).show();
                   dialog.dismiss();
                   return;
               }
           }
       });}

    }


    //Easter egg
    public void ius(View view){

        Toast.makeText(this, "Your future now!", Toast.LENGTH_SHORT).show();
    }

    //Terms of service
    public void termsOfService(View view){
        openTerms();
    }
    public void openTerms() {
        Intent intent = new Intent(this,TermsOfService.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        username = findViewById(R.id.usernameTxt);
        email = findViewById(R.id.emailTxtRegistracija);
        password = findViewById(R.id.passwordTxt);
        comPassword =findViewById(R.id.comfirmPassTxt);

        CheckBox mCheckBox= findViewById( R.id.checkBox);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public Button mButton=findViewById( R.id.registerBut);
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mButton.setEnabled(isChecked);
            }});


    }
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
