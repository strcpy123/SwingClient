//package api;
//
//import java.io.BufferedInputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.apache.commons.io.IOUtils;
//import org.json.JSONObject;
//
//public class TestAPI {
//
//	public static void main(String[] args) {
//		TestAPI.Post_JSON();
//	}
//
//	public static void Post_JSON() {
//
//		String query_url = "http://localhost:9090/hello";
//		String json = "{ \"method\" : \"guru.test\", \"params\" : [ \"jinu awad\" ], \"id\" : 124 }";
//
//		try {
//
//			URL url = new URL(query_url);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setConnectTimeout(5000);
//			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			conn.setRequestMethod("POST");
//
//			OutputStream os = conn.getOutputStream();
//			os.write(json.getBytes("UTF-8"));
//			os.close();
//
//			// read the response
////			System.out.println(conn.getInputStream());
//			InputStream in = new BufferedInputStream(conn.getInputStream());
//			String result = IOUtils.toString(in, "UTF-8");
//
//			System.out.println(result);
//
//			System.out.println("result after Reading JSON Response");
//
//			JSONObject myResponse = new JSONObject(result);
//			System.out.println("jsonrpc- " + myResponse.getString("jsonrpc"));
//			System.out.println("id- " + myResponse.getInt("id"));
//			System.out.println("result- " + myResponse.getString("result"));
//
//			in.close();
//			conn.disconnect();
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//	}
//}
