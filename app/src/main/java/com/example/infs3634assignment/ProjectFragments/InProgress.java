package com.example.infs3634assignment.ProjectFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.infs3634assignment.Connectivity.AppDatabase;
import com.example.infs3634assignment.Connectivity.HistoryInsertAsyncTask;
import com.example.infs3634assignment.Data;
import com.example.infs3634assignment.DetailRecipe;
import com.example.infs3634assignment.R;
import com.example.infs3634assignment.model.Nutrition;
import com.example.infs3634assignment.model.Result;
import com.example.infs3634assignment.model.History;

import java.util.ArrayList;

//CLASS FOR IN PROGRESS TIMER


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InProgress.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InProgress#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InProgress extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Chronometer chronometer;
    private long pauseOffset = 0;
    private boolean running = false;

    private OnFragmentInteractionListener mListener;

    public InProgress() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InProgress.
     */
    // TODO: Rename and change types and number of parameters
    public static InProgress newInstance(String param1, String param2) {
        InProgress fragment = new InProgress();
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
        View view = inflater.inflate(R.layout.fragment_in_progress, container, false);
        TextView title = view.findViewById(R.id.progress_title);

        //HANDLES START, STOP, RESET OF TIMER

        if (Data.nowCooking == null) {
            title.setText("No recipe");
        } else {
            title.setText(Data.nowCooking.getTitle());
        }

        chronometer = view.findViewById(R.id.choronmeter1);
        Button start = view.findViewById(R.id.time_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    running = true;
                }
            }
        });

        Button pause = view.findViewById(R.id.time_pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;
                }
            }
        });

        Button reset = view.findViewById(R.id.time_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;
                }
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
            }
        });


        // VIEW RECIPE DETAIL

        Button backToDatail = view.findViewById(R.id.backToDetail);
        backToDatail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent i = new Intent(getContext(), DetailRecipe.class);
                    Bundle b = new Bundle();
                    final Result recipe = Data.nowCooking;
                    b.putString("recipeTitle", recipe.getTitle());
                    b.putString("recipeURL", recipe.getSourceUrl());
                    b.putString("recipeImage", recipe.getImage());
                    b.putString("healthRank", recipe.getHealthScore() + "");
                    b.putString("preM", recipe.getPreparationMinutes());
                    b.putString("cookM", recipe.getCookingMinutes());
                    b.putString("gluten", recipe.getGlutenFree() + "");
                    b.putString("dairy", recipe.getDairyFree() + "");

                    ArrayList<String> nutritionAy = new ArrayList<>();
                    for (Nutrition n : recipe.getNutrition()) {
                        nutritionAy.add(n.getTitle() + " " + n.getAmount() + " " + n.getUnit());
                    }
                    b.putStringArrayList("recipeNutrition", nutritionAy);

                    Data.nowDetail = recipe;
                    i.putExtras(b);
                    getActivity().startActivity(i);
                } catch (NullPointerException error) {
                    Toast.makeText(getContext(), "There is no recipe to view.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //FINISH RECIPE TIMER

        final TextView comment = view.findViewById(R.id.comment);
        Button finish = view.findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Data.nowCooking == null) {
                    Toast.makeText(getContext(), "There is no recipe", Toast.LENGTH_SHORT).show();
                } else {
                    // create a history and store in database
                    String elapsedMillis = chronometer.getText().toString();
                    String userComment = comment.getText().toString();


                    History history = new History(Data.nowCooking.getTitle()
                            , elapsedMillis, userComment, Data.nowCooking.getHealthScore());

                    AppDatabase db = AppDatabase.getInstance(getContext());
                    HistoryInsertAsyncTask historyInsertAsyncTask = new HistoryInsertAsyncTask();
                    historyInsertAsyncTask.setDatabase(db);
                    historyInsertAsyncTask.execute(history);

                    // clear the nowCooking
                    Data.nowCooking = null;

                    // replace a none title fragment
                    InProgress newInProgress = new InProgress();
                    FragmentTransaction fragmentTransaction = Data.fragmentManager.beginTransaction();
                    fragmentTransaction.remove(Data.recipeNameAy.get("inProgress"));
                    fragmentTransaction.add(R.id.fragmentSlot, newInProgress);
                    fragmentTransaction.show(newInProgress);
                    fragmentTransaction.commit();
                    Data.nowFragment = newInProgress;
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
