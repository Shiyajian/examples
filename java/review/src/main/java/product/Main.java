package product;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author shiyajian
 * create: 2019-02-18
 */
public class Main {
    public static void main(String[] args) {
        Pool<String> pool = new Pool<>(new LinkedBlockingDeque<>());
        for (int i = 0; i < 20; i++) {
            new Thread(new Consumer(pool)).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(new Productor(pool)).start();
        }
    }
}
