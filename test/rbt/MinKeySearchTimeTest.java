package rbt;

import java.text.DecimalFormat;

public class MinKeySearchTimeTest {

    public static void main(String[] args) {
        
        int MAX = 400;
        int N_ELEMENTS = 4;
        
        int cnt = 0;

        long startTime;
        long endTime;

        double mapTime;
        double vecTime;

        DecimalFormat df = new DecimalFormat("0");

        Map<Integer, Integer> map = new Map();

        Integer vector[] = new Integer[MAX];
        Integer tmp;

        for (int i = 0; i < MAX; i += N_ELEMENTS) {

            cnt += N_ELEMENTS;

            for (int j = 0; j < N_ELEMENTS; j++) {

                vector[i + j] = MAX - i - j;
                map.setValue(MAX - i - j, MAX - i - j);
            }

            startTime = System.nanoTime();
            map.getValue(MAX - i - (N_ELEMENTS - 1));
            endTime = System.nanoTime();
            mapTime = (double) (endTime - startTime);

            startTime = System.nanoTime();
            tmp = getMin(vector, i + N_ELEMENTS);
            endTime = System.nanoTime();
            vecTime = (double) (endTime - startTime);

            System.out.println(cnt + " elements\t | Map: " + df.format(mapTime) + "ns\t | Vector: " + df.format(vecTime) + "ns");
        }

    }

    public static Integer getMin(Integer[] vec, int len) {

        Integer tmp = 0;

        for (int i = 0; i < len; i++) {

            if (vec[i].compareTo(tmp) > 0) {

                tmp = i;
            }
        }

        return tmp;
    }

}
