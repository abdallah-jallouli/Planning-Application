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

import com.example.myplanner.AddProduct;
import com.example.myplanner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Controller.ProductAdapter;
import Controller.DatabaseHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView ;
    public  static ProductAdapter adapter;
    DatabaseHandler db ;



    public ArticleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleFragment newInstance(String param1, String param2) {
        ArticleFragment fragment = new ArticleFragment();
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
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab_act ;
        fab_act = view.findViewById(R.id.fab);

        db = new DatabaseHandler(getContext());
        recyclerView = view.findViewById(R.id.articleRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new ProductAdapter(getContext(),db.getAllData(),db);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        fab_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Team team = new Team();
//                team = db.getTeamObjectById(5);
//                Log.d ("Team name :" , team.t_name);
//                Log.d ("Team id :" , String.valueOf(team.t_id));
                Intent intent = new Intent(getContext(), AddProduct.class);
                startActivity(intent);
            }
        });
    }


    public static void notifyAdapter(){
        adapter.notifyDataSetChanged();
    }


}