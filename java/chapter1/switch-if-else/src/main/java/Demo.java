import java.util.Arrays;

/**
 * @author shiyajian
 * create: 2018-12-12
 */
public class Demo {

    public static void main(String[] args) {

        int[] input = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        count(input);
    }

    public static void count(int[] input) {

        System.out.println("input start :" + Arrays.toString(input));

        int[] arrCount = new int[input.length];
        for (int i = 0; i < arrCount.length; i++) {
            arrCount[i] = 0;
        }

        for (int i = 0; i < arrCount.length; i++) {
            if(total(arrCount,i)){
                break;
            }
            if(i != arrCount.length -1 ){
                i = 0;
            }
        }
        System.out.println("output end :" + Arrays.toString(arrCount));
    }

    public static boolean total(int[] arrCount, int source) {
        boolean isNext = false;
        for (int i = 0; i < arrCount.length; i++) {
            int total = 0;
            for (int j = 0; j < arrCount.length; j++) {
                if (arrCount[j] == source) {
                    if (source == j && arrCount[source] == 0) continue;
                    total++;
                }
            }
            System.out.println(Arrays.toString(arrCount));
            System.out.printf("source: %s,val: %s ,total : %s \n", source, arrCount[source], total);
            if (total != arrCount[source]) {
                isNext = true;
                arrCount[source] = total;
                total(arrCount, arrCount[source]);
            }
        }
        return isNext;
    }


    public static void change(int[] arrCount, int index) {
        if (arrCount[index] == 0) {
            arrCount[arrCount[index]]++;
            change(arrCount, arrCount[arrCount[index]]);
        } else {

        }
    }

}
