package product;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shiyajian
 * create: 2019-02-18
 */
public class Storage {

    /**
     * 仓库最大数量
     */
    public static final int MAX_NUM = 100;

    public static final LinkedList<Object> CONTAINER = new LinkedList<>();

    public static final Lock LOCK = new ReentrantLock();

    public static final Condition FULL = LOCK.newCondition();

    public static final Condition EMPTY = LOCK.newCondition();

    public void produce(int num) {
        LOCK.lock();

        while (CONTAINER.size() + num > MAX_NUM) {
            try {
                FULL.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < num; i++) {
                CONTAINER.add(new Object());
            }

            FULL.signalAll();
            EMPTY.signalAll();

            // 释放锁
            LOCK.unlock();
        }
    }

    public void consumer(int num) {
        LOCK.lock();

        while (CONTAINER.size() - num < 0) {
            System.out.println("不够消费了");
            try {
                EMPTY.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < num; i++) {
            CONTAINER.remove();
        }
        FULL.signalAll();
        EMPTY.signalAll();

        // 释放锁
        LOCK.unlock();
    }


}
