package personal.alvarez.topicpomodoro;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.github.clans.fab.FloatingActionButton;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import personal.alvarez.topicpomodoro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    private float TWENTY_FIVE_MINS_SECONDS = 1500;
    float[] yData = {0, TWENTY_FIVE_MINS_SECONDS};

    MainPresenter presenter;

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

        binding.navView.setNavigationItemSelectedListener(this);

        presenter = new MainPresenter();

        initPieChart();
        initLocalData();
        getData();
    }

    private void initLocalData() {
        FloatingActionButton fab1 = new FloatingActionButton(this);
        fab1.setLabelText("TEXT");

        binding.bar.content.expandableMenu.addMenuButton(fab1);

        // TODO: 22/09/17 populate with topics currently saved
    }

    private void getData() {
        presenter.startTimer().subscribe(this::timeUpdated, throwable -> {
            Log.d("ERROR", "ERROR");
        });
    }

    private void timeUpdated(Long aLong) {
        Log.d("TIME", "" + aLong);
        yData[0] = aLong;
        addData();
    }

    private void initPieChart() {
        // configure pie chart

        // enable hole and configure
        binding.bar.content.timerChart.setDrawHoleEnabled(true);
        //binding.bar.content.timerChart.setHoleColorTransparent(true);
        binding.bar.content.timerChart.setHoleRadius(68);
        binding.bar.content.timerChart.setTransparentCircleRadius(70);

        // enable rotation of the chart by touch
        binding.bar.content.timerChart.setRotationEnabled(false);

        // set a chart value selected listener
        binding.bar.content.timerChart.setOnChartValueSelectedListener(null);

        //center text
        binding.bar.content.timerChart.setCenterTextSize(23);
        //binding.bar.content.timerChart.setCenterTextColor();

        addData();
    }

    private void addData() {

        String[] xData = {"Technical excellence", "Nimble"};

        List<PieEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new PieEntry(yData[i], ""));

        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(1);
        dataSet.setSelectionShift(0);
        dataSet.setDrawValues(false);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(ColorTemplate.rgb("FFFFFF"));
        colors.add(ColorTemplate.rgb("FFC41A1D"));
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(dataSet);

        binding.bar.content.timerChart.setData(data);

        // undo all highlights
        binding.bar.content.timerChart.highlightValues(null);

        binding.bar.content.timerChart.getLegend().setEnabled(false);
        binding.bar.content.timerChart.getDescription().setEnabled(false);
        binding.bar.content.timerChart.setCenterText(FormatUtil.getSecondsFormatted(yData[0]));

        // update pie chart
        binding.bar.content.timerChart.invalidate();


    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
