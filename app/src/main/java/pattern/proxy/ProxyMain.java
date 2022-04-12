package pattern.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        Text text1 = new ProxyText("data2.txt");
        text1.displayText();
    }
}
