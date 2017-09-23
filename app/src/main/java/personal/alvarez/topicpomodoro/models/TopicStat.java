package personal.alvarez.topicpomodoro.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import personal.alvarez.topicpomodoro.db.TopicDatabase;

/**
 * Created by Oscar Álvarez on 22/09/17.
 */

@Table(database = TopicDatabase.class)
public class TopicStat extends BaseModel{

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    int topicId;

    @Column
    long pomodoroDate;
}
