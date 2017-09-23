package personal.alvarez.topicpomodoro.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import personal.alvarez.topicpomodoro.R;
import personal.alvarez.topicpomodoro.TopicApplication;
import personal.alvarez.topicpomodoro.databinding.FragmentTopicBinding;
import personal.alvarez.topicpomodoro.models.Topic;
import personal.alvarez.topicpomodoro.presenters.TopicPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends Fragment {

    @Inject
    TopicPresenter presenter;

    FragmentTopicBinding binding;

    public TopicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_topic, container, false);
        TopicApplication.getComponent().inject(this);
        getData();
        return binding.getRoot();
    }

    private void getData() {
        List<Topic> topics = presenter.getTopics();
    }

}
