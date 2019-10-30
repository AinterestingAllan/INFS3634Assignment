package com.example.infs3634assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity
        implements QuizMenuFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener, RecipeMenuFragment.OnFragmentInteractionListener, MexicanFragment.OnFragmentInteractionListener, FragmentSwitcher {


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView BottomNav = findViewById(R.id.BottomNav);
        BottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.recipeicon:
                        switchToFragment1();
                        break;
                    case R.id.quizicon:
                        switchToFragment2();
                        break;
                    case R.id.profileicon:
                        switchToFragment3();
                        break;
                }
                return false;
            }
        });

    }

    public void switchToFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentSlot, new RecipeMenuFragment()).commit();
    }

    public void switchToFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentSlot, new QuizMenuFragment()).commit();
    }

    public void switchToFragment3() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentSlot, new ProfileFragment()).commit();
    }

    public void onFragmentInteraction(Uri uri) {
        //you can leave it empty
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentSlot, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
