import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;


public class UserInfo {
	
	private String info;
	private List<Cookie> cookies = null;
	
	private void getInfo(List<Cookie> cookies) {
	
		/* 声明网址字符串 */
        String uriAPI = 
        		"http://202.115.47.141/xjInfoAction.do?oper=xjxx";
        
        /* 建立HttpPost联机 */
        HttpPost httpRequest = new HttpPost(uriAPI);
        
        try {
        	//把之前的cookie放到此次POST所需要的头信息中
        	httpRequest.setHeader("Cookie","JSESSIONID=" + cookies.get(0).getValue());
        	
        	/* 发出HTTP request */  
        	HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
        	
        	/* 若状态码为200*/
        	if (httpResponse.getStatusLine().getStatusCode() == 200) {
        		
        		/* 把从网页获取到的内容读入buffer */
                StringBuffer sb = new StringBuffer();
                HttpEntity entity = httpResponse.getEntity();
                InputStream is = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
                
                /* 从buffer转入string */
                String data = "";  
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
                info = sb.toString();  
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public UserInfo(List<Cookie> cookies) {
		
		info = "";
		getInfo(cookies);
	}
	
	public String getInfo() {
		
		return info;
	}
}
