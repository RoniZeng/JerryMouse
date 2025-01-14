package com.github.apachefoundation.jerrymouse.network.wrapper.nio;

import com.github.apachefoundation.jerrymouse.http.HttpRequest;
import com.github.apachefoundation.jerrymouse.http.HttpResponse;
import com.github.apachefoundation.jerrymouse.network.connector.nio.NioPoller;
import com.github.apachefoundation.jerrymouse.network.endpoint.nio.NioEndpoint;
import com.github.apachefoundation.jerrymouse.network.wrapper.SocketWrapper;

import java.io.IOException;
import java.nio.channels.SocketChannel;
/**
 * @Author: xiantang
 * @Date: 2019/4/17 14:45
 */
public class NioSocketWrapper implements SocketWrapper {

    private final NioEndpoint server;
    private final NioPoller poller;
    private HttpResponse response;
    private HttpRequest request;

    private volatile boolean newSocket;

    public NioEndpoint getServer() {
        return server;
    }

    public NioPoller getPoller() {
        return poller;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    private final SocketChannel socketChannel;

    public boolean isNewSocket() {
        return newSocket;
    }

    public NioSocketWrapper(NioEndpoint server, NioPoller poller, SocketChannel socketChannel, boolean isNewSocket) {
        this.server = server;
        this.poller = poller;
        this.socketChannel = socketChannel;
        this.newSocket = isNewSocket;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    @Override
    public void close()  {
        // 取消注冊
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
