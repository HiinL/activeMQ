package com.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @ClassName
 * @Auther: Uny
 * @Date: 2019/4/4 14:48
 * @Description: TODO
 * @Version 1.0
 */
public class ActiveMQProducer {

    @Test
    public void testProduceMsg() throws Exception {
        //连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //获取连接
        Connection connection = connectionFactory.createConnection();

        //创建会话
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //创建队列
        Queue queue = session.createQueue("HelloActiveMQ");

        //创建生产者
        MessageProducer producer = session.createProducer(queue);

        //发送消息
        for (int i = 0; i < 10; i++) {
            producer.send(session.createTextMessage("activeMQ,你好!" + i));
        }
        //提交操作
        session.commit();
    }
}
