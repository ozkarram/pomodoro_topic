package personal.alvarez.topicpomodoro;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import personal.alvarez.topicpomodoro.databinding.ActivityMainBinding;
import personal.alvarez.topicpomodoro.fragments.MainFragment;
import personal.alvarez.topicpomodoro.fragments.TopicFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        goToMainFragment();

        binding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void goToMainFragment() {
        MainFragment fragment = MainFragment.getInstance();
        goToFragment(fragment);
    }

    private void goToTopicFragment() {
        TopicFragment fragment = new TopicFragment();
        goToFragment(fragment);
    }

    private void goToFragment(Fragment fragment) {
        binding.navView.getMenu().getItem(0).setChecked(true);
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        binding.navView.getMenu().getItem(0).setChecked(true);

        if (id == R.id.nav_new_pomodoro) {
            goToMainFragment();
        } else if (id == R.id.nav_topics) {
            goToTopicFragment();
        } else if (id == R.id.nav_stats) {

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
