package design_pattern.Decorator;

//抽象组件
public abstract class Component {

    //表示组件的状态
    protected String description;
    
    public abstract void describe();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
}
