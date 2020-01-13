package hanze.nl.infobord;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public  class ListenerStarter implements Runnable, ExceptionListener {
    private boolean queue;
    private boolean getAll;
    private Connection connection;
    private Session session;
    private String destinationName;

    ListenerStarter(boolean queue, boolean getAll, String destinationName) {
        this.queue = queue;
        this.getAll = getAll;
        this.destinationName = destinationName;
    }

    @Override
    public void run() {
        createConnection();
        listenToMessages();
//        cleanUp();
    }

    private void createConnection(){
        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        }catch (Exception e){
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }

    private void listenToMessages(){
        try{
            Destination destination = (queue) ?
                    session.createQueue(destinationName) : session.createTopic(destinationName);

            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener(new QueueListener());
        } catch (Exception e) {
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }

    private void cleanUp(){
        try{
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
}