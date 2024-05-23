package org.example;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;


class FactorialTask extends RecursiveTask<Long> {

    int[] array;

    public FactorialTask(int n) {
        array = IntStream.range(0, n).map(number -> number + 1).toArray();
    }

    public FactorialTask(int n, int start) {
        array = IntStream.range(start - 1, start - 1 + n).map(number -> number + 1).toArray();
    }

    @Override
    public Long compute() {
        if (array.length <= 2) {
            long result = (long) Arrays.stream(array).reduce(1, (a, b) -> a * b);
            return result;
        }
        int size=array.length / 2;
        int start=array[0];
        FactorialTask firstHalfArrayValueCounter = new FactorialTask(size, start);
        size=array.length-size;
        int i =  array.length / 2;
        start=array[i];
        FactorialTask secondHalfArrayValueCounter = new FactorialTask(size, start);
        firstHalfArrayValueCounter.fork();
        secondHalfArrayValueCounter.fork();
        return firstHalfArrayValueCounter.join() * secondHalfArrayValueCounter.join();
    }

}

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}
