package com.w3care.pleasesave.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.w3care.pleasesave.Global.Global;
import com.w3care.pleasesave.R;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    String yes;
    public static FirebaseAuth mAuth;
    ImageView myView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
        setContentView(R.layout.activity_splash);
        Global.shpref = PreferenceManager.getDefaultSharedPreferences(Splash.this);
        yes=Global.shpref.getString("signin","");
        myView=(ImageView)findViewById(R.id.imageViewsplash);
        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void run() {
                if(yes.equals("yes")){

                    Intent i=new Intent(Splash.this, Main2Activity.class);
                    startActivity(i);
                  //  overridePendingTransition(R.anim.slidein, R.anim.slideout);
                    finish();
                }
                else if(yes.equals("no")){
                    SharedPreferences.Editor editor = Global.shpref.edit();
                    editor.clear();
                    editor.commit();
                    FirebaseAuth.getInstance().signOut();
                    getWindow().setExitTransition(new Explode());
                    Intent login_intent = new Intent(Splash.this, LoginActivity.class);
                    login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(login_intent);
                   // overridePendingTransition(R.anim.slidein, R.anim.slideout);
                    finish();
                }else {
                    SharedPreferences.Editor editor = Global.shpref.edit();
                    editor.clear();
                    editor.commit();
                    FirebaseAuth.getInstance().signOut();
                    Intent login_intent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(login_intent);
                   // overridePendingTransition(R.anim.slidein, R.anim.slideout);
                    finish();
                }



            }
        }, SPLASH_TIME_OUT);
    }
}
