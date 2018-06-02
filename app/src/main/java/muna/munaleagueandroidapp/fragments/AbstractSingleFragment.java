package muna.munaleagueandroidapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import muna.munaleagueandroidapp.R;

public abstract class AbstractSingleFragment extends FragmentActivity {
    public abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abstract_single_fragment_view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment= fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment)
                    .commit();

        }
    }
}
