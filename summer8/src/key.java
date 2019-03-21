//•	CD-Key生成器——利用某种算法生成一个唯一的key。软件开发者可以用它来作为软件的激活器
import java.util.UUID;
public class key {
    public static String getGeneralKey(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static void main(String[] args) {

        for(int i=0; i<20; i++){
            System.out.println(key.getGeneralKey());
            System.out.println(key.getGeneralKey().length());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }
}
