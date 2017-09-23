package personal.alvarez.topicpomodoro.presenters;

import java.util.List;

import personal.alvarez.topicpomodoro.models.Topic;
import personal.alvarez.topicpomodoro.repositories.TopicRepository;

/**
 * Created by Oscar Álvarez on 22/09/17.
 */

public class TopicPresenter {

    TopicRepository topicRepository;

    public TopicPresenter(TopicRepository repository) {
        topicRepository = repository;
    }

    public List<Topic> getTopics() {
        return topicRepository.getTopics();
    }
}
