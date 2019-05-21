package com.e.api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.EmployeeAPI;
import model.Employee;
import model.EmployeeCUD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteEmployeeActivity extends AppCompatActivity {

    private final static String BASEURL ="http://dummy.restapiexample.com/api/v1/";
    private EditText etName, etAge, etSalary, etId;
    private Button btnSearch, btnDelete, btnUpdate;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_employee);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        etAge = findViewById(R.id.etAge);
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
                clear();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();

            }
        });
    }




    private void CreateInstance(){

        retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create()).build();


        employeeAPI = retrofit.create(EmployeeAPI.class);
    }

    private void loadData(){

        CreateInstance();
        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etId.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etName.setText(response.body().getEmployee_name());
                etSalary.setText(Float.toString(response.body().getEmployee_salary()));
                etAge.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(UpdateDeleteEmployeeActivity.this,"Error " +
                        t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }

    private void updateEmployee(){

        CreateInstance();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        EmployeeCUD employee = new EmployeeCUD(
                etName.getText().toString(),
                Float.parseFloat(etSalary.getText().toString()),
                Integer.parseInt(etAge.getText().toString())
        );

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etId.getText().toString()), employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteEmployeeActivity.this, "Successfully updated",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteEmployeeActivity.this, "Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Intent intent = new Intent(UpdateDeleteEmployeeActivity.this,DashboardActivity.class);
        startActivity(intent);
    }



    private void deleteEmployee(){


        CreateInstance();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etId.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteEmployeeActivity.this, "Successfully Deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteEmployeeActivity.this,"Error "+ t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(UpdateDeleteEmployeeActivity.this,DashboardActivity.class);
        startActivity(intent);


    }


    private void clear(){

        etId.setText("");
        etName.setText("");
        etSalary.setText("");
        etAge.setText("");

    }



}