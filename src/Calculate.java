import java.text.DecimalFormat;
import java.util.Arrays;

public class Calculate {

	private float averageGrade;
	private float GPA;
	private String[] classes;
	private int length;
	private String summary;
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

	private void Calc() {

		int i, j;
        StringBuffer sff = new StringBuffer();
        float totalScore = 0;
        float totalGrade = 0;
        float totalGPA = 0;

        CNO = new String[length/7];
        CN = new String[length/7];
        Cname = new String[length/7];
        CEname = new String[length/7];
        Cscore = new float[length/7];
        Cproperty = new String[length/7];
        Cgrade = new float[length/7];
        for (i=0, j=0; j<classes.length; i++) {

        	CNO[i] = classes[j++];
            CN[i] = classes[j++];
            Cname[i] = classes[j++];
            CEname[i] = classes[j++];
            Cscore[i] = Float.parseFloat(classes[j++]);
            Cproperty[i] = classes[j++];

            classes[j] = classes[j].substring(0, classes[j].length()-1);
            switch (classes[j]) {
            case "优秀":
            	classes[j] = "95.0";
            	break;
            case "良好":
            	classes[j] = "85.0";
            	break;
            case "中等":
            	classes[j] = "75.0";
            	break;
            case "及格":
            	classes[j] = "65.0";
            	break;
            case "不及格":
            	classes[j] = "0";
            	break;
            }
            Cgrade[i] = Float.parseFloat(classes[j++]);
        }

        /* 输出 */
        for (j=0; j<length/7; j++) {
            sff.append(CNO[j]).append("  ")
            .append(CN[j]).append("  ")
            .append(Cname[j]).append("  ")
            .append(CEname[j]).append("  ")
            .append(Cscore[j]).append("  ")
            .append(Cproperty[j]).append("  ")
            .append(Cgrade[j]).append("\n");
        }
        summary = sff.toString();

        /* 计算绩点和加权平均 */
        for (i=0; i<length/7; i++) {
        	if (Cproperty[i].equals("必修")) {
        		totalScore += Cscore[i];
        		totalGrade += Cgrade[i]*Cscore[i];

        		if (Cgrade[i] >= 95) {
    				totalGPA += 4.0*Cscore[i];
    			} else if (Cgrade[i] >= 90) {
    				totalGPA += 3.8*Cscore[i];
    			} else if (Cgrade[i] >= 85) {
    				totalGPA += 3.6*Cscore[i];
    			} else if (Cgrade[i] >= 80) {
    				totalGPA += 3.2*Cscore[i];
    			} else if (Cgrade[i] >= 75) {
    				totalGPA += 2.7*Cscore[i];
    			} else if (Cgrade[i] >= 70) {
    				totalGPA += 2.2*Cscore[i];
    			} else if (Cgrade[i] >= 65) {
    				totalGPA += 1.6*Cscore[i];
    			} else if (Cgrade[i] >= 60) {
    				totalGPA += 1.0*Cscore[i];
    			} else {
    				totalGPA += 0;
    			}
        	}
        }

        if (totalScore != 0) {
	        averageGrade = totalGrade/totalScore;
	        GPA = totalGPA/totalScore;
        }
	}

	public Calculate(String[] classInfo, int length) {

		classes = Arrays.copyOf(classInfo, length);
		this.length = length;
		Calc();
	}

	public String getSummary() {

		return summary;
	}

	public float getAverageGrade() {

		return averageGrade;
	}

	public float getGPA() {

		return GPA;
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
}
