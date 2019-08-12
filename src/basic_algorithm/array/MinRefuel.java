/**
 * @Author eunji
 */
package basic_algorithm.array;

//最少加油次数
//汽车拥有初始油量，前往目的地，中间有加油站可以补给
//start表示初始油量，distance表示目的地的距离，stations表示加油站的距离与油量
//前进一个单位消耗一个单位的油量，油量不限，若到达加油站或者目的地时油量为0，则认为成功到达加油站或者目的地
public class MinRefuel {

    public static int generate(int start, int distance, int [][] stations) {
        if (stations == null || stations.length == 0 ||
            stations[0] == null || stations[0].length == 0)
            return start >= distance ? 0 : -1;
        int [] priority = new int[stations.length];
        int previous = 0, position = start;//前一个位置，当前位置
        int count = 0;//加油次数
        int length = 0;//当前可以补给的加油站个数
        while (position < distance) {
            //将最近走过的一段路程中的加油站添加到优先数组中
            for (int i = 0; i < stations.length; i++) {
                if (stations[i][0] > previous && stations[i][0] <= position) {
                    insert(priority, stations[i][1], length + 1);
                    length++;
                }
            }
            if (length == 0)//没有加油站可以补给，不能到达目的地
                return -1;
            //选择油量最多的加油站补给
            previous = position;
            position = position + delete(priority, length);
            count++;
            length--;
        }
        return count;
    }

    //向有序数组中添加元素
    public static void insert(int [] array, int data, int length) {
        int i = length - 1;
        while (i >= 1 && array[i-1] < data) {
            array[i] = array[i-1];
            i--;
        }
        array[i] = data;
        return;
    }

    public static int delete(int [] array, int length) {
        int temp = array[0];
        for (int i = 0; i < length - 1; i++)
            array[i] = array[i+1];
        return temp;
    }
}
