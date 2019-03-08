package product;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者
 * @author shiyajian
 * create: 2019-02-18
 */
public class Consumer implements Runnable {

    private Pool<String> pool;

    public Consumer(Pool<String> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        String item = pool.get();
        System.out.println("取出了" + item);
    }
}
