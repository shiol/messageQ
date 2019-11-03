import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receive {
	static Double aPrice = 0.0;
	static Double mPrice = 0.0;
	static int flag = 0;

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String queue = delivery.getEnvelope().getRoutingKey();
			String message = new String(delivery.getBody(), "UTF-8");

			System.out.println(queue + " -> " + message);

			if (queue.equals("apple")) {
				aPrice = Double.parseDouble(message);
				flag++;
			}
			if (queue.equals("microsoft")) {
				mPrice = Double.parseDouble(message);
				flag++;
			}

			if ((aPrice < mPrice) & flag == 2) {
				System.out.println("buy apple");
				System.out.println();
				flag = 0;
			} else if (flag == 2) {
				System.out.println("buy microsoft");
				System.out.println();
				flag = 0;
			}
		};

		channel.basicConsume("apple", true, deliverCallback, consumerTag -> {
		});
		channel.basicConsume("microsoft", true, deliverCallback, consumerTag -> {
		});

	}
}