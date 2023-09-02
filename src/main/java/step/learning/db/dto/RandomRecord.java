package step.learning.db.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

/*
DTO - Data Transfer Object - об'єкт для передачі даних, синоним
 Entity, ORM, тощо. -- "Пасивний" об'єкт, задача якого "перевести"
 дані з якогось формату (JSON, DB, XML,...) у формат мови програмування.
 */
public class RandomRecord {
    private UUID id ;
    private int randInt ;
    private double randFloat ;
    private String randStr ;

    public RandomRecord() {
    }

    public RandomRecord( ResultSet res ) throws SQLException {
        // звертання до полів за назвою не вимагає певного порядку
        // їх слідування у результаті (у запиті)
        setId( UUID.fromString( res.getString( "id" ) ) ) ;
        setRandInt( res.getInt( "rand_int" ) ) ;
        setRandFloat( res.getDouble( "rand_float" ) ) ;
        setRandStr( res.getString( "rand_str" ) ) ;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.US,  // для гарантування, що десятична кома буде точкою
                "%s... %d %f %s",
                getId().toString().substring(0, 4),
                getRandInt(),
                getRandFloat(),
                getRandStr());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRandInt() {
        return randInt;
    }

    public void setRandInt(int randInt) {
        this.randInt = randInt;
    }

    public double getRandFloat() {
        return randFloat;
    }

    public void setRandFloat(double randFloat) {
        this.randFloat = randFloat;
    }

    public String getRandStr() {
        return randStr;
    }

    public void setRandStr(String randStr) {
        this.randStr = randStr;
    }
}
