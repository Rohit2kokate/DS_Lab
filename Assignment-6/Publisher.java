import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        // Create a connection factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        
        // Create a connection and start it
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        // Create a non-transactional session with automatic acknowledgment
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Create a topic for the publisher to send messages to
        Topic topic = session.createTopic("CL9");
        
        // Create a producer for the topic
        MessageProducer producer = session.createProducer(topic);
        
        // Create a text message
        TextMessage message = session.createTextMessage();
        message.setText("This is a new message from publisher");
        
        // Send the message to the topic
        producer.send(message);
        
        System.out.println("Sent message: '" + message.getText() + "'");
        
        // Close the connection
        connection.close();
    }
}
