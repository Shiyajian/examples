package product;

/**
 * @author shiyajian
 * create: 2019-02-18
 */
public class Read implements Runnable{

    private ReadWriteDemo readWriteDemo;

    public Read(ReadWriteDemo readWriteDemo) {
        this.readWriteDemo = readWriteDemo;
    }

    @Override
    public void run() {
        readWriteDemo.read();
    }
}
