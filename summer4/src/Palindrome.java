import java.util.Scanner;
//•	判断是否为回文——判断用户输入的字符串是否为回文。回文是指正反拼写形式都是一样的词，譬如“racecar”。
public class Palindrome {
    public static void main(String[] args) {
        System.out.print("输入一个字符串:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        line = line.toLowerCase();
        int j = 0;
        for (int i=0;i<line.length()/2;i++){
            if (String.valueOf(line.charAt(i)).equals(String.valueOf(line.charAt(line.length()-i-1)))){
                j++;
                continue;
            }else {
                System.out.println("这个字符串不是回文");
                break;
            }
        }
        if(j==line.length()/2)
        System.out.println("这个字符串是回文");
    }
}
