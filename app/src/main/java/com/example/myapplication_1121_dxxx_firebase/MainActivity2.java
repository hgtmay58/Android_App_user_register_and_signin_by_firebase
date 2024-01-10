package com.example.myapplication_1121_dxxx_firebase;

import static android.content.ContentValues.TAG;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText acc,pwd;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Intent it=this.getIntent();
        String accs=it.getStringExtra("acc");
        String pwds=it.getStringExtra("pwd");

        setContentView(R.layout.activity_main2);
        acc=(EditText)findViewById(R.id.editTextTextEmailAddress);
        pwd=(EditText)findViewById(R.id.editTextTextPassword);
        acc.setText(accs);
        pwd.setText(pwds);
        btn=(Button)findViewById(R.id.buttonlogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accs=acc.getText().toString();
                String pwds=pwd.getText().toString();
                signIn(accs, pwds);
            }
        });

    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity2.this, "signInWithEmail:success",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //user.getUid()
                            Intent it3=new Intent();
                            it3.setClass(MainActivity2.this,MainActivity3.class);
                            //

                            it3.putExtra("acc",email);
                            it3.putExtra("pwd",password);
                            it3.putExtra("userid",user.getUid());

                            //
                            startActivity(it3);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity2.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }



                    }
                });
    }
}