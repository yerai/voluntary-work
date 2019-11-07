package com.example.stanley.hi_fivolunteeringprototype;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;


public class SearchFragment extends Fragment {
    private ListView friendsListView;
    static public EditText etSearch;
    static public SearchEventAdapter adapter;

    public static void setListViewHeightBasedOnChildren
            (ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) view.setLayoutParams(new
                    ViewGroup.LayoutParams(desiredWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = 9000;

        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).showNavBar();
        return inflater.inflate(R.layout.fragments_search_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Search");
        ((MainActivity) getActivity()).removeArrow();


        adapter = new SearchEventAdapter(this.getContext());
        friendsListView = getView().findViewById(R.id.event_list);
        friendsListView.setAdapter(adapter);

        setListViewHeightBasedOnChildren(friendsListView);

        etSearch = getView().findViewById(R.id.search_event);
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Drawable secondaryButtonStyle = ContextCompat.getDrawable(getContext(), R.drawable.secondary_button);

                /*final Button elderlyBtn = getView().findViewById(R.id.btn_elderly);
                elderlyBtn.setBackground(secondaryButtonStyle);
                final Button childrenBtn = getView().findViewById(R.id.btn_children);
                childrenBtn.setBackground(secondaryButtonStyle);
                final Button sportBtn = getView().findViewById(R.id.btn_sport);
                sportBtn.setBackground(secondaryButtonStyle);
                final Button refugeesBtn = getView().findViewById(R.id.btn_refugees);
                refugeesBtn.setBackground(secondaryButtonStyle);
                final Button homelessBtn = getView().findViewById(R.id.btn_homeless);
                homelessBtn.setBackground(secondaryButtonStyle);


                elderlyBtn.setTextColor(getResources().getColor(R.color.primary_text));
                refugeesBtn.setTextColor(getResources().getColor(R.color.primary_text));
                sportBtn.setTextColor(getResources().getColor(R.color.primary_text));
                homelessBtn.setTextColor(getResources().getColor(R.color.primary_text));
                childrenBtn.setTextColor(getResources().getColor(R.color.primary_text));*/
                // Call back the Adapter with current character to Filter
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



    }
}