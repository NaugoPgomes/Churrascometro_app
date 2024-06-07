package com.example.churrascometro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_passwordActivity extends AppCompatActivity
{

    FirebaseAuth auth;
    TextView email;
    AppCompatButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String thisEmail = email.getText().toString();
            auth.sendPasswordResetEmail(thisEmail);
            Toast toast = Toast.makeText(getApplicationContext(), "O Link para redefinir a senha foi enviado para o seu email",Toast.LENGTH_SHORT);
            toast.show();
        }
        });


    }

}
