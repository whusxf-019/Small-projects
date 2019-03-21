
public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDRENS = 2;

    private String _title; //名称
    private int _priceCode; //价格(代号)

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int get_priceCode() {
        return _priceCode;
    }

    public void set_priceCode(int code) {
        _priceCode = code;
    }

    public String get_title() {
        return _title;
    }
}
