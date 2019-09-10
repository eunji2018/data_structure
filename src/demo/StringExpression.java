/**
 * @Author eunji
 */
package demo;

import data_structure.Queue;
import data_structure.Stack;

//字符串表达式求值
//（1）中缀表达式计算：
//申请两个栈，分别为操作数栈，运算符栈
//从左到右遍历，若扫描到数字，则压入操作数栈中
//若扫描到运算符，运算符栈为空，直接压入，否则将运算符栈中比当前运算符的优先级高的出栈，再将当前运算符压入栈中
//每次从运算符栈中出栈时，若出栈元素为四则运算符，则从操作数栈中出栈两个元素，计算结果，将结果压入操作数栈中
//扫描完毕，将运算符栈中所有元素出栈，直到栈为空，此时操作数栈顶元素即为最终结果
//（2）中缀表达式转化为后缀表达式：
//申请一个运算符栈
//从左到右遍历，若扫描到数字，直接输出
//若扫描到运算符，运算符栈为空，直接压入，否则将运算符栈中比当前运算符的优先级高的输出，再将当前运算符压入栈中
//扫描完毕，将运算符栈中所有元素输出
//（3）后缀表达式计算：
//申请一个操作数栈
//从左到右遍历，若扫描到数字，则压入操作数栈中
//若扫描到运算符，则从操作数栈中出栈两个元素，计算结果，将结果压入操作数栈中
//扫描完毕，操作数栈顶元素即为最终结果
public class StringExpression {

    public static void main(String[] args) {
        //只包含非负整数，四则运算符
        String expression = "10+0+3-20*2/5-5+0+10-18/3*2";
        System.out.println("infix: " + infixEvaluate1(expression));//-2
        Queue result = infixToSuffix(expression);
        //10 0 + 3 + 20 2 * 5 / - 5 - 0 + 10 + 18 3 / 2 * -
        result.print();
        System.out.println("suffix: " + suffixEvaluate(result));//-2

        System.out.println("-----");
        //包含整数，四则运算符，左右括号
        expression = "-2+10+0-4-0*5-(-10-2*(-16)/4)+(-100)/10*(-3)";
        System.out.println("infix: " + infixEvaluate2(expression));//36
        result = infixToSuffix(expression);
        //-2 10 + 0 + 4 - 0 5 * - -10 2 -16 * 4 / - - -100 10 / -3 * +
        result.print();
        System.out.println("suffix: " + suffixEvaluate(result));//36

    }

    //中缀表达式计算
    //表达式只包含非负整数，四则运算符：0...9 + - * /
    private static int infixEvaluate1(String expression) {
        if (expression == null)
            return Integer.MIN_VALUE;
        int index = 0, value = 0;
        char current;
        boolean flag = false;
        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();
        while (index < expression.length()) {
            current = expression.charAt(index);
            if (current >= '0' && current <= '9') {//数字字符串转换为整型数据
                value = value * 10 + current - '0';
                index++;
                flag = true;
                continue;
            }
            if (flag) {//数字字符串转换完毕
                number.push(value);
                flag = false;
            }
            //四则运算符
            while (!operator.isEmpty() && !compare(current, operator.peek())) {
                value = evaluate(number.pop(), number.pop(), operator.pop());
                number.push(value);
            }
            value = 0;
            operator.push(current);
            index++;
        }
        //扫描完毕
        number.push(value);//末尾的整数
        while (!operator.isEmpty()) {
            value = evaluate(number.pop(), number.pop(), operator.pop());
            number.push(value);
        }
        return number.pop();
    }

