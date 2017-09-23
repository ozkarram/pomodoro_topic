package personal.alvarez.topicpomodoro.injection;

import javax.inject.Singleton;

import dagger.Component;
import personal.alvarez.topicpomodoro.fragments.MainFragment;
import personal.alvarez.topicpomodoro.fragments.TopicFragment;
import personal.alvarez.topicpomodoro.presenters.TopicPresenter;

/**
 * Created by Oscar Álvarez on 22/09/17.
 */

@Singleton
@Component(modules = {GeneralModule.class})
public interface AppComponent {

    void inject(MainFragment mainFragment);

    void inject(TopicFragment topicFragment);

    void inject(TopicPresenter topicPresenter);
}
