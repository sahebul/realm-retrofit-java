package com.sahebul.realm_retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sahebul.realm_retrofit.MainActivity;
import com.sahebul.realm_retrofit.R;
import com.sahebul.realm_retrofit.models.Employee;

import java.util.List;

/**
 * Created by Sahebul on 27/4/19.
 */
public class EmployeeAdapter extends BaseAdapter {
    Context context;
    private LayoutInflater inflater;
    List<Employee> data;

    public EmployeeAdapter(Context context,List<Employee> data) {
        this.data=data;
        this.context=context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView=inflater.inflate(R.layout.single_employee,null);
        }
        TextView empName=(TextView)convertView.findViewById(R.id.tvEmpName);
        empName.setText(data.get(position).getEmployeeName());
        return  convertView;
    }
}
