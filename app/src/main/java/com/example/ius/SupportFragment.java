package com.example.ius;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SupportFragment extends Fragment {

    @Nullable
    ImageView vedoIg;
    ImageView vedoFb;
    ImageView vedoGm;
    ImageView lexIg;
    ImageView lexFb;
    ImageView lexGm;
    ImageView talicIg;
    ImageView talicFb;
    ImageView talicGm;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_support,container,false);

        // vedo
        vedoIg = v.findViewById(R.id.vedo_ig);
        vedoFb = v.findViewById(R.id.vedo_fb);
        vedoGm = v.findViewById(R.id.vedo_gm);

        vedoIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/vedadovsky");
                Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
                instagram.setPackage("com.instagram.android");
                try {
                    startActivity(instagram);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/vedadovsky")));
                }
            }
        });

        vedoGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:vedadovsky13@gmail.com");
                Intent gmail = new Intent(Intent.ACTION_VIEW, uri);
                gmail.setPackage("com.gmail.android");
                try {
                    startActivity(gmail);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:vedadovsky13@gmail.com")));
                }
            }
        });

        vedoFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.facebook.com/people/Vedad-Jahjaefendic/100008315001674");
                Intent facebook = new Intent(Intent.ACTION_VIEW, uri);
                facebook.setPackage("com.facebook.android");
                try {
                    startActivity(facebook);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/people/Vedad-Jahjaefendic/100008315001674")));
                }
            }
        });


        //Lex

        lexIg = v.findViewById(R.id.lex_ig);
        lexFb = v.findViewById(R.id.lex_fb);
        lexGm = v.findViewById(R.id.lex_gm);

        lexIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/lexon1304");
                Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
                instagram.setPackage("com.instagram.android");
                try {
                    startActivity(instagram);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/lexon1304")));
                }
            }
        });

        lexGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:Lejla1304@gmail.com");
                Intent gmail = new Intent(Intent.ACTION_VIEW, uri);
                gmail.setPackage("com.gmail.android");
                try {
                    startActivity(gmail);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:lejla1304@gmail.com")));
                }
            }
        });

        lexFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.facebook.com/lejla.smaji");
                Intent facebook = new Intent(Intent.ACTION_VIEW, uri);
                facebook.setPackage("com.facebook.android");
                try {
                    startActivity(facebook);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/lejla.smaji")));
                }
            }
        });

        //Matmazel

        talicIg = v.findViewById(R.id.talic_ig);
        talicFb = v.findViewById(R.id.talic_fb);
        talicGm = v.findViewById(R.id.talic_gm);

        talicIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/matmazel_talic");
                Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
                instagram.setPackage("com.instagram.android");
                try {
                    startActivity(instagram);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/matmazel_talic")));
                }
            }
        });

        talicGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:merjem.talic.09@gmail.com");
                Intent gmail = new Intent(Intent.ACTION_VIEW, uri);
                gmail.setPackage("com.gmail.android");
                try {
                    startActivity(gmail);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:merjem.talic.09@gmail.com")));
                }
            }
        });

        talicFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.facebook.com/merjem.talich");
                Intent facebook = new Intent(Intent.ACTION_VIEW, uri);
                facebook.setPackage("com.facebook.android");
                try {
                    startActivity(facebook);
                }
                catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/merjem.talich")));
                }
            }
        });



        return v;
    }

   }
