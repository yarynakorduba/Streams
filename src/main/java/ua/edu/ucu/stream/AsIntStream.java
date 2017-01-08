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
    public Double average() { // !!!!!!!!!!!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer max() { // !!!!!!!!!!!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer min() { // !!!!!!!!!!!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        return this.arr.size();
    }

    @Override
    public Integer sum() {
        int sum = 0;
        IntBinaryOperator nw = new IntBinaryOperator() {
            @Override
            public int apply(int left, int right) {
                left += right;
                return left;
            }
        };
        return reduce(sum, nw);
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
        this.forEach(
                x -> {
                    System.out.println(mapper.apply(x));
                    nw.arr.add(mapper.apply(x));
                });
        return nw;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) { // !!!!!!!!!!!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int res = identity;
        for (int el: this.arr) {
            res = op.apply(res, el);

        }
        return res;
    }

    @Override
    public int[] toArray() { // !!!!!!!!!!!
        int[] arra = new int[(int)count()];
        for (int el=0; el<(int)count(); el++) {
           arra[el] = arr.get(el);
    }
    return arra;
    }

}
