package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {

    private ArrayList<Integer> arr;

    private AsIntStream() {
        this.arr = new ArrayList<Integer>();
    }

    public static AsIntStream of(int... values) {
        AsIntStream newStream = new AsIntStream();
        for (int el: values) {
            newStream.arr.add(el);
        }
        return newStream;
    }

    @Override
    public Double average() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer max() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer min() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        /*
        int value = 0;
        IntConsumer action = value1 -> value1++;
        this.forEach(action);
        */
        return (long)1;
    }

    @Override
    public Integer sum() {
        /*
        int value = 0;
        this.forEach(value1 -> value += value1);
        */
        return 0;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream res = new AsIntStream();
        for (int i: arr) {
            if (predicate.test(i)) {
                System.out.println(i);
                res.arr.add(i);
            }
        }
        return res;
    }

    @Override
    public void forEach(IntConsumer action) {
        this.arr.forEach(x -> action.accept(x));

    }

    @Override
    public AsIntStream map(IntUnaryOperator mapper) {
        AsIntStream nw = new AsIntStream();
        this.arr.forEach(
                x -> {
                    System.out.println(mapper.apply(x));
                    nw.arr.add(mapper.apply(x));
                });
        return nw;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
