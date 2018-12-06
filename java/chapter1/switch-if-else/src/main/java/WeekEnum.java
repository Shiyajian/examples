/**
 * @author shiyajian
 * create: 2018-12-05
 */
public enum WeekEnum {
    /** 星期日 */
    SUNDAY(0, "sunday"),

    /** 星期一 */
    MONDAY(1, "monday"),

    /** 星期二 */
    TUESDAY(2, "tuesday"),

    /** 星期三 */
    WEDNESDAY(3, "wednesday"),

    /** 星期四 */
    THURSDAY(4, "thursday"),

    /** 星期五 */
    FRIDAY(5, "friday"),

    /** 星期六 */
    SATURDAY(6, "saturday");

    private int value;

    private String name;

    WeekEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
