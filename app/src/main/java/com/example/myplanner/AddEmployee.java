package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myplanner.Fragments.ArticleFragment;
import com.example.myplanner.Fragments.EquipeFragment;

import java.util.ArrayList;
import java.util.List;
import Controller.DatabaseHandler;
import Models.ArticleModel;
import Models.Employee;
import Models.Team;

public class AddEmployee extends AppCompatActivity {


    EditText e_name , e_Fname , e_phone  ;
    Button e_save ;
    Spinner team_spinner ;
    List<Team> teamList ;
    private DatabaseHandler db ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);



        e_name = findViewById(R.id.e_edit_name);
        e_Fname = findViewById(R.id.e_edit_first_name);
        e_phone = findViewById(R.id.e_edit_phone);
        //e_position = findViewById(R.id.e_edit_position);
        e_save = findViewById(R.id.e_save_btn);
        team_spinner = findViewById(R.id.teamSpinner);
        teamList = new ArrayList<>();
        db = new DatabaseHandler(this);
        final Team[] selectedTeam = {new Team()};


        //fetchTeamsFromDatabase();
        teamList = db.getAllTeam();
        List<String> teamNames = new ArrayList<>();
        for (Team team : teamList) {
            teamNames.add(team.getT_name());
        }

        // Set up the spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, teamNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team_spinner.setAdapter(adapter);

        team_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected team object
                selectedTeam[0] = teamList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_emp = e_name.getText().toString();
                String fname_emp = e_Fname.getText().toString();
                String phone = e_phone.getText().toString();

                db.addEmployee(new Employee(name_emp,fname_emp,phone, selectedTeam[0]));
                //Toast.makeText(Add_Article.this, "Successfull", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}