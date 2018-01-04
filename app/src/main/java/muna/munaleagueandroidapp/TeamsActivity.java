package muna.munaleagueandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TeamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
    }

    public void goToNextActivity(View view){
        String mainActivityButtonClicked= getIntent().getStringExtra("MainActivityButtonClicked");
        if (mainActivityButtonClicked.equals("Rosters")){
            Intent intent= new Intent(this, RosterActivity.class);
            startActivity(intent);
        } else if (mainActivityButtonClicked.equals("Team Challenges")){
            Intent intent= new Intent(this, TeamChallengesActivity.class);
            startActivity(intent);
        }
    }






}
