package mem;

public class LFSR implements NumberGenerator {
    static private final long POLY_MASK_32 = Long.parseLong("F73593FF", 16);
    static private final long SEED_32 = Long.parseLong("E553F0FF", 16);
    static private final long PERIOD_BIGGEST_FACTOR = 65537;

    @Override
    public void generate() {
        printDetails();
        long lfsrOn32Bits = generateWithLFSR(LFSR.SEED_32, LFSR.POLY_MASK_32);
        System.out.println("Nr of bits 0: " + nrOfBits0(lfsrOn32Bits));
        System.out.println("Nr of bits 1: " + nrOfBits1(lfsrOn32Bits));
    }

    public long generateWithLFSR(long seed, long mask) {
        long nrOfBits = Long.toBinaryString(seed).length();

        for(int i = 0; i < LFSR.PERIOD_BIGGEST_FACTOR; i++) {
            long seedAndMask = seed & mask;
            long bit = nrOfBits1(seedAndMask) % 2;
            long feedback = (seed >> 1) | (bit << (nrOfBits - 1));
            seed = feedback;
        }

        return seed;
    }

    private long nrOfBits0(long number) {
        String binaryNumberString = Long.toBinaryString(number).replace("1", "");
        return binaryNumberString.length();
    }

    private long nrOfBits1(long number) {
        String binaryNumberString = Long.toBinaryString(number).replace("0", "");
        return binaryNumberString.length();
    }

    public void printDetails() {
        System.out.println("Seed: " + SEED_32 + " Mask: " + POLY_MASK_32);
    }
}
