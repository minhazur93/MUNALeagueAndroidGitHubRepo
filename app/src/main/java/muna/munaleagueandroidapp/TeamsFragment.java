package muna.munaleagueandroidapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class TeamsFragment extends Fragment implements View.OnClickListener {
    FragmentTransaction fragmentTransaction;

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
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team2:
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team3:
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team4:
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team5:
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team6:
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team7:
                openTeamChallengesFragmentOrRostersFragment();
                break;
            case R.id.team8:
                openTeamChallengesFragmentOrRostersFragment();
                break;
        }
    }

    public void openTeamChallengesFragmentOrRostersFragment() {
        String teamChallengesOrRosters = getArguments().getString("Team Challenges or Rosters");
        if (teamChallengesOrRosters.equals("Team Challenges")) {
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, new TeamChallengesFragment()).addToBackStack(null);
            fragmentTransaction.commit();
        } else if (teamChallengesOrRosters.equals("Rosters")) {
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, new RostersFragment()).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
