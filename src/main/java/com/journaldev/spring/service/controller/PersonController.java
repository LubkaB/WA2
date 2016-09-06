package com.journaldev.spring.service.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

import org.apache.cxf.helpers.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.journaldev.spring.dao.impl.JournalHome;
import com.journaldev.spring.dao.impl.ReaderHome;
import com.journaldev.spring.dao.impl.StyleHome;
import com.journaldev.spring.dao.impl.TitleHome;
import com.journaldev.spring.model.Journal;
import com.journaldev.spring.model.Reader;
import com.journaldev.spring.model.Style;
import com.journaldev.spring.model.Title;
import com.journaldev.spring.service.impl.SendServiceImpl;

@Controller
@Configuration
@ComponentScan("com.journaldev.spring")
public class PersonController {

	@Inject
	private SendServiceImpl sendService;

	@Autowired
	private StyleHome styleHome;
	@Autowired
	private TitleHome titleHome;
	@Autowired
	private ReaderHome readerHome;
	@Autowired
	private JournalHome journalHome;

	private Date parseStringToDate(String d) {
		Date date = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			date = formatter.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	////////////////////////////////////////////////////////
	// USER

	// ADD
	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public @ResponseBody String addUser(HttpServletRequest request) throws IOException {
		String jsonString = IOUtils.toString(request.getInputStream());
		JSONObject jObject = new JSONObject(jsonString);
		String name = jObject.getString("name");
		int age = jObject.getInt("age");

		Reader reader = new Reader(name, age);
		readerHome.persist(reader);

		String json = new Gson().toJson(reader);
		return json;
	}

	// REMOVE
	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/user/remove", method = RequestMethod.DELETE)
	public @ResponseBody void removeUser(HttpServletRequest request) throws IOException {
		String jsonString = IOUtils.toString(request.getInputStream());
		JSONObject jObject = new JSONObject(jsonString);
		String name = jObject.getString("name");

		int id = readerHome.findByName(name);
		Reader reader = readerHome.findById(id);
		readerHome.remove(reader);
	}

	// UPDATE
	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/user/update", method = RequestMethod.PUT)
	public @ResponseBody void updateUser(HttpServletRequest request) throws IOException {
		String jsonString = IOUtils.toString(request.getInputStream());
		JSONObject jObject = new JSONObject(jsonString);
		String name = jObject.getString("name");
		int age = jObject.getInt("age");

		int id = readerHome.findByName(name);
		Reader reader = readerHome.findById(id);
		reader.setAge(age);
		readerHome.update(reader);
	}

	////////////////////////////////////////////////////////
	// STYLE

	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/style", method = RequestMethod.GET)
	public @ResponseBody String getStyles() throws IOException {
		List<Style> l = styleHome.getAll();

		String json = new Gson().toJson(l);
		return json;
	}

	////////////////////////////////////////////////////////
	// TITLE

	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public @ResponseBody String getTitles() throws IOException {
		List<Title> l = titleHome.getAll();

		String json = new Gson().toJson(l);
		return json;
	}

	////////////////////////////////////////////////////////
	// JOURNAL

	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/journal/add", method = RequestMethod.POST)
	public @ResponseBody void createJournal(HttpServletRequest request) throws IOException {
		String jsonString = IOUtils.toString(request.getInputStream());
		JSONObject jObject = new JSONObject(jsonString);
		String name = jObject.getString("name");
		int age = jObject.getInt("age");
		String book = jObject.getString("book");
		String author = jObject.getString("author");
		String from = jObject.getString("from");
		String to = jObject.getString("to");
		String rating = jObject.getString("rating");
		String genre = jObject.getString("genre");

		int readerId = readerHome.findByName(name);
		int titleId = titleHome.findByName(book);

		if (titleId < 0) {
			int styleId = styleHome.findByName(genre);
			Title title = new Title(book, author, styleId);
			titleHome.persist(title);
			titleId = titleHome.findByName(book);
		}

		Journal journal = new Journal(parseStringToDate(from), parseStringToDate(to), readerId, titleId,
				rating.length());
		journalHome.persist(journal);
	}

	////////////////////////////////////////////////////////
	// RABBBITMQ

	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public @ResponseBody String send(HttpServletRequest request) throws IOException, TimeoutException {
		String jsonString = IOUtils.toString(request.getInputStream());
		JSONObject jObject = new JSONObject(jsonString);
		String name = jObject.getString("name");

		int titleId = titleHome.findByName(name);

		List<Journal> list = journalHome.getByTitleId(titleId);

		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");

		String json = new Gson().toJson(list);
		json = "rating " + uuid + " " + json;
		sendService.sendMessage(json);

		return uuid;
	}

	@CrossOrigin // (origins = "http://localhost:8080")
	@RequestMapping(value = "/message/get/{id}", method = RequestMethod.GET)
	public @ResponseBody String getResults(@PathVariable("id") String id)
			throws IOException, TimeoutException {
		File file = new File("C:/School/CVUT/WA2/semestralka/responses/"+id+" .txt");
		
		

		if (file.exists()) {
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			String s = bReader.readLine();
			bReader.close();
			return s;
		} else {
			return "wait";
		}
	}

}
