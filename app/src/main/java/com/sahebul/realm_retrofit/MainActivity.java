package com.sahebul.realm_retrofit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.sahebul.realm_retrofit.Utilities.Utils;
import com.sahebul.realm_retrofit.adapters.EmployeeAdapter;
import com.sahebul.realm_retrofit.models.Employee;
import com.sahebul.realm_retrofit.networking.APIService;
import com.sahebul.realm_retrofit.networking.ApiUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    protected ListView employee_listview;

//    RealmConfiguration realmConfiguration;
    private Realm realm;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employee_listview = findViewById(R.id.employee_listview);
        mAPIService = ApiUtils.getAPIService();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);
        if(Utils.isOnline(MainActivity.this)){
            Toast.makeText(MainActivity.this, "Online Mode", Toast.LENGTH_LONG).show();
            getEmployee();
        }else {
            RealmResults<Employee> dataSet= realm.where(Employee.class).findAll();
            Toast.makeText(MainActivity.this, "Offline Mode", Toast.LENGTH_LONG).show();
            setEmployeeData(dataSet);
        }
    }
    public  void  getEmployee(){
        mAPIService.getEmployeeList().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(@NonNull Call<List<Employee>> call, @NonNull Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    List<Employee> employeeList = response.body();
                    realm = Realm.getDefaultInstance();

                    realm.beginTransaction();
                    realm.delete(Employee.class); // Remove older values first
                    realm.commitTransaction();

                    realm.beginTransaction();
                    List<Employee> employee = realm.copyToRealm(employeeList);
                    realm.commitTransaction();

                    setEmployeeData(employeeList);

                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong,Please try again", Toast.LENGTH_LONG).show();
            }
        });
    }
   public void setEmployeeData(List<Employee> data){
       EmployeeAdapter employeeAdapter=new EmployeeAdapter(MainActivity.this,data);
       employee_listview.setAdapter(employeeAdapter);
   }
}
