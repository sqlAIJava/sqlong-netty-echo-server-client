package org.sqlong.study.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )  {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println( "echo client start by host, port : " + host + ", " + port );

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(new Thread(() -> {
                EchoClient client = new EchoClient(host, port, finalI);
                try {
                    client.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }

        executorService.shutdown();
    }
}
