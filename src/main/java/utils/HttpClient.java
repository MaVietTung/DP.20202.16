package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
// Communication cohesion: Cac phuong thuc thuc hien viec chia se du lieu cua thuoc tinh Logger va co các mục đích thức hiện có liên quan mật thiết đến nhau
public class HttpClient {

	public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	public static String get(String url, String token) throws Exception {
		LOGGER.info("Request URL: " + url + "\n");
		HttpURLConnection conn = setupConnection(url, "GET");
		conn.setRequestProperty("Authorization", "Bearer " + token);

        String response = getStringResponse(conn);
		LOGGER.info("Response Info: " + response);
		return response;
	}

	public static String post(String url, String payload) throws IOException {
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");
		HttpURLConnection conn = setupConnection(url, "PATCH");
		addPayloadToConnection(payload, conn);

		String response = getStringResponse(conn);
		LOGGER.info("Response Info: " + response);
		return response;
	}

	private static void addPayloadToConnection(String payload, HttpURLConnection conn) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
	}

	private static String getStringResponse(HttpURLConnection conn) throws IOException {
		BufferedReader in;
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		return response.toString();
	}

	//Data Coupling: Dung cac tham so de thuc hien luong thuc thi cua minh
	private static HttpURLConnection setupConnection(String url, String method) throws IOException {
		allowMethods(method.toUpperCase());
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestMethod(method.toUpperCase());
		return conn;
	}

	//Data Coupling: Dung cac tham so de thuc hien luong thuc thi cua minh
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
}
