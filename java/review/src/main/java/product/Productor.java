package product;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author shiyajian
 * create: 2019-02-18
 */
public class Productor implements Runnable{

    private Pool<String> pool;

    public Productor(Pool<String> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        String item = new Random().nextInt(10) + ":px";
        pool.put(item);
    }
}
