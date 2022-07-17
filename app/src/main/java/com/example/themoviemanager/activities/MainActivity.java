package com.example.themoviemanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.themoviemanager.R;
import com.example.themoviemanager.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String mail,password;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        dialog=new Dialog(MainActivity.this);

        binding.buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mail=binding.editTextEmail.getText().toString().replaceAll("\\s","");
                password=binding.editTextPassword.getText().toString().replaceAll("\\s","");

                if(!isNetworkAvailable(MainActivity.this))
                {
                    dialog.setContentView(R.layout.no_internet_connection);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    Button buttonOkNoConnection=dialog.findViewById(R.id.buttonOkNoConnection);

                    buttonOkNoConnection.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

                else
                {
                    if (isValidMail(mail)&&isValidPassword(password))
                    {
                        dialog.setContentView(R.layout.loading);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                                startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                            }
                        }, 3000);
                    }
                    else if (mail.isEmpty() || password.isEmpty())
                    {
                        if(mail.isEmpty() && password.isEmpty())
                        {
                            binding.textInputLayoutEmail.setError("Please enter a valid email address");
                            binding.textInputLayoutEmail.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.acikKirmizi)));
                            binding.textInputLayoutPassword.setError("Please enter a valid password");
                            binding.textInputLayoutPassword.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.acikKirmizi)));
                        }
                        else if (password.isEmpty())
                        {
                            binding.textInputLayoutPassword.setError("Please enter a valid password");
                            binding.textInputLayoutPassword.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.acikKirmizi)));
                        }
                        else
                        {
                            binding.textInputLayoutEmail.setError("Please enter a valid email address");
                            binding.textInputLayoutEmail.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.acikKirmizi)));
                        }

                    }
                }
            }
        });

        binding.editTextEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (isValidMail(editable.toString()))
                {
                    binding.textInputLayoutEmail.setHelperText("Valid mail address");
                }
                else
                {
                    binding.textInputLayoutEmail.setError("Please enter a valid email address");
                    binding.textInputLayoutEmail.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.acikKirmizi)));
                }
            }
        });

        binding.editTextPassword.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (isValidPassword(editable.toString()))
                {
                    binding.textInputLayoutPassword.setHelperText("Valid password");
                }
                else
                {
                    binding.textInputLayoutPassword.setError("Please enter a valid password");
                    binding.textInputLayoutPassword.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.acikKirmizi)));
                }
            }
        });

        binding.imageViewInfoPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dialog.setContentView(R.layout.password_valid_info);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button buttonOkPasswordInfo=dialog.findViewById(R.id.buttonOkPasswordInfo);

                buttonOkPasswordInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public static boolean isValidMail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password)
    {
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[.#?!@$%^&*-]).{8,}$";

        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}