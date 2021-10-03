package com.exzhacks.ui.social;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exzhacks.Organization;
import com.exzhacks.R;

import java.util.ArrayList;
import java.util.List;

import static com.exzhacks.MainActivity.mOrganizations;

public class SocialFragment extends Fragment implements RVAdapter.OnItemClicked, View.OnClickListener{

    SearchView searchView;
    ArrayList<Organization> dataset;
    RVAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_social, container, false);


        RecyclerView mRecyclerView = (RecyclerView) root.findViewById(R.id.org_rv);

        dataset = mOrganizations;

        // assign the layout to be displayed ion the recycler view
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // provide a dataset to the recycler view through an adapter
        mAdapter = new RVAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);

        // enable on click for recycler view items
        mAdapter.setOnClick(this);

        //set up searching
        searchView = root.findViewById(R.id.org_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    void filter(String text){
        ArrayList<Organization> temp = new ArrayList();
        for(Organization note: dataset){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if((note.getName().toLowerCase().contains(text.toLowerCase())) || (note.getSummary().toLowerCase().contains(text.toLowerCase()))){
                temp.add(note);
            }
        }
        //update recyclerview
        mAdapter.updateList(temp);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(int position) {

    }
}