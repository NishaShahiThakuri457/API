package com.e.api;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

public class SearchEmployeeActivity extends AppCompatActivity {
    private static final String BaseURL ="http://dummy.restapiexample.com/api/v1/";
    private EditText  etID, etName, etSalary, etAge, etProfile;
    private Button btnSearch, btnUpdate, btnDelete;

    EmployeeAPI employeeAPI;
    AlertDialog.Builder alert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etID = findViewById(R.id.etEmpID);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        etAge = findViewById(R.id.etAge);
        etProfile = findViewById(R.id.etProfile);

        btnSearch= findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        alert = new AlertDialog.Builder(this);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateEmployee();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.setMessage("Do you want to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DeleteEmployee();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog =  alert.create();
                alertDialog.setTitle("Delete Employee");
                alertDialog.show();

            }
        });
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etID.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etName.setText(response.body().getEmployee_name());
                etSalary.setText(Float.toString(response.body().getEmployee_salary()));
                etAge.setText(Integer.toString(response.body().getEmployee_age()));
                etProfile.setText(response.body().getProfile_image());
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpdateEmployee(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        EmployeeCUD employee = new EmployeeCUD(
                etName.getText().toString(),
                Float.parseFloat(etSalary.getText().toString()),
                Integer.parseInt(etAge.getText().toString())
        );

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etID.getText().toString()), employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SearchEmployeeActivity.this, "Successfully updated",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SearchEmployeeActivity.this, "Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void DeleteEmployee(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etID.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SearchEmployeeActivity.this, "Successfully Deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SearchEmployeeActivity.this,"Error "+ t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
