package com.example.myplanner.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myplanner.R;

import java.util.ArrayList;

import Controller.ArticleAdapter;
import Controller.ScheduleAdapter;
import Models.ScheduleModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanningFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String[] nameArticle;
    private String[] poidsArticle;
    private String[] nbPreparation;
    private String[] equipeResp;
    private ArrayList<ScheduleModel> scheduleModels;
    RecyclerView recyclerView ;

    RecyclerView.Adapter  adapter ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlanningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlanningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanningFragment newInstance(String param1, String param2) {
        PlanningFragment fragment = new PlanningFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();
        recyclerView = view.findViewById(R.id.planningRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new ScheduleAdapter(getContext(),scheduleModels);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        scheduleModels = new ArrayList<>();

        nameArticle = new String[]{
                getString(R.string.name_1),
                getString(R.string.name_2),
                getString(R.string.name_3),
                getString(R.string.name_4)
        };
        poidsArticle = new String[]{
                getString(R.string.poids_1),
                getString(R.string.poids_2),
                getString(R.string.poids_3),
                getString(R.string.poids_4)
        };
        nbPreparation = new String[]{"12", "8","10","4"};
        equipeResp = new String[]{"1","1", "2","3"};

        for(int i =0 ; i < nameArticle.length ; i++ )
        {
            ScheduleModel scheduleModel = new ScheduleModel(nameArticle[i], poidsArticle[i], nbPreparation[i], equipeResp[i]);
            scheduleModels.add(scheduleModel);
        }
    }
}