/**
 * Class Filter
 * 解析HTML文件，将课程信息解析出来存入数组中
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Filter {
	
	private String gradeHTML;
	private String infoHTML;
	private String[] classes;
	private String[] info;
	private int gradeLength;
	private int infoLength;
	
	private void filterGradeHtml() {  
        
        int i = 0;
        
        Document doc = Jsoup.parse(gradeHTML);
        Elements classesInfo = doc.select("td[align=center]");
        
        gradeLength = classesInfo.size();
        classes = new String[gradeLength];
        for (Element classInfo : classesInfo) {
            classes[i++] = classInfo.text();
        }
    }
	
	private void filterInfoHtml() {  
        
        int i = 0;
        
        Document doc = Jsoup.parse(infoHTML);
        Elements infoInfo = doc.select("td[width=275]");
        
        infoLength = infoInfo.size();
        info = new String[infoLength];
        for (Element iInfo : infoInfo) {
            info[i++] = iInfo.text();
        }
    }

	public Filter(String grade, String info) {
		
		gradeHTML = grade;
		infoHTML = info;
		filterGradeHtml();
		filterInfoHtml();
	}
	
	public String[] getFilter() {
		
		return classes;
	}
	
	public String[] getInfoFilter() {
		
		return info;
	}
	
	public int getGradeLength() {
		
		return gradeLength;
	}
	
	public int getInfoLength() {
		
		return infoLength;
	}
}
