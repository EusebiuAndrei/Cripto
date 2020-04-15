package mem;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        BBS bbs = new BBS(true); // Blum Blum Shub Generator
//        bbs.generate();

//        Jacobi jacobi = new Jacobi(true); // Jacobi Generator
//        jacobi.generate();

//        LFSR lfsr = new LFSR(true); // Line-feedback shift register Generator
//        lfsr.generate();

//        RC4 rc4 = new RC4(true); // Rivest Cipher 4 Generator
//        rc4.generate();

        App.getTimeComparison();
    }

    public static long getRunningTime(NumberGenerator numberGenerator) {
        long startTime = System.nanoTime();
        numberGenerator.generate();
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public static void getTimeComparison() {
        BBS bbs = new BBS(false); // Blum Blum Shub Generator
        Jacobi jacobi = new Jacobi(false); // Jacobi Generator
        LFSR lfsr = new LFSR(false); // Line-feedback shift register Generator
        RC4 rc4 = new RC4(false); // Rivest Cipher 4 Generator

        long bbsRunningTime = App.getRunningTime(bbs);
        long jacobiRunningTime = App.getRunningTime(jacobi);
        long lfsrRunningTime = App.getRunningTime(lfsr);
        long rc4RunningTime = App.getRunningTime(rc4);

        System.out.println("BBS time:    " + bbsRunningTime);
        System.out.println("Jacobi time: " + jacobiRunningTime);
        System.out.println("LFSR time:   " + lfsrRunningTime);
        System.out.println("RC4 time:    " + rc4RunningTime);
    }
}
