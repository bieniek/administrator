package com.mbsoft.activemq.administrator.infrastructure.activemq;

import com.mbsoft.activemq.administrator.core.Administrator;
import com.mbsoft.activemq.administrator.core.WorkItemsQueue;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.*;

public class ActiveMQAdministrator implements Administrator {
    private ActiveMQConnectionFactory connectionFactory;

    public ActiveMQAdministrator(ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<WorkItemsQueue> list() {
        List<WorkItemsQueue> result = new ArrayList<>();


        ActiveMQConnection connection = null;
        try {
            connection = (ActiveMQConnection) connectionFactory.createConnection();
            connection.start();

            DestinationSource ds = connection.getDestinationSource();
            Set<ActiveMQQueue> queues = ds.getQueues();

            QueueSession queueSession =
                    connection.createQueueSession(true, Session.CLIENT_ACKNOWLEDGE);
            for(ActiveMQQueue queue : queues){
                QueueBrowser browser = queueSession.createBrowser(queue);
                Enumeration<?> messagesInQueue = browser.getEnumeration();
                long timestamp = Long.MAX_VALUE;
                long messagesInQueueSize = 0L;
                while (messagesInQueue.hasMoreElements()) {
                    TextMessage queueMessage = (TextMessage) messagesInQueue.nextElement();
                    if (queueMessage.getJMSTimestamp() < timestamp)
                        timestamp = queueMessage.getJMSTimestamp();
                    messagesInQueueSize++;
                }
                result.add(new WorkItemsQueue(queue.getQueueName(), timestamp, messagesInQueueSize, 1000L));
            }

            connection.stop();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void delete(String queueName) {
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("admin", "admin",
                        "tcp://DESKTOP-V9B0AR7:61616");

        ActiveMQConnection connection = null;
        try {
            connection = (ActiveMQConnection) connectionFactory.createConnection();
            connection.start();

            connection.destroyDestination(new ActiveMQQueue(queueName));

            connection.stop();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
