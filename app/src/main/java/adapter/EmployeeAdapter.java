package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.api.R;

import java.util.List;

import model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{
    Context mContext;
    List<Employee> employeesList;

    public EmployeeAdapter(Context mContext, List<Employee> employeesList) {
        this.mContext = mContext;
        this.employeesList = employeesList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee,viewGroup,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
        Employee employee = employeesList.get(i);
        employeeViewHolder.name.setText(employee.getEmployee_name());
        employeeViewHolder.age.setText(String.valueOf(employee.getEmployee_age()));
        employeeViewHolder.salary.setText(String.valueOf(employee.getEmployee_salary()));
        employeeViewHolder.profile.setText(employee.getProfile_image());


    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }


    public class EmployeeViewHolder extends RecyclerView.ViewHolder{

        private TextView name,salary,age,profile;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvEmployeename);
            salary = itemView.findViewById(R.id.tvSalary);
            age = itemView.findViewById(R.id.tvAge);
            profile = itemView.findViewById(R.id.tvProfile);

        }
    }

}