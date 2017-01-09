package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

/**
 * Created by cs.ucu.edu.ua on 1/8/2017.
 */
public class Main {
    public static void main(String[] args) {
        IntPredicate nw  = new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value>3;
            }
        };

        IntUnaryOperator mapp = new IntUnaryOperator() {
            @Override
            public int apply(int operand) {
                return operand+5;
            }
        };

        int[] rtt = {1, 2, 3, 4, 5};
        IntToIntStreamFunction itr = new IntToIntStreamFunction() {
            @Override
            public IntStream applyAsIntStream(int value) {
                return AsIntStream.of(value, value*(-1));
            }
        };
        System.out.println(AsIntStream.of(rtt).map(mapp));
        System.out.println(AsIntStream.of(rtt).flatMap(itr));
        System.out.println(AsIntStream.of(rtt).filter(nw));


        // int[] nw = {-1, 2, 3, 4, 5};
        //System.out.println(AsIntStream.of(nw));
    }
}

