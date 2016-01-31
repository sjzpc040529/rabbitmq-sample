package org.lzh.rabbitmq.sample01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

/**
 * ���ӵ�RabbitMQ����ʱ������Ҫ������������һ�����ݣ�Ȼ���˳���
 * ���Ͷˣ���������
 */
public class Send
{
    //��������
    private final static String QUEUE_NAME = "hello";
    private final  static String MQ_HOST="127.0.0.1";

    public static void main(String[] argv) throws java.io.IOException, TimeoutException, InterruptedException {
        /**
         * �����������ӵ�MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        //����MabbitMQ��������ip����������
        factory.setHost(MQ_HOST);
        //����һ������
        Connection connection = factory.newConnection();
        //����һ��Ƶ��
        Channel channel = connection.createChannel();
        //ָ��һ������
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //���͵���Ϣ
        String message = "hello world2!";
        for (int i=0 ;i<100;i++){
            message="this is message==>"+i;
            //�������з���һ����Ϣ
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(1000L);
        }

        //�ر�Ƶ��������
        channel.close();
        connection.close();
    }
}
