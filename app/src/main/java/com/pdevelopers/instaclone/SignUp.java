package com.pdevelopers.instaclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText UsernameSignUp,UsernameLogIn, PasswordSignUp, PasswordLogIn;
    private Button LogInBtn, SignUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        UsernameSignUp = findViewById(R.id.UsernameSignUp);
        UsernameLogIn = findViewById(R.id.UsernameLogIn);
        PasswordSignUp = findViewById(R.id.PasswordSignUp);
        PasswordLogIn = findViewById(R.id.PasswordLogIn);
        LogInBtn = findViewById(R.id.LogInBtn);
        SignUpBtn = findViewById(R.id.SignUpBtn);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser user = new ParseUser();
                user.setUsername(UsernameSignUp.getText().toString());
                user.setPassword(PasswordSignUp.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUp.this, user.get("username")+" is signed up successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            UsernameSignUp.setText("");
                            PasswordSignUp.setText("");
                        } else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });

        LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(UsernameLogIn.getText().toString(), PasswordLogIn.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e==null) {
                            FancyToast.makeText(SignUp.this, user.get("username")+" is logged in successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            UsernameLogIn.setText("");
                            PasswordLogIn.setText("");
                        } else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        }); 
    }
}
