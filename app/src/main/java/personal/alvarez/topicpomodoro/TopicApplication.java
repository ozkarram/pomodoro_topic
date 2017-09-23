package personal.alvarez.topicpomodoro;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import personal.alvarez.topicpomodoro.injection.AppComponent;
import personal.alvarez.topicpomodoro.injection.DaggerAppComponent;

/**
 * Created by Oscar Álvarez on 22/09/17.
 */

public class TopicApplication extends Application {

    static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        component = DaggerAppComponent.builder().build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
