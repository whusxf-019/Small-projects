//•	学生成绩管理器——记录一个班级的学生（创建一个Student类，记录他们的名字、平均分和考试分数）和他们的成绩等级。根据学生的测验和作业的分数计算出平均分和成绩等级。复杂一点可以将数据画在贝尔曲线上。
public class Test {
    public static void main(String[] args) {
        Student student=new Student("1", "sss", "男", 85, 74, 92, 88);
        double sumScore=student.getSumScore(student.getHtmlScore(),
                student.getJavaScore(), student.getSqlScore(), student.getcScore());
        System.out.println("学生"+student.getName()+"均分："+sumScore);
        student.level(85, 74, 92, 88, 100);
    }
}
