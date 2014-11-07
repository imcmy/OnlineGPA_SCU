import java.text.DecimalFormat;

public class OnlineGPA {
	
	private String summary;
	private float grade = 0;
	private float GPA = 0;
	private String[] info;
	// 课程号
    private String CNO[];
    // 课序号
    private String CN[];
    // 课程名
    private String Cname[];
    // 英文课程名
    private String CEname[];
    // 学分
    private float Cscore[];
    // 课程属性
    private String Cproperty[];
    // 分数
    private float Cgrade[];
    private int length;
    private String finalText;
	
	public OnlineGPA(String zjh, String mm) {
		
		Cookies cookies = new Cookies(zjh, mm);
		Grade grades = new Grade(cookies.getCookies());
		UserInfo userinfo = new UserInfo(cookies.getCookies());
		Filter filter = new Filter(grades.getGrade(), userinfo.getInfo());
		Calculate calc = new Calculate(filter.getFilter(), filter.getGradeLength());
		
		info = filter.getInfoFilter();
		summary = calc.getSummary();
		grade = calc.getAverageGrade();
		GPA = calc.getGPA();
		CNO = calc.getCNO();
		CN = calc.getCN();
		Cname = calc.getCname();
		CEname = calc.getCEname();
		Cscore = calc.getCscore();
		Cproperty = calc.getCproperty();
		Cgrade = calc.getCgrade();
		length = CNO.length;
		
		if (grade == 0) {
			
			finalText = "Error!";
		} else {
			
			DecimalFormat df = new DecimalFormat("0.00");
			finalText = "你的必修课加权平均分为：" + df.format(grade) + "    " + "你的必修课绩点为：" + df.format(GPA);
		}
	}
	
	public String getSummary() {
		
		return summary;
	}
	
	public String getFinal() {
		
		return finalText;
	}
	
	public String[] getCNO() {
		
		return CNO;
	}
	
	public String[] getCN() {
		
		return CN;
	}
	
	public String[] getCname() {
		
		return Cname;
	}
	
	public String[] getCEname() {
		
		return CEname;
	}
	
	public float[] getCscore() {
		
		return Cscore;
	}
	
	public String[] getCproperty() {
		
		return Cproperty;
	}
	
	public float[] getCgrade() {
		
		return Cgrade;
	}

	
	public int getLength() {
		
		return length;
	}
	
	public String getName() {
		
		return info[1] + ", 您好!";
	}
}
