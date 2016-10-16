import javafx.scene.transform.MatrixType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ErrorManager;

/**
 * Created by Julia on 12.10.2016.
 */
public class Algorithms {

    public static void main(String[] args) {
        Algorithms algorithms = new Algorithms();
        long NOD = algorithms.NODEvklideAlgorithm(7,5);
        System.out.println(NOD);

        System.out.println(algorithms.powerRecurtion(562, 6));
        int[] n = {2, 2, 6};
        int[] m = {3, 5, 7};
        System.out.println(algorithms.CRTAlgorithm(m, n));
        // System.out.println(algorithms.LagandreYakobySymbol(219,383));
        System.out.println(algorithms.sqrtModuleP(53,10));
        System.out.println(Arrays.toString(algorithms.fullEuklid(7,3)));
    }

    public long NODEvklideAlgorithm(long x, long y) {
        if (x == 0) {
            throw new NullPointerException("X is equals 0");
        } else {
            if (y == 0) {
                throw new NullPointerException("Y is equals 0");
            }
        }
        long temp;
        if (y > x) {
            temp = x;
            x = y;
            y = temp;
        }

        while (y > 0) {
            temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }

    @Deprecated
    public long[] NODEvklideFullAlgorithm(long x, long y) {
        if (x == 0) {
            throw new NullPointerException("X is equals 0");
        } else {
            if (y == 0) {
                throw new NullPointerException("Y is equals 0");
            }
        }
        long temp;
        if (y > x) {
            temp = x;
            x = y;
            y = temp;
        }
        HashMap<String, Long> nameToValueMap = new HashMap<>();
        nameToValueMap.put("a", 1L);
        nameToValueMap.put("b", 0L);
        nameToValueMap.put("g", x);
        nameToValueMap.put("u", 0L);
        nameToValueMap.put("v", 1L);
        nameToValueMap.put("w", y);

        long q;
        long tempA;
        long tempB;
        long tempG;
        while (nameToValueMap.get("w") > 0) {
            q = nameToValueMap.get("g") / nameToValueMap.get("w");

            tempA = nameToValueMap.get("a");
            nameToValueMap.replace("a", nameToValueMap.get("u"));

            tempB = nameToValueMap.get("b");
            nameToValueMap.remove("b", nameToValueMap.get("v"));

            tempG = nameToValueMap.get("g");
            nameToValueMap.replace("g", nameToValueMap.get("w"));

            nameToValueMap.replace("u", (tempA - q * nameToValueMap.get("u")));
            nameToValueMap.replace("v", (tempB - q * nameToValueMap.get("v")));
            nameToValueMap.replace("w", (tempG - q * nameToValueMap.get("w")));
        }
        long[] abgArray = new long[3];
        abgArray[0] = nameToValueMap.get("a");
        abgArray[1] = nameToValueMap.get("b");
        abgArray[2] = nameToValueMap.get("g");
        return abgArray;
    }

    public int[] fullEuklid(int a, int b){
        int[] result = new int[3];

        if (b == 0){
            result[2] = a;
            result[0] = 1;
            result[1] = 0;
        }
        int x2 = 1;
        int x1 = 0;
        int y1 = 1;
        int y2 = 0;
        int q;
        int r;
        while (b > 0){
            q =  (int) a/b;
            r = a - q*b;
            result[0] = x2 - q*x1;
            result[1] = y2 - q*y1;

            a = b; b = r; x2 = x1; x1 = result[0]; y2 = y1; y1 = result[1];
        }
        result[2] = a;
        result[0] = x2;
        result[1] = y2;
        return result;
    }

    public long powerRecurtion(long x, int n) {
        long value;
        if (n == 1) {
            return x;
        } else {
            if (n % 2 == 0) {
                value = (long) Math.pow(powerRecurtion(x, (n / 2)), 2);
            } else {
                value = (long) Math.pow(powerRecurtion(x, (n - 1) / 2), 2);
                value = x * value;
            }
        }
        return value;
    }


    public int CRTAlgorithm(int[] mArray, int[] nArray) {
        int r = mArray.length;
        int[] k = new int[r + 1];
        double[] c = new double[r + 1];
        for (int i = 1; i < r; i++) {
            k[i] = 1;
            for (int j = 0; j <= (i - 1); j++) {
                k[i] = k[i] * mArray[j];
            }
            c[i] = fullEuklid(k[i], mArray[i])[0] % mArray[i];
        }
        int M = k[r - 1] * mArray[r - 1];

        int n = nArray[0];
        double u;
        for (int i = 1; i < r; i++) {
            u = ((nArray[i] - n) * c[i]) % mArray[i];
            n = n + (int) u * k[i];
        }
        n = n % M;
        return n;
    }

    public int LagandreYakobySymbol(int m, int a) {

        a = a % m;
        int t = 1;
        int temp;
        while (!(a == 0)) {
            while (a % 2 == 0) {
                a = a / 2;
                if ((m % 8) >= 3 && (m % 8) <= 5) {
                    t = -t;
                }
            }
            temp = a;
            a = m;
            m = temp;
            if (a == m && m % 4 == 3) {
                t = -t;
            }
            a = a % m;
        }
        if (m == 1) return t;
        return 0;
    }

    public int sqrtModuleP(int p, int a) {
        a = a % p;
        if (p % 8 == 3 || p % 8 == 7) {
            int x = (int) Math.pow(a, (p + 1) / 4) % p;
            return x;
        }
        if (p % 8 == 5) {
            int x = (int) Math.pow(a, (p + 3) / 8) % p;
            int c = (int) Math.pow(x, 2) % p;
            if (!(c == a % p)) {
                x = x * (int) Math.pow(2, (p - 1) / 4) % p;
                return x;
            }
        }
        return -1025468;
    }


}

