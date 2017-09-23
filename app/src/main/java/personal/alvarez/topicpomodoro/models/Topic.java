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
public class Topic extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String name;

    @Column
    int daysPerWeek;

    @Column
    int pomodorosPerDay;

    @Column
    int color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public int getPomodorosPerDay() {
        return pomodorosPerDay;
    }

    public void setPomodorosPerDay(int pomodorosPerDay) {
        this.pomodorosPerDay = pomodorosPerDay;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
