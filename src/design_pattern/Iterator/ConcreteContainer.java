package design_pattern.Iterator;

import java.util.ArrayList;
import java.util.List;

//具体容器
//使用ArrayList保存元素（或者使用LinkedList）
public class ConcreteContainer implements Container {

    private List<Object> list;

    public ConcreteContainer() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(Object object) {
        list.add(object);
    }
    
    @Override
    public void remove(Object object) {
        list.remove(object);
    }
    
    @Override
    public Object get(int index) {
        return list.get(index);
    }

    //获取关联此容器的迭代器
    @Override
    public Iterator iterator() {
        return new ConcreteIterator(this);
    }
    
    @Override
    public int size() {
        return list.size();
    }
}
