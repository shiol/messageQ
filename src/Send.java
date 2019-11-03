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

		Random random = new Random(new Random().nextInt());
		Double aPrice = 1.0;
		Double mPrice = 1.0;

		while (true) {
			if (random.nextBoolean())
				aPrice += random.nextDouble();
			else {
				aPrice -= random.nextDouble();
				if (aPrice <= 0)
					aPrice = 0.01;
			}
			if (random.nextBoolean())
				mPrice += random.nextDouble();
			else {
				mPrice -= random.nextDouble();
				if (mPrice <= 0)
					mPrice = 0.01;
			}

			String aMessage = Double.toString(aPrice);
			String mMessage = Double.toString(mPrice);

			channel.basicPublish("", "apple", null, aMessage.getBytes("UTF-8"));
			channel.basicPublish("", "microsoft", null, mMessage.getBytes("UTF-8"));

			Thread.sleep(1000);
		}
	}
}