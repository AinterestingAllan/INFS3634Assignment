package com.example.infs3634assignment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.infs3634assignment.ProjectFragments.InProgress;
import com.example.infs3634assignment.ProjectFragments.ProfileFragment;
import com.example.infs3634assignment.ProjectFragments.QuizMenuFragment;
import com.example.infs3634assignment.ProjectFragments.RecipeFragment;
import com.example.infs3634assignment.ProjectFragments.RecipeMenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity
        implements QuizMenuFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener, RecipeMenuFragment.OnFragmentInteractionListener,
        FragmentSwitcher, RecipeFragment.OnFragmentInteractionListener,
        InProgress.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecipeMenuFragment recipeMenuFragment = new RecipeMenuFragment();
        final InProgress inProgress = new InProgress();
        Data.recipeNameAy.put("inProgress", inProgress);

        final ProfileFragment profileFragment = new ProfileFragment();

        //Fragment Manager
        final RecipeMenuFragment fragment = new RecipeMenuFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Data.fragmentManager = fragmentManager;
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentSlot, recipeMenuFragment);
        fragmentTransaction.add(R.id.fragmentSlot, inProgress);
        fragmentTransaction.add(R.id.fragmentSlot, profileFragment);
        fragmentTransaction.hide(inProgress);
        fragmentTransaction.hide(profileFragment);
        fragmentTransaction.show(recipeMenuFragment);
        fragmentTransaction.commit();

        Data.nowFragment = recipeMenuFragment;

        // Code for bottom navigation
        final BottomNavigationView BottomNav = findViewById(R.id.BottomNav);
        BottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.recipeicon:
                        switchToFragment(recipeMenuFragment, fragmentManager);
                        break;
                    case R.id.inProgress:
                        switchToFragment(Data.recipeNameAy.get("inProgress"), fragmentManager);
                        break;
                    case R.id.profileicon:
                        switchToFragment(profileFragment, fragmentManager);
                        break;
                }
                return false;
            }
        });

    }

    // Methods for bottom navigation
    public void switchToFragment(Fragment fragment, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().hide(Data.nowFragment).commit();
        fragmentManager.beginTransaction().show(fragment).commit();
        Data.nowFragment = fragment;
    }


    public void onFragmentInteraction(Uri uri) {
        //you can leave it empty
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        boolean exist = false;
        for (String x : Data.recipeNameAy.keySet()) {
            if (x.equals(tag)) {
                exist = true;
                break;
            }
        }

        if (exist) {
            Fragment f = Data.recipeNameAy.get(tag);
            fragmentTransaction.remove(f);
            fragmentTransaction.add(R.id.fragmentSlot, fragment);

        } else {
            Data.recipeNameAy.put(tag, fragment);
            fragmentTransaction.add(R.id.fragmentSlot, fragment);

        }

        fragmentTransaction.hide(Data.nowFragment);
        Data.nowFragment = fragment;
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }
}
