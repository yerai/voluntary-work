package com.example.stanley.hi_fivolunteeringprototype;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.example.stanley.hi_fivolunteeringprototype.R;

public class HelpsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).hideNavBar();
        return inflater.inflate(R.layout.fragment_helps, container, false);


//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().onBackPressed();
//            }
//        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Help Level");
        ((MainActivity) getActivity()).addArrow();
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        Button invite = getView().findViewById(R.id.friends_button);
        invite.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Fragment nextFragment = new FriendsFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, nextFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        ObjectAnimator help_animation = ObjectAnimator.ofInt(getView().findViewById(R.id.help_level), "progress", 30);
        help_animation.setDuration(2000);
        help_animation.setInterpolator(new AccelerateDecelerateInterpolator());
        help_animation.start();

    }
}
