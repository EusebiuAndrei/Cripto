package mem;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerHelper {
    static public BigInteger getNumber3mod4() {
        // generam number pe 512 bits, prim (3 mod 4)
        Random r = new Random();
        BigInteger number;

        do {
            number = BigInteger.probablePrime(512, r);
        } while(!number.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)));

        return number;
    }

    static public BigInteger getJacobiSign(BigInteger a, BigInteger n) {
        BigInteger b = a.mod(n);
        BigInteger c = n;
        int s = 1;

        while(b.compareTo(BigInteger.valueOf(2)) >= 0) {
            while(b.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(0)))
                b = b.divide(BigInteger.valueOf(4));

            if(b.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0))) {
                if(c.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(3)) || c.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5))) {
                    s = (-1) * s;
                }
                b = b.divide(BigInteger.valueOf(2));
            }

            if(b.equals(BigInteger.valueOf(1))) break;

            if(b.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && c.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
                s = (-1) * s;
                BigInteger aux = b;
                b = c.mod(b);
                c = aux;
            }

            if(b.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(1)) || c.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(1))) {
                BigInteger aux = b;
                b = c.mod(b);
                c = aux;
            }
        }

        return b.multiply(BigInteger.valueOf(s));
    }
}
