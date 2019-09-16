package design_pattern.Iterator;

import java.util.Date;

//迭代器模式
//引入迭代器后可以将遍历与实现分离开来，具体容器的遍历不依赖于具体容器的实现
//具体容器改变保存元素的策略，不影响外部类遍历的逻辑
//具体迭代器遍历的方式：从前向后，从后向前，跳跃式遍历
public class Client {

	public static void main(String[] args) {
		Container container = new ConcreteContainer();
		container.add('a');
		container.add(new Date());
		container.add(1);
		container.add("string");
		container.add(true);

		//获取容器的迭代器，使用此迭代器遍历容器中的元素
        //先调用hasNext()方法判断，再调用next()方法取出元素
		Iterator iterator = container.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		
		System.out.println();
		container.remove(1);
		iterator.reset();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		
		return;
	}
}
