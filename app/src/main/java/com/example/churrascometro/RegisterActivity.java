package com.example.churrascometro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity
{

    FirebaseAuth user;

    EditText registrationEmail;
    EditText registrationPassword;
    EditText confirmRegistrationPassword;
    AppCompatButton buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        user = FirebaseAuth.getInstance();

        registrationEmail = findViewById(R.id.registrationEmail);
        registrationPassword = findViewById(R.id.registrationPassword);
        confirmRegistrationPassword = findViewById(R.id.confirmRegistrationPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                registerUser();
            }
        });

    }

    private void registerUser()
    {
        String Email = registrationEmail.getText().toString();
        String Password = registrationPassword.getText().toString();
        String ConfirmPassword = confirmRegistrationPassword.getText().toString();


        if(Email.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty())
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Email, Senha  e Confirmar senha Não podem estar vazios",Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Carregando...");
            progressDialog.show();

            if(Password.equals(ConfirmPassword))
            {
                user.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
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
            else
            {
                progressDialog.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(),"Os dois campos de senha tem que ser iguais",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private void startActivityMain()
    {
        Intent intentE = new Intent(this, MainActivity.class);
        startActivity(intentE);
    }
}