package api;


import java.util.List;

import model.Employee;
import model.EmployeeCUD;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    //to get all the employees
    @GET("employees")
    Call<List<Employee>> getAllEmployee();

    //to get employee on the basis of their id
    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empID);

    //to create or register
    @POST("create")
    Call<Void> registerEmployee (@Body EmployeeCUD emp);

    //update employee on the basis of id
    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId, @Body EmployeeCUD emp);

    //delete employee on the basis of id
    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID") int empId);
}
