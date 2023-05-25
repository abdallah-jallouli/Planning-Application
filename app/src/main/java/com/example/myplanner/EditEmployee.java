package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myplanner.Fragments.ArticleFragment;
import com.example.myplanner.Fragments.EquipeFragment;

import java.util.ArrayList;
import java.util.List;

import Controller.DatabaseHandler;
import Models.Employee;
import Models.Team;

public class EditEmployee extends AppCompatActivity {

    private EditText e_name , e_fname , e_phone ;
    private Spinner e_spinner ;
    private Button save;
    private DatabaseHandler databaseHandler;
    List<Team> teamList ;
    Employee employee ;
    int position;
    String str_position;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        e_name = findViewById(R.id.e_edit_name2);
        e_fname = findViewById(R.id.e_edit_first_name2);
        e_phone = findViewById(R.id.e_edit_phone2);
        e_spinner = findViewById(R.id.teamSpinner2);
        save = findViewById(R.id.e_save_btn2);

        teamList = new ArrayList<>();
        final Team[] selectedTeam = {new Team()};
        databaseHandler = new DatabaseHandler(this);


        // array list for the spinner
        // containt list of teams name
        teamList = databaseHandler.getAllTeam();
        List<String> teamNames = new ArrayList<>();
        for (Team team : teamList) {
            teamNames.add(team.getT_name());
        }

        // set the Adapter of the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, teamNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        e_spinner.setAdapter(adapter);


        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        position = Integer.parseInt(str_position);
        databaseHandler = new DatabaseHandler(this);
        employee = databaseHandler.getEmployee(position);


        if(employee != null){
            e_name.setText(employee.getE_name());
            e_fname.setText(employee.getE_first_name());
            e_phone.setText(employee.getE_phone_number());
            e_spinner.setAdapter(adapter);
        }


        e_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected team object
                selectedTeam[0] = teamList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employee.setE_name(e_name.getText().toString());
                employee.setE_first_name(e_fname.getText().toString());
                employee.setE_phone_number(e_phone.getText().toString());
                employee.setTeam(selectedTeam[0]);




                databaseHandler.updateEmployee(employee);
                EquipeFragment.notifyAdapter();
//                Intent i = new Intent(EditEmployee.this, Interface_Chef.class);
//                startActivity(i);
                finish();
            }
        });






    }
}