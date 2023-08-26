package step.learning.ioc;

import com.google.inject.Singleton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Singleton
public class Md5HashService implements HashService {
    public String hash( String str ) {
        MessageDigest hasher ;
        try {
            hasher = MessageDigest.getInstance( "MD5" ) ;
            hasher.update( str.getBytes() ) ;
            StringBuilder sb = new StringBuilder() ;
            for( int b : hasher.digest() ) {
                sb.append(
                        String.format(
                                "%02x",
                                b & 0xFF
                        )
                ) ;
            }
            return sb.toString() ;
        }
        catch( NoSuchAlgorithmException ex ) {
            System.err.println( ex.getMessage() ) ;
            return null ;
        }
    }
}
