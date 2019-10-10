package until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
   public static String getTime() {
       Date date = new Date();
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return format.format(date);
   }
}
