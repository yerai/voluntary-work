package com.example.stanley.hi_fivolunteeringprototype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddFriendsFragment extends Fragment {
    private FriendsListAdapter adapter;
    private ListView friendsListView;
    private EditText etSearch;

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

        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    List<HashMap<String, String>> fList = new ArrayList<HashMap<String, String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).hideNavBar();

        View view= inflater.inflate(R.layout.fragment_add_friends, container, false);
        fList = new ArrayList<HashMap<String, String>>();


        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle("Add Friends");
        ((MainActivity) getActivity()).addArrow();

        String[] name = new String[]{
                "Obara Sand", "Oberyn", "Olenna Tyrell", "Olyvar",
        };

        int[] image = new int[]{
                R.drawable.friends_1, R.drawable.friends_2, R.drawable.friends_3, R.drawable.friends_4,
        };

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("name", name[i]);
            hm.put("image", Integer.toString(image[i]));
            fList.add(hm);
        }

        String[] from = {"name", "image"};
        int[] to = {R.id.name, R.id.profile_pic};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), list, R.layout.friend_list_item, from, to);
        ListView lv = (ListView) view.findViewById(R.id.event_list);
        lv.setAdapter(simpleAdapter);
        setListViewHeightBasedOnChildren(lv);

        etSearch = getView().findViewById(R.id.search_friend);
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = etSearch.getText().toString().toLowerCase();

                List<HashMap<String, String>> filtered_List_final = new ArrayList<HashMap<String, String>>();
                for(int i=0; i < fList.size(); i++){

                    if(fList.get(i).toString().toLowerCase().contains(text)){
                        filtered_List_final.add(fList.get(i));
                    }
                }

                String[] from = {"name", "image"};
                int[] to = {R.id.name, R.id.profile_pic};

                SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), filtered_List_final, R.layout.friend_list_item, from, to);
                ListView lv = (ListView) getView().findViewById(R.id.event_list);
                lv.setAdapter(simpleAdapter);
                setListViewHeightBasedOnChildren(lv);
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
