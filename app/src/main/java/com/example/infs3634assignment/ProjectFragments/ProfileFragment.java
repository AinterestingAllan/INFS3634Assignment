package com.example.infs3634assignment.ProjectFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3634assignment.Connectivity.AppDatabase;
import com.example.infs3634assignment.Connectivity.HistoryDao;
import com.example.infs3634assignment.Connectivity.HistorySelectAsyncTask;
import com.example.infs3634assignment.Connectivity.ScoreDAO;
import com.example.infs3634assignment.Connectivity.ScoreDatabase;
import com.example.infs3634assignment.HistoryActivity;
import com.example.infs3634assignment.Quiz;
import com.example.infs3634assignment.R;
import com.example.infs3634assignment.model.History;
import com.example.infs3634assignment.model.Score;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView go1;
    private ImageView go2;
    private ImageView go3;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //BUTTON TO VIEW HISTORY LIST

        Button viewHistory = view.findViewById(R.id.viewHistory);
        viewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //CODE TO DETERMINE TOTAL QUIZ SCORE
        ScoreDatabase database = Room.databaseBuilder(getContext(), ScoreDatabase.class, "db-scores")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        ScoreDAO scoreDAO = database.getScoreDAO();

        int quizsum = 0;
        List<Score> quizScores = scoreDAO.getScores();
        int QuizCount = quizScores.size();
        for (Score score : quizScores) {
            int s1 = score.getQuizScore();
            quizsum += s1;
            TextView quizScore = view.findViewById(R.id.quiz_score);
            quizScore.setText("Quiz Score: " + Integer.toString(quizsum) + "/" + (QuizCount * 5));

            TextView quizDetails = view.findViewById(R.id.health_score);
            quizDetails.setText("You have completed " + QuizCount + " quizzes");
        }

        return view;
    }

    @Override
    public void onResume() {

        //CODE FOR TOTAL QUIZ SCORE

        super.onResume();
        ScoreDatabase database = Room.databaseBuilder(getContext(), ScoreDatabase.class, "db-scores")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        ScoreDAO scoreDAO = database.getScoreDAO();

        int quizsum = 0;

        List<Score> quizScores = scoreDAO.getScores();
        int QuizCount = quizScores.size();

        for (Score score : quizScores) {
            int s1 = score.getQuizScore();
            quizsum += s1;

            TextView quizScore = getActivity().findViewById(R.id.quiz_score);
            quizScore.setText("Quiz Score: " + Integer.toString(quizsum) + "/" + (QuizCount * 5));

            TextView quizDetails = getActivity().findViewById(R.id.health_score);
            quizDetails.setText("You have completed " + QuizCount + " quizzes");

        }
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
