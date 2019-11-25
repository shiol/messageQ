import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("apple", false, false, false, null);
		channel.queueDeclare("microsoft", false, false, false, null);

		Random random = new Random(System.currentTimeMillis());
		Double aPrice = 1.0;
		Double mPrice = 1.0;
		Double min = 0.0;
		Double max = 20.0;

		while (true) {
			if (random.nextBoolean()) {
				aPrice = (Math.random() * ((max - min) + 1)) + min;
			
			}
			if (random.nextBoolean()) {
				mPrice = (Math.random() * ((max - min) + 1)) + min;
			
			}

			String aMessage = Double.toString(aPrice);
			String mMessage = Double.toString(mPrice);

			System.out.println("send apple price -> " + aMessage);
			System.out.println("send microsoft price -> " + mMessage);
			System.out.println();

			channel.basicPublish("", "apple", null, aMessage.getBytes("UTF-8"));
			channel.basicPublish("", "microsoft", null, mMessage.getBytes("UTF-8"));

			Thread.sleep(1000);
		}
	}
}