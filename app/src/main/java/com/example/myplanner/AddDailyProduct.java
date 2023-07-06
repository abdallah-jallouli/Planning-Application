package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Controller.DatabaseHandler;
import Models.ArticleModel;
import Models.Employee;
import Models.ScheduledArticle;
import Models.Team;

public class Add_Scheduled_Article extends AppCompatActivity {

    private EditText numb_prep ;
    Spinner article_spinner , team_spinner ;
    Button save ;
    List<Team> teamList ;
    List<ArticleModel> articleList;
    private DatabaseHandler db ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheduled_article);

        article_spinner = findViewById(R.id.s_spin_art);
        team_spinner = findViewById(R.id.s_spin_team);
        numb_prep = findViewById(R.id.s_numb_prep);
        save = findViewById(R.id.s_save);

        teamList = new ArrayList<>();
        articleList = new ArrayList<>();
        db = new DatabaseHandler(this);
        final Team[] selectedTeam = {new Team()};
        final ArticleModel[] selectedArticle = {new ArticleModel()};

        //fetchArticlesFromDatabase();
        articleList = db.getAllData();
        List<String> articleNames = new ArrayList<>();
        for (ArticleModel articleModel : articleList) {
            articleNames.add(articleModel.getName_art() + ","+articleModel.getWeight_art());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, articleNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        article_spinner.setAdapter(adapter);

        article_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected team object
                selectedArticle[0] = articleList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //fetchTeamsFromDatabase();
        teamList = db.getAllTeam();
        List<String> teamNames = new ArrayList<>();
        for (Team team : teamList) {
            teamNames.add(team.getT_name());
        }

        ArrayAdapter<String> t_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, teamNames);
        t_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team_spinner.setAdapter(t_adapter);

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer numbP =Integer.parseInt(numb_prep.getText().toString());

                db.addScheduledArticle(new ScheduledArticle(selectedArticle[0], selectedTeam[0],numbP));
                finish();
            }
        });





    }
}