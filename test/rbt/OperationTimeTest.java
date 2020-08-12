package rbt;

import java.text.DecimalFormat;

public class OperationTimeTest {

    public static void main(String[] args) {

        int MAX = 2000;
        int N_ELEMENTS = 2;
        int N_ROUNDS = 100;

        int cnt = 0;

        long startTime;
        long endTime;

        double setTime = 0;
        double getTime = 0;

        DecimalFormat df = new DecimalFormat("0");

        for (int i = 0; i < MAX; i += N_ELEMENTS) {

            cnt += N_ELEMENTS;

            for (int k = 0; k < N_ROUNDS; k++) {

                Map<Integer, Integer> map = new Map();

                for (int j = 0; j < i; j++) {

                    map.setValue(j, j);
                }

                startTime = System.nanoTime();

                for (int j = i; j < i + N_ELEMENTS; j++) {

                    map.setValue(j, j);
                }

                endTime = System.nanoTime();
                setTime += (double) (endTime - startTime);
                startTime = System.nanoTime();

                for (int j = i; j < i + N_ELEMENTS; j++) {

                    map.getValue(j);
                }

                endTime = System.nanoTime();
                getTime += (double) (endTime - startTime);
            }

            getTime = (double) getTime / N_ROUNDS;
            setTime = (double) setTime / N_ROUNDS;

            System.out.println(cnt + " elements\t | setValue: " + df.format(setTime) + "ns\t | getValue: " + df.format(getTime) + "ns");

        }

    }
}
