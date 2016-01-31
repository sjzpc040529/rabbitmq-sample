package org.lzh.rabbitmq.sample02;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *  生产者 produces  发送端
 *  我们使用Thread.sleep来模拟耗时的任务。我们在发送到队列的消息的末尾添加一定数量的点，每个点代表在工作线程中需要耗时1秒，例如hello…将会需要等待3秒。我们使用Thread.sleep来模拟耗时的任务。我们在发送到队列的消息的末尾添加一定数量的点，每个点代表在工作线程中需要耗时1秒，例如hello…将会需要等待3秒。
 */
public class NewTask {
    //队列名称
    private final static String QUEUE_NAME = "workqueue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送10条消息，依次在消息后面附加1-10个点
        for (int i = 0; i < 10; i++) {
            String dots = "";
            for (int j = 0; j <= i; j++) {
                dots += ".";
            }
            String message = "helloworld" + dots + dots.length();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        //关闭频道和资源
        channel.close();
        connection.close();

    }


}
