import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Cookies {
	
	private List<Cookie> cookies;
	private String zjh;
	private String mm;
	
	private void getCookie() {
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse;
		
		String uriAPI = "http://202.115.47.141/loginAction.do";
		
		/* 建立HTTP Post连线 */
		HttpPost httpRequest = new HttpPost(uriAPI);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		/* 以下三个数据就是我们的之前在POST里的数据 */
		params.add(new BasicNameValuePair("zjh", zjh));    //这是学号
		params.add(new BasicNameValuePair("mm", mm));   //这里的密码我用*取代了
		
		try {
			
		    // 发出HTTP request
		    httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		    
		    // 取得HTTP response
		    httpResponse = client.execute(httpRequest);
		    
		    // 若状态码为200, 返回值正常
		    if (httpResponse.getStatusLine().getStatusCode() == 200) {
		        // 获取返回的cookie
		        cookies = ((AbstractHttpClient)client).getCookieStore().getCookies();
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public Cookies(String zjh, String mm) {
		
		this.zjh = zjh;
		this.mm = mm;
		getCookie();
	}
	
	public List<Cookie> getCookies() {
		
		return cookies;
	}
}
