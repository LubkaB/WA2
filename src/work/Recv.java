package work;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = null;
		try {
			connection = factory.newConnection();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {			
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				/*try {
					wait(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				StringTokenizer strTokenizer = new StringTokenizer(message);
				
				String action = strTokenizer.nextToken();
				
				if (action.equals("rating")) {
					//calculateRating(message);
					
					String json = message; //= Arrays.toString(message.split(" ", 2));
					json = json.replaceFirst("rating ", "");
					
					char c = json.charAt(0);
					String fileName = String.valueOf(c);
					json = json.substring(1);
					
					while (c != ' ') {
						c = json.charAt(0);
						
						fileName += String.valueOf(c);
						
						json = json.substring(1);
					}
					
					json.trim();
					fileName.trim();
					
					JSONArray jArray = new JSONArray(json);
					
					int count = 1;
					int total = 0;
					
					// to simulate the working
					for (int i = 1; i < 900000000; i++) {
						int a = 9999999%i;
					} 
					
					for (int i = 0; i < jArray.length(); i++) {
						total += jArray.getJSONObject(i).getInt("rating");
						count ++;
					}
					
					double avgRating = total/count;
					System.out.println(avgRating);
					
					String content = String.valueOf(avgRating);

					File file = new File("/School/CVUT/WA2/semestralka/responses/"+fileName+".txt");

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();
				}
				
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
	public void calculateRating (String in) {
		String json = Arrays.toString(in.split(" ", 2));
		
		JSONArray jArray = new JSONArray(json);
		
		int count = 1;
		int total = 0;
		
		for (int i = 0; i < jArray.length(); i++) {
			total += jArray.getJSONObject(i).getInt("rating");
			count ++;
		}
		
		double avgRating = total/count;
	}

}
