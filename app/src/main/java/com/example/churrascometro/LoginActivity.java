package com.example.churrascometro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{

    FirebaseAuth user;

    EditText email;
    EditText password;
    AppCompatButton button;
    TextView registrationScreenLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        user = FirebaseAuth.getInstance();

        checksIfTheUserIsLoggedIn();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button = findViewById(R.id.button);
        registrationScreenLogin = findViewById(R.id.registrationScreenLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        registrationScreenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentE = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentE);
            }
        });
    }

    private void checksIfTheUserIsLoggedIn()
    {
        if(user.getCurrentUser() != null)
        {
            startActivityMain();
        }
    }

    private void login()
    {
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if(Email.isEmpty() || Password.isEmpty())
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Email e senha Não podem estar vazios",Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Carregando...");
            progressDialog.show();

            user.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task task) {
                    progressDialog.dismiss();

                    if(task.isSuccessful())
                    {
                        startActivityMain();
                        finish();
                    }
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), task.getException().toString(),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            });
        }
    }

    private void startActivityMain()
    {
        Intent intentE = new Intent(this, MainActivity.class);
        startActivity(intentE);
    }
}