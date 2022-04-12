package pattern.proxy;

public class ProxyText implements Text {
    private String fileName;
    private String fileContent = "proxy temp data....";
    private RealText realText;

    public ProxyText(String fileName) {
        this.fileName = fileName;
        realText = new RealText(fileName);
    }

    @Override
    public void displayText() {
        if (realText.getPending()) { // 기다리는 중
            System.out.println(fileName + " Displaying : " + fileContent);
        } else {
            realText.displayText();
        }
    }
}
