package automation.core.utils;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Logger logger = LogManager.getLogger(DateUtils.class);

    public static long getTimeInMilliSecond() {
        Date d = new Date();
        long timeInMs;
        timeInMs = TimeUnit.MILLISECONDS.toMillis(d.getTime());
        logger.info("*** Time in millisecond is: " + timeInMs + " **** ");
        return timeInMs;
    }
}
