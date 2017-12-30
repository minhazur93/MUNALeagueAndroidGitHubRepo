package muna.munaleagueandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Rosters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rosters);
    }

    public void goToRoster(View view){
        Intent intent= new Intent(this, Rosters2.class);
        startActivity(intent);
    }






}
