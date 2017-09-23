package personal.alvarez.topicpomodoro.db;

import com.raizlabs.android.dbflow.annotation.Database;

import personal.alvarez.topicpomodoro.TopicPomodoroConstants;

/**
 * Created by Oscar Álvarez on 22/09/17.
 */

@Database(name = TopicPomodoroConstants.DATABASE_NAME, version = TopicPomodoroConstants.DATABASE_VERSION)
public class TopicDatabase {

    public static final String NAME = TopicPomodoroConstants.DATABASE_NAME;

    public static final int VERSION = TopicPomodoroConstants.DATABASE_VERSION;
}
