package personal.alvarez.topicpomodoro.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import personal.alvarez.topicpomodoro.FormatUtil;
import personal.alvarez.topicpomodoro.R;
import personal.alvarez.topicpomodoro.TopicApplication;
import personal.alvarez.topicpomodoro.databinding.FragmentMainBinding;
import personal.alvarez.topicpomodoro.presenters.MainPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    static MainFragment fragment;

    private float TWENTY_FIVE_MINS_SECONDS = 1500;
    float[] yData = {0, TWENTY_FIVE_MINS_SECONDS};

    @Inject
    MainPresenter presenter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment getInstance() {
        if (fragment == null) {
            fragment = new MainFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        TopicApplication.getComponent().inject(this);

        initPieChart();
        initLocalData();
        getData();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initLocalData() {
        // TODO: 22/09/17 populate with topics currently saved
        FloatingActionButton fab1 = new FloatingActionButton(getActivity());
        fab1.setLabelText("TEXT");

        binding.expandableMenu.addMenuButton(fab1);
    }

    private void getData() {
        presenter.startTimer().subscribe(this::timeUpdated, throwable -> {
            Log.d("ERROR", "ERROR");
        });
    }

    private void timeUpdated(Long aLong) {
        yData[0] = aLong;
        yData[1] = yData[1] - 1;
        Log.d("TIME", "0 : " + yData[0] + ", 1 : " + yData[1]);

        drawData();
    }

    private void initPieChart() {
        // configure pie chart

        // enable hole and configure
        binding.timerChart.setDrawHoleEnabled(true);
        //binding.bar.content.timerChart.setHoleColorTransparent(true);
        binding.timerChart.setHoleRadius(68);
        binding.timerChart.setTransparentCircleRadius(70);

        // enable rotation of the chart by touch
        binding.timerChart.setRotationEnabled(false);

        // set a chart value selected listener
        binding.timerChart.setOnChartValueSelectedListener(null);

        //center text
        binding.timerChart.setCenterTextSize(23);
        //binding.bar.content.timerChart.setCenterTextColor();

        drawData();
    }

    private void drawData() {

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

        binding.timerChart.setData(data);

        // undo all highlights
        binding.timerChart.highlightValues(null);

        binding.timerChart.getLegend().setEnabled(false);
        binding.timerChart.getDescription().setEnabled(false);
        binding.timerChart.setCenterText(FormatUtil.getSecondsFormatted(yData[0]));

        // update pie chart
        binding.timerChart.invalidate();


    }
}
