package com.vazheninapps.testemployees.screens.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.vazheninapps.testemployees.R;
import com.vazheninapps.testemployees.adapters.EmployeeAdapter;
import com.vazheninapps.testemployees.pojo.Employee;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity implements EmployeeListView {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeListPresenter presenter;


    private void initItems() {
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployes);
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmployeeAdapter();
        recyclerViewEmployees.setAdapter(adapter);
        presenter = new EmployeeListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItems();
        presenter.loadData();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disposeDisposable();
    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }
}
