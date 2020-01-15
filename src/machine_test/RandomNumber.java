/**
 * @Author eunji
 */
package machine_test;

//随机数相关
public class RandomNumber {

/*****等可能随机数*****/

    //等可能N随机数
    //生成0 - N-1的随机数
    public static int generate1(int n) {
        return (int) (Math.random() * n);//random()方法返回浮点数 0 <= double < 1
    }

    //平方随机数
    //由等可能N随机数生成N^2随机数
    public static int generate2(int n) {
        return generate1(n) * n + generate1(n);//两次方法调用相互独立
    }

    //N-M随机数
    //由等可能N随机数生成等可能M随机数，m <= n^2
    public static int generate3(int n, int m) {
        int result  = 0;
        int temp = 0;
        if (m <= n) {//第一种情形，m <= n，直接截取前半段随机数
            temp = n;
            while (temp % m != 0)//找到M小于或等于N的最大倍数
                temp--;
            while (true) {
                result = generate1(n);
                if (result < temp)
                    break;
            }
        }else {//第二种情形，n < m <= n^2，先得到N^2随机数，再转换为第一种情形
            temp = n * n;
            while (temp % m != 0)//找到M小于或等于N^2的最大倍数
                temp--;
            while (true) {
                result = generate2(n);
                if (result < temp)
                    break;
            }
        }
        return result % m;//映射到目标范围;
    }

/*****不等可能随机数*****/

    //不等可能0-1随机数
    //以p概率生成0，以1 - p概率生成1
    public static int generate6(float p) {
        if (p < 0 || p > 1)
            return -1;
        return Math.random() < p ? 0 : 1;
    }

    //等可能0-1随机数
    //由不等可能0-1随机数生成等可能0-1随机数
    public static int generate7(float p) {
        if (p < 0 || p > 1)
            return -1;
        int result;
        while (true) {
            result = generate6(p);//生成第一个随机数
            if (result != generate6(p))//生成第二个随机数
                break;
        }
        return result;
    }


    //N随机数
    //由0-1随机数生成N随机数
    public static int generate8(int n) {

        return 0;
    }

}
