package design_pattern.Iterator;

//抽象迭代器
public interface Iterator {

    //判断是否存在下一个元素
	boolean hasNext();

	//获取下一个元素
	Object next();

	//重置
	void reset();
	
}
