import java.util.Scanner;
/*•	拉丁猪文字游戏——这是一个英语语言游戏。基本规则是将一个英语单词的第一个辅音音素的字母移动到词尾并且加上后缀-ay（譬如“banana”会变成“anana-bay”）。*/
public class WordGame {
    public static void main(String[] args){
        System.out.print("请输入一个单词:");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        word = word.toLowerCase();
        StringBuffer stringBuffer = new StringBuffer(word);
        String consonant = "eioua";
        int result = 0;
        String temp = " ";
        for(int i=0;i<word.length();i++){
            result = consonant.indexOf(String.valueOf(word.charAt(i)));
            if(result == -1){
                temp = String.valueOf(word.charAt(i));
                stringBuffer.deleteCharAt(i);
                break;
            }
            continue;
        }
        System.out.println(stringBuffer+"-"+temp+"ay");
    }
}
