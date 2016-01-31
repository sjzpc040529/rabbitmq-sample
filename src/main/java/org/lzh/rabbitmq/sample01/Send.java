package org.lzh.rabbitmq.sample01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

/**
 * 连接到RabbitMQ（此时服务需要启动），发送一条数据，然后退出。
 * 发送端，即生产者
 */
public class Send
{
    //队列名称
    private final static String QUEUE_NAME = "hello";
    private final  static String MQ_HOST="127.0.0.1";

    public static void main(String[] argv) throws java.io.IOException, TimeoutException, InterruptedException {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost(MQ_HOST);
        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送的消息
        String message = "hello world2!";
        for (int i=0 ;i<100;i++){
            message="this is message==>"+i;
            //往队列中发出一条消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(1000L);
        }

        //关闭频道和连接
        channel.close();
        connection.close();
    }
}
