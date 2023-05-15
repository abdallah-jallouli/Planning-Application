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
import Models.ArticleModel;

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
    private ArrayList<ArticleModel> listItems;
    private String[] nameArticle;
    private String[] poidsArticle;
    private String[] cadenceAriticle;
    RecyclerView recyclerView ;

    RecyclerView.Adapter  adapter ;


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
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        dataInitialize();
        recyclerView = view.findViewById(R.id.articleRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new ArticleAdapter(getContext(),listItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        listItems = new ArrayList<>();

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
        cadenceAriticle = new String[]{
                getString(R.string.cadence_1),
                getString(R.string.cadence_2),
                getString(R.string.cadence_3),
                getString(R.string.cadence_4)
        };

        for(int i =0 ; i < nameArticle.length ; i++ )
        {
            ArticleModel articleModel = new ArticleModel(nameArticle[i], poidsArticle[i], cadenceAriticle[i]);
            listItems.add(articleModel);
        }
    }
}