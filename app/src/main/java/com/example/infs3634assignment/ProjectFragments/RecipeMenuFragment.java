package com.example.infs3634assignment.ProjectFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.infs3634assignment.FragmentSwitcher;
import com.example.infs3634assignment.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecipeMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeMenuFragment newInstance(String param1, String param2) {
        RecipeMenuFragment fragment = new RecipeMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_recipe_menu, container, false);

        ImageButton mexicanbutton = view.findViewById(R.id.MexicanButton);
        mexicanbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRecipeFragment("mexican", "url");
            }
        });

        ImageButton americanbutton = view.findViewById(R.id.AmericanButton);
        americanbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRecipeFragment("american", "url");
            }
        });

        ImageButton italianbutton =  view.findViewById(R.id.ItalianButton);
        italianbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRecipeFragment("italian", "url");
            }
        });

        ImageButton chinesebutton =  view.findViewById(R.id.ChineseButton);
        chinesebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRecipeFragment("chinese", "url");
            }
        });

        ImageButton greekbutton =  view.findViewById(R.id.GreekButton);
        greekbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRecipeFragment("greek", "url");
            }
        });

        ImageButton indianbutton = view.findViewById(R.id.IndianButton);
        indianbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRecipeFragment("indian", "url");
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

    public void showRecipeFragment(String recipeName, String url){
        Fragment fr = new RecipeFragment(recipeName,url);
        FragmentSwitcher fc=(FragmentSwitcher) getActivity();
        fc.replaceFragment(fr,recipeName);
    }


}