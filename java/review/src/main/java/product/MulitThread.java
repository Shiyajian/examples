package product;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shiyajian
 * create: 2019-02-18
 */
public class MulitThread {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try {
            lock.tryLock(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
