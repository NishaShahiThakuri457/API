package com.e.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.EmployeeAPI;
import model.EmployeeCUD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {
    private static final String BaseURL ="http://dummy.restapiexample.com/api/v1/";
    private EditText etName, etSalary, etAge;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        etAge = findViewById(R.id.etAge);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
    }
    private void Register(){
        String name;
        int age;
        float salary;

        name = etName.getText().toString();
        age =Integer.parseInt(etAge.getText().toString());
        salary =Float.parseFloat(etSalary.getText().toString());

        EmployeeCUD employee = new EmployeeCUD(name, salary, age);

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI  = retrofit.create(EmployeeAPI.class);

        Call<Void> voidCall = employeeAPI.registerEmployee(employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegistrationActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this,"Error" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();


            }
        });


    }
}
