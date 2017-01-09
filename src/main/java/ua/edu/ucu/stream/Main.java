package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntUnaryOperator;

/**
 * Created by cs.ucu.edu.ua on 1/8/2017.
 */
public class Main {
    public static void main(String[] args) {
        IntPredicate nw  = new IntPredicate() {
            @Override
            public boolean test(int value) {
                return (value>3);
            }
        };

        IntUnaryOperator mapp = new IntUnaryOperator() {
            @Override
            public int apply(int operand) {
                return operand+5;
            }
        };

        int[] rtt = {1, 2, 3, 4, 5};
        System.out.println(AsIntStream.of(rtt).min());


       // int[] nw = {-1, 2, 3, 4, 5};
        //System.out.println(AsIntStream.of(nw));
    }
}

