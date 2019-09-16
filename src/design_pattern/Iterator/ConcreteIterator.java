package design_pattern.Iterator;

//具体迭代器
public class ConcreteIterator implements Iterator{

	private int index;

	//此迭代器关联的容器
	private Container container;
	
	public ConcreteIterator(Container container) {
	    this.index = 0;
		this.container = container;
	}
	
	@Override
	public boolean hasNext() {
		return index < container.size();
	}

	//外部类必须在调用hasNext()方法判断后再调用next()方法
	@Override
	public Object next() {
		Object object = container.get(index);
		index++;
		return object;
	}

	@Override
	public void reset() {
		index = 0;
		return;
	}
	
}
