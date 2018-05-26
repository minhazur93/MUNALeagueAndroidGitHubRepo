package muna.munaleagueandroidapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import muna.munaleagueandroidapp.R;


public class TeamsFragment extends Fragment implements View.OnClickListener {
    FragmentTransaction fragmentTransaction;
    String teamPicked;


    public TeamsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        Button team1 = (Button) view.findViewById(R.id.team1);
        Button team2 = (Button) view.findViewById(R.id.team2);
        Button team3 = (Button) view.findViewById(R.id.team3);
        Button team4 = (Button) view.findViewById(R.id.team4);
        Button team5 = (Button) view.findViewById(R.id.team5);
        Button team6 = (Button) view.findViewById(R.id.team6);
        Button team7 = (Button) view.findViewById(R.id.team7);
        Button team8 = (Button) view.findViewById(R.id.team8);

        team1.setOnClickListener(this);
        team2.setOnClickListener(this);
        team3.setOnClickListener(this);
        team4.setOnClickListener(this);
        team5.setOnClickListener(this);
        team6.setOnClickListener(this);
        team7.setOnClickListener(this);
        team8.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.team1:
                teamPicked= "Shahin/Huzifa";
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team2:
                teamPicked= "Aldin/Redwan";
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team3:
                teamPicked= "Shojib/Sahid";
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team4:
                teamPicked= "Alzaber/Miqdad";
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team5:
                teamPicked= "Mahi/Hanzala";
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team6:
                teamPicked= "Ragib/Salah Uddin";
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team7:
                teamPicked= "Rabbi/Minhaz";
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team8:
                teamPicked= "Arek/Mubin";
                openTeamChallengesFragmentOrRostersFragment();
                break;
        }
    }

    public void openTeamChallengesFragmentOrRostersFragment() {
        String teamChallengesOrRosters = getArguments().getString("Team Challenges or Rosters");
        if (teamChallengesOrRosters.equals("Team Challenges")) {
            Bundle bundle = new Bundle();
            bundle.putString("Team that was clicked on", teamPicked);
            TeamChallengesFragment teamChallengesFragment = new TeamChallengesFragment();
            teamChallengesFragment.setArguments(bundle);

            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, teamChallengesFragment).addToBackStack(null);
            fragmentTransaction.commit();
        } else if (teamChallengesOrRosters.equals("Rosters")) {
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, new RostersFragment()).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
