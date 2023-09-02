package step.learning.services.random;

import java.util.Random;

public class RandomServiceV1 implements RandomService {
    private final Random random = new Random() ;
    @Override
    public int getInt() {
        return random.nextInt( 1000 ) ;
    }

    @Override
    public double getDouble() {
        return random.nextDouble() * 1000.0 ;
    }

    @Override
    public String getString() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = characters.length() ;
        StringBuilder randomString = new StringBuilder() ;
        int stringLength = 8 ;
        for( int i = 0; i < stringLength; i++ ) {
            int randomIndex = random.nextInt( length ) ;
            char randomChar = characters.charAt( randomIndex ) ;
            randomString.append( randomChar ) ;
        }
        return randomString.toString() ;
    }
}
