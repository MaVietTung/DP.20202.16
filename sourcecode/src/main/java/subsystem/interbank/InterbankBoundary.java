package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.HttpClient;

public class InterbankBoundary {
	//Data COupling: DUng tham so de thuc hien luong thuc thi
	String query(String url, String data) {
		String response = null;
		try {
			response = HttpClient.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
