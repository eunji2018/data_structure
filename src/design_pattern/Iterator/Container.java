package design_pattern.Iterator;

//抽象容器
public interface Container {

    //添加元素
	void add(Object object);

	//删除元素
	void remove(Object object);

	//获取指定位置的元素
	Object get(int index);

	Iterator iterator();
	
	int size();
	
}
