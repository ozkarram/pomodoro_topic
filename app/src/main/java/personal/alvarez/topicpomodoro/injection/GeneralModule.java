package personal.alvarez.topicpomodoro.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import personal.alvarez.topicpomodoro.presenters.MainPresenter;
import personal.alvarez.topicpomodoro.presenters.TopicPresenter;
import personal.alvarez.topicpomodoro.repositories.TopicRepository;

/**
 * Created by Oscar Álvarez on 22/09/17.
 */
@Module
public class GeneralModule {

    @Singleton
    @Provides
    public TopicRepository providesTopicRepository() {
        return new TopicRepository();
    }

    @Singleton
    @Provides
    public TopicPresenter providesTopicPresenter(TopicRepository repository) {
        return new TopicPresenter(repository);
    }

    @Singleton
    @Provides
    public MainPresenter providesMainPresenter() {
        return new MainPresenter();
    }
}
