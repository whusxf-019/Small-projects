
public class Student {
    private String stuId;
    private String name;
    private String gender;
    private double javaScore;
    private double sqlScore;
    private double cScore;
    private double htmlScore;
    private double homeworkScore;
    private double avgScore;
    private double sumScore;

    public Student(){};

    public Student(String stuId, String name, String gender,double javaScore,double sqlScore,
                   double cScore, double htmlScore)
    {
        this.stuId = stuId;
        this.name = name;
        this.gender = gender;
        this.javaScore = javaScore;
        this.sqlScore = sqlScore;
        this.cScore = cScore;
        this.htmlScore = htmlScore;
        this.homeworkScore = homeworkScore;
    }

    public double getSumScore(double javaScore,double sqlScore,
                              double cScore, double htmlScore)
    {
        return (javaScore+sqlScore+cScore+htmlScore) / 4;
    }

    public void level(double javaScore,double sqlScore,
                      double cScore, double htmlScore, double homeworkScore){
        if(((javaScore+sqlScore+cScore+htmlScore) / 4 * 0.7 + homeworkScore * 0.3)>=90)
            System.out.println("等级:A");
        else if(((javaScore+sqlScore+cScore+htmlScore) / 4 * 0.7 + homeworkScore * 0.3)>=80 && ((javaScore+sqlScore+cScore+htmlScore) / 4 * 0.7 + homeworkScore * 0.3)<90)
            System.out.println("等级:B");
        else
            System.out.println("等级:C");
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }

    public double getSqlScore() {
        return sqlScore;
    }

    public void setSqlScore(double sqlScore) {
        this.sqlScore = sqlScore;
    }

    public double getcScore() {
        return cScore;
    }

    public void setcScore(double cScore) {
        this.cScore = cScore;
    }

    public double getHtmlScore() {
        return htmlScore;
    }

    public void setHtmlScore(double htmlScore) {
        this.htmlScore = htmlScore;
    }

    public void setHomeworkScore(double homeworkScore){
        this.homeworkScore = homeworkScore;
    }

    public double getHomeworkScore(){
        return homeworkScore;
    }

    public double getAvgScore() {
        return avgScore;
    }

}
