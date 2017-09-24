package personal.alvarez.topicpomodoro.repositories;

import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import personal.alvarez.topicpomodoro.models.Topic;

/**
 * Created by Oscar Álvarez on 22/09/17.
 */

public class TopicRepository {

    public List<Topic> getTopics() {
        if (getDbSize() == 0) {
            createFirstTopic();
        }
        return SQLite.select().
                from(Topic.class)
                .queryList();
    }

    private void createFirstTopic() {
        Topic t1 = new Topic();
        t1.setColor(0);
        t1.setDaysPerWeek(7);
        t1.setName("General Topic");
        t1.setPomodorosPerDay(1);
        t1.save();

        Topic t2 = new Topic();
        t2.setColor(0);
        t2.setDaysPerWeek(7);
        t2.setName("General Topic 2");
        t2.setPomodorosPerDay(1);
        t2.save();

        Topic t3 = new Topic();
        t3.setColor(0);
        t3.setDaysPerWeek(7);
        t3.setName("General Topic 3");
        t3.setPomodorosPerDay(1);
        t3.save();
    }

    public void createNewTopic(Topic topic) {

    }

    public boolean removeTopic() {
        return false;
    }

    public void editTopic(Topic topic, int topicId) {

    }

    public long getDbSize() {
        return SQLite.select(Method.count())
                .from(Topic.class)
                .count();
    }


}
