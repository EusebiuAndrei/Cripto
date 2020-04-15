package mem;

import java.util.Random;

public class LFSR implements NumberGenerator {
    private final int TAP;
    private final int seedLength = 32;
    private final int L = (int) Math.pow(2, 16);
    private int[] seed = new int[seedLength];
    private boolean display;

    public LFSR(boolean display) {
        this.display = display;

        Random r = new Random();
        TAP = r.nextInt(31);

        for(int  i = 0; i < seedLength; i ++) {
            seed[i] = r.nextInt(2);
        }
    }

    public void generate() {
        int nrOfBits0 = 0;
        int nrOfBits1 = 0;

        for(int nthFeed = 1; nthFeed <= L; nthFeed++){
            int nextBit = (seed[seedLength - 1] ^ seed[TAP]);
            if(display) System.out.print(nextBit);

            for(int i = seedLength - 1; i > 0; i--) {
                seed[i] = seed[i - 1];
            }
            seed[0] = nextBit;

            if(nextBit == 0)
                nrOfBits0++;
            else
                nrOfBits1++;
        }

        if(display) System.out.println();
        if(display) System.out.println("Numarul de biti de 1: " + nrOfBits1);
        if(display) System.out.println("Numarul de biti de 0: " + nrOfBits0);
    }
}
