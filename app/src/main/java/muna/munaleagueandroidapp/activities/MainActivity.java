package muna.munaleagueandroidapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import muna.munaleagueandroidapp.R;
import muna.munaleagueandroidapp.fragments.AboutFragment;
import muna.munaleagueandroidapp.fragments.ChatFragment;
import muna.munaleagueandroidapp.fragments.HomeFragment;
import muna.munaleagueandroidapp.fragments.TeamsFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        beginFragmentTransactions(fragmentTransaction,new HomeFragment(),"Home");

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            openHomeFragment();
        } else if (id == R.id.nav_team_challenges) {
            openTeamsFragmentForTeamChallenges();
        } else if (id == R.id.nav_rosters) {
            openTeamsFragmentForRosters();
        } else if (id == R.id.nav_chat) {
            openChatFragment();
        } else if (id == R.id.nav_gallery) {
            startMediaActivity();
        } else if (id == R.id.nav_about) {
            openAboutFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openHomeFragment() {
        beginFragmentTransactions(fragmentTransaction,new HomeFragment(),"Home");
    }

    private void openTeamsFragmentForTeamChallenges() {
        Bundle bundle = new Bundle();
        bundle.putString("Team Challenges or Rosters", "Team Challenges");
        TeamsFragment teamsFragment = new TeamsFragment();
        teamsFragment.setArguments(bundle);
        beginFragmentTransactions(fragmentTransaction,teamsFragment,"Teams");
    }

    private void openTeamsFragmentForRosters() {
        Bundle bundle = new Bundle();
        bundle.putString("Team Challenges or Rosters", "Rosters");
        TeamsFragment teamsFragment = new TeamsFragment();
        teamsFragment.setArguments(bundle);
        beginFragmentTransactions(fragmentTransaction,teamsFragment,"Teams");

    }


    private void openChatFragment() {
        beginFragmentTransactions(fragmentTransaction,new ChatFragment(),"Chat");
    }

    private void startMediaActivity() {
        Intent intent = new Intent(this, MediaActivity.class);
        startActivity(intent);
    }

    private void openAboutFragment() {
        beginFragmentTransactions(fragmentTransaction, new AboutFragment(),"About");
    }

    private void beginFragmentTransactions (FragmentTransaction fragmentTransaction, Fragment fragment, String actionBarTitle){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment).addToBackStack(null);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(actionBarTitle);
    }
}
