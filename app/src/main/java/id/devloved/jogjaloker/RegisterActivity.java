package id.devloved.jogjaloker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText regEmail, regPassword, regConPassword;
    Button btnReg;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        regEmail = (EditText) findViewById(R.id.registerEmail);
        regPassword = (EditText) findViewById(R.id.registerPassword);
        regConPassword = (EditText) findViewById(R.id.registerConfirmPassword);
        btnReg = (Button) findViewById(R.id.btnRegister);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();
                String conPassword = regConPassword.getText().toString();

                if (!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(conPassword)){

                    if (password.equals(conPassword)){


                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    Intent settingIntent=new Intent(RegisterActivity.this,SettingActivity.class);
                                    startActivity(settingIntent);
                                    finish();

                                }else {

                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this,"Error : "+errorMessage,Toast.LENGTH_LONG).show();

                                }


                            }
                        });

                    }else{

                        Toast.makeText(RegisterActivity.this,"Silakan cek ulang password anda",Toast.LENGTH_LONG).show();

                    }


                }else {

                    Toast.makeText(RegisterActivity.this,"Form tidak boleh kosong",Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser=mAuth.getCurrentUser();
        if (currentUser != null){

            sendToMain();

        }
    }

    private void sendToMain() {
        Intent mainIntent=new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
