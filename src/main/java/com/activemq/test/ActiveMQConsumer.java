package com.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @ClassName
 * @Auther: Uny
 * @Date: 2019/4/4 14:53
 * @Description: TODO
 * @Version 1.0
 */
public class ActiveMQConsumer {
    @Test
    public void testConsumeMsg() throws Exception {
        // 连接工厂
        // 使用默认用户名、密码、路径
        // 路径 tcp://host:61616
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        // 获取一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //建立会话,第一个参数是否开启事务,为true时,最后需要session.conmit()的提交
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列或者话题对象
        Queue queue = session.createQueue("HelloActiveMQ");
        // 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

        while (true) {
            TextMessage message = (TextMessage) messageConsumer.receive(5000);
            if (message != null) {
                System.out.println(message.getText());
            } else {
                break;
            }
        }
    }
}
