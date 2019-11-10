import java.util.ArrayList;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receive {

	static Double aPrice = 0.0;
	static Double mPrice = 0.0;
	static int flag = 0;
	static ArrayList<Double> list1 = new ArrayList<>(), list2 = new ArrayList<>();

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("apple", false, false, false, null);
		channel.queueDeclare("microsoft", false, false, false, null);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String queue = delivery.getEnvelope().getRoutingKey();
			String message = new String(delivery.getBody(), "UTF-8");

			// System.out.println(queue + " -> " + message);

			if (queue.equals("apple")) {
				aPrice = Double.parseDouble(message);
				list1.add(aPrice);
			}
			if (queue.equals("microsoft")) {
				mPrice = Double.parseDouble(message);
				list2.add(mPrice);
			}

			printList(list1, "apple prices");
			printList(list2, "microsoft prices");
		};

		channel.basicConsume("apple", true, deliverCallback, consumerTag -> {
		});
		channel.basicConsume("microsoft", true, deliverCallback, consumerTag -> {
		});

	}

	public static void printList(ArrayList<Double> list, String name) {
		if (list.size() >= 10) {
			System.out.println(name);
			list.forEach((x) -> {
				System.out.printf("%.4f", x);
				System.out.print(" | ");
			});
			System.out.println();
			System.out.println();
			list.clear();
		}
	}
}