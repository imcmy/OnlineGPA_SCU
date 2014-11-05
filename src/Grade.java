/**
 * Class Grade
 * ������ȡ�ɼ�ҳ��ĳɼ����Ƚ��䴫���ַ���
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
	
		/* ������ַ�ַ��� */
        String uriAPI = 
        		"http://202.115.47.141/gradeLnAllAction.do?type=ln&oper=sxinfo&lnsxdm=001#qb_001";
        
        /* ����HttpPost���� */
        HttpPost httpRequest = new HttpPost(uriAPI);
        
        try {
        	//��֮ǰ��cookie�ŵ��˴�POST����Ҫ��ͷ��Ϣ��
        	httpRequest.setHeader("Cookie","JSESSIONID=" + cookies.get(0).getValue());
        	
        	/* ����HTTP request */  
        	HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
        	
        	/* ��״̬��Ϊ200*/
        	if (httpResponse.getStatusLine().getStatusCode() == 200) {
        		
        		/* �Ѵ���ҳ��ȡ�������ݶ���buffer */
                StringBuffer sb = new StringBuffer();
                HttpEntity entity = httpResponse.getEntity();
                InputStream is = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
                
                /* ��bufferת��string */
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
