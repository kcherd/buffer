import java.io.*;

public class Main {
    public static void main(String[] args) {
        InputStream stream = null;

        try {
            stream = new FileInputStream("C:\\Users\\Kris\\IdeaProjects\\buffInput\\src\\file.txt");
            MyBuffer buffer = new MyBuffer(stream);
            buffer.write();
            for (int i = 0; i < 100; i ++){
                System.out.println(buffer.read()[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}