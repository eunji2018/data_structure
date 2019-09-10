/**
 * @Author eunji
 */
package basic_algorithm.string;

//通配符匹配
//string不包含'.'、'*'两种字符，expression可以包含'.'、'*'两种字符，且'*'不能是首字符，任意两个'*'不能相邻
//'.'代表任何一个字符，'*'表示前一个字符可以有0个或多个，判断string能否被expression匹配
public class WildcardMatch {

    //先校验string、expression是否有效
    public static boolean generate(char [] string, char [] expression) {
        if (string == null || expression == null)
            return false;
        if (!validate(string,expression))
            return false;

        return false;
    }

    //递归法
    //若string[i - length-1]与expression[j - length-1]匹配，则返回true，否则返回false
    //每次递归调用可以保证expression当前位置的字符不为'*'
    private static boolean process1(char [] string, char [] expression, int i, int j) {
        if (j == expression.length)
            return i == string.length;
        if (j == expression.length - 1) {

        }
        if (expression[j+1] != '*') {//expression当前位置的下一个字符不为'*'，则当前位置也不为'*'

        }
        return false;
    }

    //动态规划法
    private static boolean process2() {
        return false;
    }

    //校验字符串
    private static boolean validate(char [] string, char [] expression) {
        for (int i = 0; i < string.length; i++) {
            if (string[i] == '.' || string[i] == '*')
                return false;
        }
        if (expression[0] == '*')
            return false;
        for (int i = 1; i < expression.length; i++) {
            if (expression[i] == '*' && expression[i-1] == '*')
                return false;
        }
        return true;
    }
}
