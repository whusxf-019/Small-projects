import java.util.Scanner;
//•	逆转字符串——输入一个字符串，将其逆转并输出
public class reverse {
    public static void main(String[] args){
        System.out.print("请输入一个字符串:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println("逆转前:"+line);
        String result = " ";
        for(int i=line.length()-1;i>=0;i--)
            result = result + line.charAt(i);
        System.out.println("逆转后:"+result);
    }
}
