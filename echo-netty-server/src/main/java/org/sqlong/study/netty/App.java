package org.sqlong.study.netty;

/**
 * Hello world!
 *
 */

public class App {
    public static void main( String[] args ) throws InterruptedException {
        int port = Integer.parseInt(args[0]);
        System.out.println( "echo server start by port : " + port );

        EchoServer echoServer = new EchoServer(port);
        echoServer.start();

        System.out.println("echo server start ok !");
    }
}
