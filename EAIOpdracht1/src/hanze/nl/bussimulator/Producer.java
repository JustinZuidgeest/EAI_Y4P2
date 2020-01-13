package hanze.nl.bussimulator;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

class Producer {
    private String queueName;
    private Session session;
    private Connection connection;

    Producer(String queueName) {
        this.queueName = queueName;
    }

    void createConnection() {
        try{
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        }catch (Exception e){
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }

    void sendMessage(String message){
        try{
            Destination destination = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
        }catch (Exception e){
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }

    void cleanUp(){
        try{
            session.close();
            connection.close();
        }catch (Exception e){
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }
}
