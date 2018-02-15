package muna.munaleagueandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_team_challenges) {
            startTeamChallengesActivity();

        } else if (id == R.id.nav_rosters) {
            startRosterActivity();

        } else if (id == R.id.nav_chat) {
            startChatActivity();

        } else if (id == R.id.nav_gallery) {
            startMediaActivity();
        }
        else if (id == R.id.nav_about) {
            startAboutActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startTeamChallengesActivity() {
        Intent intent = new Intent(this, TeamsActivity.class);
        intent.putExtra("MainActivityButtonClicked", "Team Challenges");
        startActivity(intent);
    }

    private void startRosterActivity() {
        Intent intent = new Intent(this, TeamsActivity.class);
        intent.putExtra("MainActivityButtonClicked", "Rosters");
        startActivity(intent);
    }

    private void startChatActivity() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    private void startMediaActivity() {
        Intent intent = new Intent(this, MediaActivity.class);
        startActivity(intent);
    }

    private void startAboutActivity() {
        Intent intent= new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


}
