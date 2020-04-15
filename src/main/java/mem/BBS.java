package mem;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Random;

public class BBS implements NumberGenerator {

    BigInteger n;
    int L,  seed;
    private boolean display;

    public BBS(boolean display) {
        this.display = display;
        init();
    }

    void init() {
        BigInteger p = BigIntegerHelper.getNumber3mod4();
        BigInteger q = BigIntegerHelper.getNumber3mod4();

        this.n = p.multiply(q);
        this.L = (int) Math.pow(2, 16);
        this.seed = (int) System.currentTimeMillis();

        if(display) displayInit(p, q);
    }

    void displayInit(BigInteger p, BigInteger q) {
        System.out.println("P: " + p);
        System.out.println("Q: " + q);
        System.out.println("N: " + n);
        System.out.println("SEED: " + seed);
        System.out.println("L: " + L);
    }

    public void generate() {
        int nrOfBits0 = 0;
        int nrOfBits1 = 0;

        BigInteger x0 = new BigInteger(String.valueOf(seed * seed)).mod(n);
        if(display) System.out.println("X0: " + x0);

        if(display) System.out.println("Bits sequence:");
        for(int  i = 1; i <= L ; ++i){
            if(display) if(i % 20 == 0) System.out.println();

            BigInteger x = (x0.multiply(x0)).mod(n);
            if(display) System.out.print(x.mod(BigInteger.valueOf(2)) + " ");

            int bit = x.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1)) ? 1 : 0;
            nrOfBits0 += 1 - bit;
            nrOfBits1 += bit;

            x0 = x;
        }

        if(display) System.out.println("\nBits of 0: " + nrOfBits0);
        if(display) System.out.println("Bits of 1: " + nrOfBits1);
    }

}
