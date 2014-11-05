/**
 * Class Grade
 * 用来获取成绩页面的成绩，比将其传入字符串
 */

import java.io.*;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;


public class Grade {
	
	private String grade;
	private String name;
	private List<Cookie> cookies = null;
	
	private void getGrades(List<Cookie> cookies) {
	
		/* 声明网址字符串 */
        String uriAPI = 
        		"http://202.115.47.141/gradeLnAllAction.do?type=ln&oper=sxinfo&lnsxdm=001#qb_001";
        
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
                grade = sb.toString();  
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public Grade(List<Cookie> cookies) {
		
		grade = "";
		getGrades(cookies);
	}
	
	public String getGrade() {
		
		return grade;
	}
}
