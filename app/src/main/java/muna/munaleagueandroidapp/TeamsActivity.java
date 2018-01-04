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

    public void goToRosterActivity(View view){
        Intent intent= new Intent(this, RosterActivity.class);
        startActivity(intent);
    }






}
