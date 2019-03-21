//•	统计字符串中的单词数目——统计字符串中单词的数目，更复杂的话从一个文本中读出字符串并生成单词数目统计结果。
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
public class count {
    public static void main(String[] args){
        System.out.print("请输入一个字符串:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        line = line.toLowerCase();
        String[] array = {".", "?", "!", " "};
        for (int i = 0; i < array.length; i++) {
            line = line.replace(array[i], ",");
        }
        String[] lineArray = line.split(",");
        Map<String,Integer> map = new HashMap<String, Integer>();
        for(int i=0;i<lineArray.length;i++){
            String key = lineArray[i];
            if(!"".equals(key)){
                Integer num = map.get(key);
                if(num == null || num == 0){
                    map.put(key,1);
                }else if(num >0){
                    map.put(key,num+1);
                }
            }
        }
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            Integer num = map.get(key);
            System.out.println(key + "\n\t\t" + num + "次\n----------");
        }
    }
}
