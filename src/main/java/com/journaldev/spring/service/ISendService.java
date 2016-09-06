package com.journaldev.spring.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ISendService {

	boolean sendMessage(String message) throws IOException, TimeoutException;

}
