package step.learning.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Newspaper extends Literature implements Periodic {
    private Date date ;
    private static final SimpleDateFormat dateParseFormat =
            new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat datePrintFormat =
            new SimpleDateFormat("dd.MM.yyyy");

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setDate(String date) throws ParseException {
        this.setDate( dateParseFormat.parse( date ) ) ;
    }

    @Override
    public String getCard() {
        return String.format(
                "Newspaper: '%s' (from %s)",
                super.getTitle(),
                datePrintFormat.format( this.getDate() )
        ) ;
    }

    @Override
    public String getPeriod() {
        return "Daily";
    }
}
