import java.io.IOException;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the topic from which we will receive messages from = "CL9"
    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        
        // Create a connection and start it
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        // Create a session with automatic acknowledgment
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Create a topic to receive messages from
        Topic topic = session.createTopic("CL9");
        
        // Create a consumer for the topic
        MessageConsumer consumer = session.createConsumer(topic);
        
        // Create a listener to handle received messages
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received message: '" + textMessage.getText() + "'");
                    }
                } catch (JMSException e) {
                    System.out.println("Caught: " + e);
                    e.printStackTrace();
                }
            }
        };
        
        // Set the listener to consume messages
        consumer.setMessageListener(listener);
        
        // Keep the subscriber running to receive messages
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Close the connection after receiving the message
        connection.close();
    }
}
