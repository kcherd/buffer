import java.io.IOException;
import java.io.InputStream;

public class MyBuffer {
    private InputStream inputStream;
    private ListOfBlocks blocks;

    public MyBuffer(InputStream inputStream){
        this.inputStream = inputStream;
        blocks = new ListOfBlocks(10);
    }

    private void writePrivate(){
        synchronized (blocks) {

            byte[] bytes = new byte[10];
            try {
                while (inputStream.read(bytes) != -1) {
                    while (!blocks.add(bytes)){
                        try {
                            blocks.wait();
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("add block");
                    blocks.notify();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(){
        Thread thread = new Thread(this::writePrivate);
        thread.start();
    }

    public byte[] read(){
        synchronized (blocks){
            while (blocks.isEmpty()) {
                try {
                    blocks.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            byte[] bytes = blocks.remove();
            System.out.println(blocks.getSize());
            blocks.notify();
            return bytes;
        }
    }
}