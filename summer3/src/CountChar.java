import java.util.Scanner;
//•	统计元音字母——输入一个字符串，统计处其中元音字母的数量。更复杂点的话统计出每个元音字母的数量。
public class CountChar {
    public static void main(String[] args){
        System.out.print("输入一个字符串:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        line = line.toLowerCase();
        String conword = "eioua";
        int a = 0;
        int e = 0;
        int i = 0;
        int o = 0;
        int u = 0;
        int count = 0;
        int result = 0;
        for(int j=0;j<line.length();j++){
            String temp = String.valueOf(line.charAt(j));
            result = conword.indexOf(String.valueOf(line.charAt(j)));
            if(result >= 0) count++;
            if(temp.equals("a")) a++;
            if(temp.equals("e")) e++;
            if(temp.equals("i")) i++;
            if(temp.equals("o")) o++;
            if(temp.equals("u")) u++;
        }
        System.out.println("元音数:"+count);
        System.out.println("a:"+a+" "+"e:"+e+" "+"i:"+i+" "+"o:"+o+" "+"u:"+u);

    }
}
