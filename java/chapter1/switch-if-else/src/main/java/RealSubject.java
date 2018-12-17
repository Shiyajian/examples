/**
 * @author shiyajian
 * create: 2018-12-12
 */
public class RealSubject implements  Subject {
    @Override
    public void rent() {
        System.out.println("realsubject rent");
    }

    @Override
    public void hello(String str) {
        System.out.println("realsubject hello" + str);
    }
}
