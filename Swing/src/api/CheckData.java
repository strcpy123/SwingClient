package api;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckData {
	String Sname = "Spidy";
	String Semail = "sss@gmail";
	String Smobile = "9874561330";
	String SmachineKey = "123456";
	String SproductKey = "SERT45";
	String SserialNumber = "1111111";

	public void checkMethod() {
		JSONObject reqObj = prepareReqJsonObj(Sname, Semail, Smobile, SmachineKey, SproductKey, SserialNumber);

		String reqString = reqObj.toString();
		String APIUrl = "http://localhost:9090/hello";

		String response = Utility.excutePost(APIUrl, reqString);

		System.out.println(" reqObj" + reqObj);
		System.out.println("reqString" + reqString);
		System.out.println("response" + response);
	}

	@SuppressWarnings("unchecked")
	public JSONObject prepareReqJsonObj(String s1, String s2, String s3, String s4, String s5, String s6) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("name", s1);
			jsonobj.put("emailid", s2);
			jsonobj.put("mobile", s3);
			jsonobj.put("machineKey", s4);
			jsonobj.put("productkey", s5);
			jsonobj.put("serialNo", s6);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}

	public static void main(String[] args) {
		CheckData obj = new CheckData();
		obj.checkMethod();
	}

}
