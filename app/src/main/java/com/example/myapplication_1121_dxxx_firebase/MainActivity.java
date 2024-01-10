package com.example.myapplication_1121_dxxx_firebase;

import static android.content.ContentValues.TAG;

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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText e1,p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //
      e1   =(EditText) findViewById(R.id.editTextTextEmailAddress);
      p1=(EditText)findViewById(R.id.editTextTextPassword);
      p2=(EditText)findViewById(R.id.editTextTextPassword2);
     Button btn=(Button) findViewById(R.id.button);
     Button btn2=(Button) findViewById(R.id.button2);
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
           //
             mAuth.createUserWithEmailAndPassword(e1.getText().toString(), p1.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 // Sign in success, update UI with the signed-in user's information
                                 Log.d(TAG, "createUserWithEmail:success");
                                 Toast.makeText(MainActivity.this, "createUserWithEmail:success",
                                         Toast.LENGTH_LONG).show();
                                 FirebaseUser user = mAuth.getCurrentUser();
                                 //
                                 Intent it=new Intent();
                                 it.setClass(MainActivity.this,MainActivity3.class);
                                 it.putExtra("acc",e1.getText().toString());
                                 it.putExtra("pwd",p1.getText().toString());
                                 it.putExtra("userid",user.getUid());
                                 startActivity(it);
                                 //
                             } else {
                                 // If sign in fails, display a message to the user.
                                 Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                 Toast.makeText(MainActivity.this, "Authentication failed.",
                                         Toast.LENGTH_SHORT).show();
                                 Toast.makeText(MainActivity.this, "createUser:failure:"+task.getException(),
                                         Toast.LENGTH_LONG).show();

                             }
                         }
                     });






             //
         }
     });
     btn2.setOnClickListener(new View.OnClickListener() {
         @Override

         public void onClick(View view) {
             Intent it=new Intent();
             it.setClass(MainActivity.this,MainActivity2.class);
             it.putExtra("acc",e1.getText().toString());
             it.putExtra("pwd",p1.getText().toString());
             startActivity(it);

         }
     });


    }

}