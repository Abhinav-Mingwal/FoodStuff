package com.example.abhinav_pc.foodstuff;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
public class LoginActivity extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    Button login;
    Button registerr;
    EditText enterpassword,enterUsername;
    TextView forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button)findViewById(R.id.login_btn_login);
        registerr=(Button)findViewById(R.id.register_btn_login);
        enterpassword=(EditText)findViewById(R.id.password_edt);
        enterUsername=(EditText)findViewById(R.id.username_edit);
        forgetpass=(TextView)findViewById(R.id.forgetpassLogin);

        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter.open();

        registerr.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent i=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                String Username =enterUsername.getText().toString();
                String Password=enterpassword.getText().toString();
                UserPass storedUser=loginDataBaseAdapter.getSinlgeEntry(new UserPass(Username,Password));


                if(Password.equals(storedUser.Password)&&Username.equals(storedUser.username))
                {
                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
                    Intent ii=new Intent(LoginActivity.this,MainActivityTabbed.class);
                    startActivity(ii);
                    finish();
                }
                else
                if(Password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter Your Password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

        forgetpass.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forget_search);
                dialog.show();

                final EditText security=(EditText)dialog.findViewById(R.id.securityhint_edt);
                final TextView getpass=(TextView)dialog.findViewById(R.id.textView3);

                Button ok=(Button)dialog.findViewById(R.id.getpassword_btn);
                Button cancel=(Button)dialog.findViewById(R.id.cancel_btn);

                ok.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        String hint=security.getText().toString();
                        if(hint.equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Please enter your securityhint", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String storedPassword=loginDataBaseAdapter.getAllTags(hint);
                            if(storedPassword==null)
                            {
                                Toast.makeText(getApplicationContext(), "Please enter correct securityhint", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.d("GET PASSWORD",storedPassword);
                                getpass.setText(storedPassword);
                            }
                        }
                    }
                });
                cancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
// TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }

}
