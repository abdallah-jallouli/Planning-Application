package com.example.myplanner.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myplanner.AddEmployee;
import com.example.myplanner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Controller.DatabaseHandler;
import Controller.EmployeeAdapter;
import Models.Team;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private String mParam1;
    private String mParam2;
    private EditText team_name ;
    private Button btn_add_team , btn_del_team ;
    private FloatingActionButton e_fab ;
    private RecyclerView e_recyclerView ;
    public  static EmployeeAdapter e_adapter;
    DatabaseHandler db ;

    public TeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(String param1, String param2) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static void notifyAdapter() {
        e_adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        team_name = view.findViewById(R.id.edite_name_team);
        btn_add_team = view.findViewById(R.id.add_team_btn);
        btn_del_team = view.findViewById(R.id.delete_team_btn);
        e_fab =view.findViewById(R.id.fab_emp);
        db = new DatabaseHandler(getActivity());







        btn_add_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_name = team_name.getText().toString();
                db.addTeam(new Team(t_name));
//                if (id == -1)
//                Toast.makeText(getContext(), "Team Not Added ", Toast.LENGTH_SHORT).show();
            }
        });

        btn_del_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_name1 =team_name.getText().toString();
                db.deleteTeam(t_name1);
            }
        });

        e_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddEmployee.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHandler(getContext());
        e_recyclerView = view.findViewById(R.id.employee_recycler);
        e_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        e_recyclerView.setHasFixedSize(true);
        e_adapter = new EmployeeAdapter(getContext(),db.getAllEmployee(),db);
        e_recyclerView.setAdapter(e_adapter);
        e_adapter.notifyDataSetChanged();

    }

}