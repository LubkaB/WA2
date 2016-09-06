package com.journaldev.spring.service.impl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.journaldev.spring.service.ISendService;

@Service
public class SendServiceImpl implements ISendService {
	private final static String QUEUE_NAME = "hello";

	@Override
	public boolean sendMessage(String message) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
		
		return true;
	}

	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(SendServiceImpl.class);
	 * 
	 * @Inject private volatile AmqpTemplate amqpTemplate;
	 * 
	 * public static final String RABBIT_EXCHANGE = "eventExchange"; public
	 * static final String GENERAL_EVENT_ROUTE_KEY = "event.general.*"; public
	 * static final String ERROR_EVENT_ROUTE_KEY = "event.error.*";
	 * 
	 * @Override
	 * 
	 * @Transactional public boolean sendMessage(String message) {
	 * 
	 * 
	 * try {
	 * 
	 * logger.debug("Publishing event to RabbitMQ");
	 * this.amqpTemplate.convertAndSend(RABBIT_EXCHANGE,
	 * GENERAL_EVENT_ROUTE_KEY, ": " + message);
	 * 
	 * return true; } catch (Exception e) {
	 * 
	 * logger.debug("Publishing event to RabbitMQ");
	 * 
	 * return false; } }
	 */
}