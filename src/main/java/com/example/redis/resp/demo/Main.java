package com.example.redis.resp.demo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {

    static Socket socket;
    static PrintWriter printWriter;
    static BufferedReader bufferedReader;

    public static void main(String[] args) {
        try {
            // 1. 建立连接
            String host = "127.0.0.1";
            int port = 6379;
            socket = new Socket(host, port);
            // 2. 获取输出流、输入流
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            // 3.发出请求set test yhq
            sendRequest();
            // 解析响应
            Object response = handleResponse();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(socket)) {
                    socket.close();
                }
                if (Objects.nonNull(bufferedReader)) {
                    bufferedReader.close();
                }
                if (Objects.nonNull(printWriter)) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Object handleResponse() throws IOException{
        int read = bufferedReader.read();
        switch (read) {
            case '+' :
                return bufferedReader.readLine();
            case '-' :
                break;
            case ':' :
                break;
            case '$' :
                break;
            case '*' :
                break;
            default:
                throw new RuntimeException("not impl");
        }
        return null;
    }

    private static void sendRequest() {
        // set test yhq
        printWriter.println("*3");
        printWriter.println("$3");
        printWriter.println("set");
        printWriter.println("$4");
        printWriter.println("test");
        printWriter.println("$3");
        printWriter.println("yhq");

        printWriter.flush();
    }
}
