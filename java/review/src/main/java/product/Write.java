package product;

/**
 * @author shiyajian
 * create: 2019-02-18
 */
public class Write implements Runnable{

    private ReadWriteDemo readWriteDemo;

    public Write(ReadWriteDemo readWriteDemo) {
        this.readWriteDemo = readWriteDemo;
    }

    @Override
    public void run() {
        readWriteDemo.write();
    }
}
