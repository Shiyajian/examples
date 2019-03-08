package product;

import java.util.concurrent.BlockingQueue;

/**
 * 生产消费模型中的池子，提供存入方法和取出方法
 * @author shiyajian
 * create: 2019-02-18
 */
public class Pool<T> {

    private BlockingQueue<T> pool;

    public Pool(BlockingQueue<T> pool) {
        this.pool = pool;
    }

    public T get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public void put(T item) {
        try {
            pool.put(item);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void size(){
        System.out.println(pool.size());
    }
}
