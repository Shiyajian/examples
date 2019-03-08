package product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shiyajian
 * create: 2019-02-18
 */
public class ReadWriteDemo {

    private Integer maxSize = 128;

    private ArrayList<String> list = new ArrayList<>();

    private Lock lock = new ReentrantLock();

    private Condition write = lock.newCondition();

    private Condition read = lock.newCondition();


    public void read() {
        try {
            lock.lock();
            while (list.size() <= 0) {
                read.await();
            }

            String last = list.get(0);
            System.out.println("读了:" + last);
            list.remove(last);
            write.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void write() {
        try {
            lock.lock();
            while (list.size() > maxSize) {
                write.await();
            }

            String item ="helloworld" + new Random().nextInt(100);
            list.add(item);
            System.out.println("写了"+item);
            read.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteDemo demo = new ReadWriteDemo();

        for (int i = 0; i < 200; i++) {
            new Thread(new Read(demo)).start();
            new Thread(new Write(demo)).start();
        }
    }
}
