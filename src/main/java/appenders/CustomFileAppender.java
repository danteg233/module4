import org.apache.log4j.FileAppender;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomAppender extends FileAppender {

    @Override
    public void setFile(String file) {
        super.setFile(prependDate(file));
    }

    private static String prependDate(String filename) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date()) + "_" + filename;
    }
}
