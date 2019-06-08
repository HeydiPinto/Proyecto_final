package com.example.proyecto_final;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.proyecto_final.Fragments.EventosFragment;
import com.example.proyecto_final.Fragments.InfoFragment;
import com.example.proyecto_final.Fragments.PerfilFragment;

public class ActivityPrincipal extends AppCompatActivity {
    private TextView mTextMessage;
    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.eventos:
                    toolbar.setTitle("Eventos");
                    fragment = new EventosFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.info:
                    toolbar.setTitle("Informacion");
                    fragment = new InfoFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.perfil:
                    toolbar.setTitle("Perfil");
                    fragment = new PerfilFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbar = getSupportActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Eventos");
        loadFragment(new EventosFragment());
    }

}
