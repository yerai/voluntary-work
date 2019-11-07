package com.example.stanley.hi_fivolunteeringprototype;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;
import org.w3c.dom.Text;

import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    public static boolean events = false;

    BottomNavigationView bottomNav;

    public static boolean friends = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Stack<Fragment> stack = new Stack<Fragment>();

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.menu_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.menu_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.menu_new_event:
                            selectedFragment = new NotImplementedFragment();
                            break;

                    }

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();

                    return true;
                }
            };


    public void setFragmentTitle(String title){
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(title);
    }

    public void addArrow(){
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void removeArrow(){
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(null);
    }



    public void onEventSelected(int position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        Toast toast=Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

    public void hideNavBar(){
        bottomNav.setVisibility(View.GONE);
    }

    public void showNavBar(){
        bottomNav.setVisibility(View.VISIBLE);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_sat:
                if (checked) {
                    LinearLayout time_options;
                    time_options = (LinearLayout) findViewById(R.id.choose_time);
                    time_options.setVisibility(View.VISIBLE);
                }

                    break;
            case R.id.radio_fri:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radio_eleven:
                if (checked) {
                    Button offerHelp;
                    offerHelp = (Button) findViewById(R.id.offer_help);
                    offerHelp.setEnabled(true);
                    offerHelp.setBackgroundResource(R.drawable.primary_button);
                }
                    break;
            case R.id.radio_five:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    public void requestSent (View view){

        // Create new fragment and transaction
        Fragment newFragment = new RequestSentFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void goEvent (View view){

        Fragment newFragment;
            TextView element = (TextView) view.findViewById(R.id.item_number);
            if(element.getText() != "Helping the elderly") {

                // Create new fragment and transaction
                newFragment = new NotImplementedFragment();

            }else{
                // Create new fragment and transaction
                newFragment = new DetailViewFragment();
            }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    public void goNoImplemented(View view)
    {
        Fragment newFragment;
        newFragment = new NotImplementedFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void onCategoryClicked(View view) {
        // Is the button now checked?
        Button btn = (Button) view;

        Drawable secondaryButtonStyle = ContextCompat.getDrawable(view.getContext(), R.drawable.secondary_button);
        Drawable primaryButtonStyle = ContextCompat.getDrawable(view.getContext(), R.drawable.primary_button);


        if(!SearchEventAdapter.cat.contains(btn.getText())){
            btn.setBackground(primaryButtonStyle);
            btn.setTextColor(Color.WHITE);
            SearchEventAdapter.cat.add(btn.getText().toString());
        } else{
            btn.setBackground(secondaryButtonStyle);
            btn.setTextColor(getResources().getColor(R.color.primary_text));
            List<String> s = SearchEventAdapter.cat;
            SearchEventAdapter.cat.remove(btn.getText());
            Log.d("Ssa",SearchEventAdapter.cat.toString());
        }

        SearchFragment.adapter.getFilter().filter(SearchFragment.etSearch.getText());
/*        final Button elderlyBtn = findViewById(R.id.btn_elderly);
        elderlyBtn.setBackground(secondaryButtonStyle);
        final Button childrenBtn = findViewById(R.id.btn_children);
        childrenBtn.setBackground(secondaryButtonStyle);
        final Button sportBtn = findViewById(R.id.btn_sport);
        sportBtn.setBackground(secondaryButtonStyle);
        final Button refugeesBtn = findViewById(R.id.btn_refugees);
        refugeesBtn.setBackground(secondaryButtonStyle);
        final Button homelessBtn = findViewById(R.id.btn_homeless);
        homelessBtn.setBackground(secondaryButtonStyle);


        elderlyBtn.setTextColor(getResources().getColor(R.color.primary_text));
        refugeesBtn.setTextColor(getResources().getColor(R.color.primary_text));
        sportBtn.setTextColor(getResources().getColor(R.color.primary_text));
        homelessBtn.setTextColor(getResources().getColor(R.color.primary_text));
        childrenBtn.setTextColor(getResources().getColor(R.color.primary_text));*/





    }


    public void goBackHome (View view){

        MainActivity.events = true;

        // Create new fragment and transaction
        Fragment newFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    public void goAddFriends (View view){

        // Create new fragment and transaction
        Fragment newFragment = new AddFriendsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    public void addedFriend (View view){
        Button btn = view.findViewById(R.id.btn_add);
        btn.setText("ADDED");
        btn.setBackgroundResource(R.drawable.primary_button_ok);
        friends = true;

    }


}