//•	电影商店——管理录像带租借，记录借出时间、到期时间、逾期费用。复杂一点可以生成逾期用户的账号报告。
public class Test {
    public static void main(String[] args) {
        Customer customer = new Customer("ZhangSan");

        customer.addRental(new Rental(new Movie("aaa",Movie.REGULAR),1));
        customer.addRental(new Rental(new Movie("bbb",Movie.NEW_RELEASE),1));
        customer.addRental(new Rental(new Movie("ccc",Movie.CHILDRENS),1));

        System.out.println(customer.statement());
    }
}
