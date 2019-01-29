/**
 * @author shiyajian
 * create: 2018-12-05
 */
public class IfTest implements Testable {
    @Override
    public void testBox(Integer target) {
        if(target == 1){
            System.out.println("hello");
            return;
        }

        if(target == 2){
            System.out.println("world");
            return;
        }

        System.out.println("nihao");
    }

    @Override
    public void testUnbox(int target) {
        if(target == 1){
            System.out.println("hello");
            return;
        }

        if(target == 2){
            System.out.println("world");
            return;
        }

        System.out.println("shijie");
    }

    @Override
    public void testString(String target) {
        if(target.equals("hello")){
            System.out.println(1);
            return;
        }

        if(target.equals("world")){
            System.out.println(2);
            return;
        }

        System.out.println(0);
    }

    @Override
    public void testEnum(WeekEnum weekEnum) {

        if(WeekEnum.MONDAY == weekEnum){
            System.out.println(1);
            return;
        }

        if(WeekEnum.TUESDAY == weekEnum){
            System.out.println(2);
            return;
        }

        System.out.println(0);
    }
}
