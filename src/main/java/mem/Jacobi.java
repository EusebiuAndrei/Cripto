package mem;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Random;

public class Jacobi implements NumberGenerator {
    BigInteger n, seed;
    int L ;
    private boolean display;

    public Jacobi(boolean display) {
        this.display = display;
        init();
    }

    void init() {
        BigInteger p = BigIntegerHelper.getNumber3mod4();
        BigInteger q = BigIntegerHelper.getNumber3mod4();

        this.n = p.multiply(q);
        this.L = (int) Math.pow(2, 16);
        this.seed = BigInteger.valueOf((int)System.currentTimeMillis());

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

        if(display) System.out.println("Bits sequence:");
        for(int  i = 1; i <= L; i++){
            BigInteger elem_i = BigIntegerHelper.getJacobiSign(seed.add(BigInteger.valueOf(i)), n); // Jacobi  de (a+i/n)

            elem_i = elem_i.add(BigInteger.valueOf(1));
            elem_i = elem_i.divide(BigInteger.valueOf(2));

            if(display) if(i % 20 == 0) System.out.println();
            if(display) System.out.print(elem_i.mod(BigInteger.valueOf(2)) + " ");

            if(elem_i.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1)) == true)
                nrOfBits1++;
            else
                nrOfBits0++;

        }

        if(display) System.out.println("\nBits of 0: " + nrOfBits0);
        if(display) System.out.println("Bits of 1: " + nrOfBits1);
    }

}
