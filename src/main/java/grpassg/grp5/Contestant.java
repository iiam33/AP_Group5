package grpassg.grp5;

public class Contestant{
    private static String name;
    private static String country;
    private static String pic1;
    private static String pic2;
    private static String pic3;
    private static String password;

    public Contestant(String n, String c, String p1, String p2, String p3, String pw) {
        name = n;
        country = c;
        pic1 = p1;
        pic2 = p2;
        pic3 = p3;
        password = pw;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String n) {
        name = n;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String c) {
        country = c;
    }

    public static String getPic1() {
        return pic1;
    }

    public static void setPic1(String p1) {
        pic1 = p1;
    }

    public static String getPic2() {
        return pic2;
    }

    public static void setPic2(String p2) {
        pic2 = p2;
    }

    public static String getPic3() {
        return pic3;
    }

    public static void setPic3(String p3) {
        pic3 = p3;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String pw) {
        password = pw;
    }
}
