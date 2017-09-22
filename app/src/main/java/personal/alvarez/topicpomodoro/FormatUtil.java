package personal.alvarez.topicpomodoro;

/**
 * Created by Oscar Álvarez on 21/09/17.
 */

public class FormatUtil {

    public static String getSecondsFormatted(float seconds) {
        float secs = seconds % 60;
        int mins = (int) seconds / 60;
        return mins + " min. " + secs + "s.";
    }
}
