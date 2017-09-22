package personal.alvarez.topicpomodoro;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oalvarez on 9/15/17.
 */

public class MainPresenter {

    public MainPresenter() {

    }

    public Observable<Long> startTimer() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .take(1500)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
