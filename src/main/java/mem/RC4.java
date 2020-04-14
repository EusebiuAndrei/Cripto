package mem;

import java.util.Random;

public class RC4 implements NumberGenerator {
    private final int[] S = new int[256];
    private final int[] T = new int[256];
    private final int keylen;

    public void displayArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

        System.out.println();
    }

    public RC4() {
        int[] key = getRandomKey();
        keylen = key.length;
        displayArray(key);

        for (int i = 0; i < 256; i++) {
            S[i] = i;
            T[i] = key[i % keylen];
        }

        int j = 0, tmp;

        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) % 256;
            tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
        }

        displayArray(S);
    }

    public int trans(int i, int j) {
        int tmp;
        i = (i + 1) % 256;
        j = (j + S[i]) % 256;
        tmp = S[j];
        S[j] = S[i];
        S[i] = tmp;
        return S[(S[i] + S[j]) % 256];
    }

    public void generate() {
        int[] sir = new int[256];
        int j = 0;

        for(int i = 0; i < 256; i++) {
            sir[i] = trans(i, j);
        }

        displayArray(sir);
    }

    private int[] getRandomKey() {
        Random random = new Random();
        int[] key = new int[16];

        for(int i = 0; i < 16; i++) {
            int bit = random.nextInt(2);
            key[i] = bit;
        }

        return key;
    }
}