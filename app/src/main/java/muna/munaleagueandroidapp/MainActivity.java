package muna.munaleagueandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button rostersButton;
    Button teamChallengesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rostersButton= (Button) findViewById(R.id.rostersButton);
        teamChallengesButton= (Button) findViewById(R.id.teamChallengesButton);
    }

    public void goToTeamsActivity(View view) {
        Intent intent = new Intent(this, TeamsActivity.class);

        if(view==rostersButton){
            intent.putExtra("MainActivityButtonClicked", "Rosters");
        } else if (view== teamChallengesButton){
            intent.putExtra("MainActivityButtonClicked", "Team Challenges");
        }

        startActivity(intent);
    }

    public void goToChatActivity(View view){
        Intent intent= new Intent(this, ChatActivity.class);
        startActivity(intent);

    }
}