    //中缀表达式计算
    //表达式包含整数，四则运算符，左右括号：0...9 + - * / ( )
    //负数需要使用括号括起来，但是在表达式开头或括号内开头的情况除外
    private static int infixEvaluate2(String expression) {
        if (expression == null)
            return Integer.MIN_VALUE;
        int index = 0, value = 0;
        char current, temp;
        boolean flag = false, positive = true;
        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();
        while (index < expression.length()) {
            current = expression.charAt(index);
            if (current >= '0' && current <= '9') {//数字字符串转换为整型数据
                value = value * 10 + (positive ? current - '0' : '0' - current);
                index++;
                flag = true;
                continue;
            }
            if (flag) {//数字字符串转换完毕
                number.push(value);
                flag = false;
                positive = true;
            }
            //运算符
            switch (current) {
                case ')' ://从运算符栈中出栈，直到'('出栈
                    temp = operator.pop();
                    while (temp != '(') {
                        value = evaluate(number.pop(), number.pop(), temp);
                        number.push(value);
                        temp = operator.pop();
                    }
                    break;
                case '-' :
                    if (index == 0 || expression.charAt(index-1) == '(') {//扫描到负数
                        positive = false;
                        break;
                    }
                default :
                    while (!operator.isEmpty() && !compare(current, operator.peek())) {
                        value = evaluate(number.pop(), number.pop(), operator.pop());
                        number.push(value);
                    }
                    operator.push(current);
            }
            value = 0;
            index++;
        }
        //扫描完毕
        if (flag)//末尾是整数
            number.push(value);
        while (!operator.isEmpty()) {
            value = evaluate(number.pop(), number.pop(), operator.pop());
            number.push(value);
        }
        return number.pop();
    }

    //中缀表达式转化为后缀表达式
    //中缀表达式包含整数，四则运算符，左右括号：0...9 + - * / ( )
    //负数需要使用括号括起来，但是在表达式开头或括号内开头的情况除外
    private static Queue infixToSuffix(String expression) {
        if (expression == null)
            return null;
        int index = 0, value = 0;
        char current, temp;
        boolean flag = false, positive = true;
        Stack<Character> operator = new Stack<>();
        Queue<Object> queue = new Queue<>();
        while (index < expression.length()) {
            current = expression.charAt(index);
            if (current >= '0' && current <= '9') {
                value = value * 10 + (positive ? current - '0' : '0' - current);
                flag = true;
                index++;
                continue;
            }
            if (flag) {
                queue.enter(value);
                flag = false;
                positive = true;
                value = 0;
            }
            //运算符
            switch (current) {
                case ')' :
                    temp = operator.pop();
                    while (temp != '(') {
                        queue.enter(temp);
                        temp = operator.pop();
                    }
                    break;
                case '-' :
                    if (index == 0 || expression.charAt(index-1) == '(') {
                        positive = false;
                        break;
                    }
                default :
                    while (!operator.isEmpty() && !compare(current, operator.peek()))
                        queue.enter(operator.pop());
                    operator.push(current);
            }
            index++;
        }
        //扫描完毕
        if (flag)//末尾的整数
            queue.enter(value);
        while (!operator.isEmpty())
            queue.enter(operator.pop());
        return queue;
    }

    //后缀表达式计算
    private static int suffixEvaluate(Queue expression) {
        if (expression == null)
            return Integer.MIN_VALUE;
        int value;
        Object current;
        Stack<Integer> number = new Stack<>();
        while (!expression.isEmpty()) {
            current = expression.depart();
            if (current instanceof Integer) {
                number.push((int) current);
            }else {
                value = evaluate(number.pop(), number.pop(), (char) current);
                number.push(value);
            }
        }
        return number.pop();
    }

    //四则运算：[first][operator][second]
    //first先入栈后出栈，second后入栈先出栈
    private static int evaluate(int second, int first, char operator) {
        int result = 0;
        switch (operator) {
            case '+' : result = first + second; break;
            case '-' : result = first - second; break;
            case '*' : result = first * second; break;
            case '/' : result = first / second;
        }
        return result;
    }

    /* 运算符优先级比较
     * 若当前运算符current的优先级高于栈顶运算符top，则返回true
     * 最左一列为current，最上一行为top（'-'表示这种情况不会出现）
     *   + - * / ( )
     * + f f f f t -
     * - f f f f t -
     * * t t f f t -
     * / t t f f t -
     * ( t t t t t -
     * ) f f f f f -
     */
    private static boolean compare(char current, char top) {
        boolean result = false;
        switch (current) {
            case '+' :
            case '-' : if (top == '(') result = true; break;
            case '*' :
            case '/' : if (top == '+' || top == '-' || top == '(') result = true; break;
            default : if (current == '(') result = true;
        }
        return result;
    }
}
