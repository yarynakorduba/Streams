package ua.edu.ucu.stream;
import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

import java.util.ArrayList;

public class AsIntStream implements IntStream {

    private ArrayList<Integer> arr;

    private AsIntStream() {
        this.arr = new ArrayList<Integer>();
    }

    public static AsIntStream of(int... values) {
        AsIntStream newStream = new AsIntStream();
        for (int el : values) {
            newStream.arr.add(el);
        }
        return newStream;
    }

    @Override
    public Double average() {
        return (double)sum()/count();
    }

    @Override
    public Integer max() {
        if (this.arr.isEmpty()) throw new IllegalArgumentException("The stream is empty");
        int maxi = 0;
        IntBinaryOperator op = new IntBinaryOperator() {
            @Override
            public int apply(int left, int right) {
                if (right > left) {
                    return right;
                }
                return left;
            }
        };
        return reduce(maxi, op);
    }

    @Override
    public Integer min() {
        if (this.arr.isEmpty()) throw new IllegalArgumentException("The stream is empty");
        int maxi = sum();
        IntBinaryOperator op = new IntBinaryOperator() {
            @Override
            public int apply(int left, int right) {
                if (right < left) {
                    return right;
                }
                return left;
            }
        };
        return reduce(maxi, op);
    }

    @Override
    public long count() {
        return this.arr.size();
    }

    @Override
    public Integer sum() {
        if (this.arr.isEmpty()) throw new IllegalArgumentException("The stream is empty");
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
        for (int i : arr) {
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
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        this.forEach(
                x -> {
                    System.out.println(func.applyAsIntStream(x));
                    AsIntStream ar = (AsIntStream)func.applyAsIntStream(x);
                    IntConsumer action = new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            temp.add(value);
                        }};
                    ar.forEach(action);
                });
        int[] intArray = new int[temp.size()];
        for (int el=0; el<temp.size(); el++) {
            intArray[el] = temp.get(el);
            System.out.println(intArray[el]);
        }
        return AsIntStream.of(intArray);
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
    public int[] toArray() {
        int[] arra = new int[(int)count()];
        for (int el=0; el<(int)count(); el++) {
            arra[el] = arr.get(el);
        }
        return arra;
    }

}
