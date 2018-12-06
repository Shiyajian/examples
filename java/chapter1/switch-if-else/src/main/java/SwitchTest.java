/**
 * @author shiyajian
 * create: 2018-12-05
 */
public class SwitchTest implements Testable {
    @Override
    public void testBox(Integer target) {
        switch (target) {
            case 1:
                System.out.println("hello");
                break;
            case 2:
                System.out.println("workd");
                break;
            default:
                break;
        }

    }

    @Override
    public void testUnbox(int target) {
        switch (target) {
            case 1:
                System.out.println("nihao");
                break;
            case 2:
                System.out.println("shijie");
                break;
            default:
                break;
        }
    }

    @Override
    public void testString(String target) {
        switch (target) {
            case "nihao":
                System.out.println(1);
                break;
            case "shijie":
                System.out.println(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void testEnum(WeekEnum weekEnum) {

        switch (weekEnum) {
            case SUNDAY :
                System.out.println(0);
                break;
            case MONDAY:
                System.out.println(1);
                break;
            default:
                break;
        }
    }
}
