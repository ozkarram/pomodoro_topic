package personal.alvarez.topicpomodoro.presenters;

import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import personal.alvarez.topicpomodoro.TopicPomodoroConstants;
import personal.alvarez.topicpomodoro.models.Topic;
import personal.alvarez.topicpomodoro.repositories.TopicRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oalvarez on 9/15/17.
 */

public class MainPresenter {

    TopicRepository repository;

    Observable<Long> timerObservable;

    public MainPresenter(TopicRepository repository) {
        this.repository = repository;
    }

    public Observable<Long> getCounter() {
        if (timerObservable == null) {
            timerObservable = getObservableTimer();
        }
        // TODO: 23/09/17 check if works out of main fragment
        return timerObservable.flatMap(Observable::just);
    }

    private Observable<Long> getObservableTimer() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .take((int) TopicPomodoroConstants.TWENTY_FIVE_MINS_SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public List<Topic> getTopics() {
        return repository.getTopics();
    }

    public void stopCounter() {

    }




}
