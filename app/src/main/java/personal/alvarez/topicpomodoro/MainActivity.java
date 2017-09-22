package personal.alvarez.topicpomodoro;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
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

        binding.bar.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        presenter = new MainPresenter();

        initPieChart();
        getData();
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
        binding.bar.content.timerChart.setUsePercentValues(true);
        binding.bar.content.timerChart.setDescription(new Description());

        // enable hole and configure
        binding.bar.content.timerChart.setDrawHoleEnabled(true);
        //binding.bar.content.timerChart.setHoleColorTransparent(true);
        binding.bar.content.timerChart.setHoleRadius(7);
        binding.bar.content.timerChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        binding.bar.content.timerChart.setRotationAngle(0);
        binding.bar.content.timerChart.setRotationEnabled(true);

        // set a chart value selected listener
        binding.bar.content.timerChart.setOnChartValueSelectedListener(null);

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
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        binding.bar.content.timerChart.setData(data);

        // undo all highlights
        binding.bar.content.timerChart.highlightValues(null);

        // update pie chart
        binding.bar.content.timerChart.invalidate();
        binding.bar.content.timerChart.getLegend().setEnabled(false);
        binding.bar.content.timerChart.getDescription().setEnabled(false);

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

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
