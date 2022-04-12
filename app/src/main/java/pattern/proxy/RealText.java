package pattern.proxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class RealText implements Text {
    private String fileName;
    private String fileContent;
    private boolean isPending = true;

    public RealText(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);

        // 찾는 파일이 없으면 새로운 스레드에게 재요청
        if (isPending == true) {
            System.out.println("Pending : " + fileName);
            new Thread(() -> {
                while (isPending) {
                    loadFromDisk(fileName);
                }
                displayText();
            }).start();
        }

    }

    public boolean getPending() {
        return isPending;
    }

    private void loadFromDisk(String fileName) {

        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            fileContent = br.readLine();
            br.close();
            isPending = false;
            System.out.println("Load Complete");
        } catch (Exception e) {
            System.out.print(".");
        }

        // 1초 쉬고 다시 찾기
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void displayText() {
        System.out.println(fileName + " Displaying : " + fileContent);
    }
}
