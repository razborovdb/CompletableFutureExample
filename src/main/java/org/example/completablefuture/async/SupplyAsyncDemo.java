package org.example.completablefuture.async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {

    public Boolean getInteger() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<Boolean> listCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Executed by : " + Thread.currentThread().getName());
                    return List.of(1, 2, 3, 4);
                },executor)
                .thenApplyAsync(list -> {
                    list.forEach(System.out::println);
                    return true;
                }, executor);
        return listCompletableFuture.get();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        boolean result = supplyAsyncDemo.getInteger();
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
            Thread.sleep(1000);
        }
    }
}
