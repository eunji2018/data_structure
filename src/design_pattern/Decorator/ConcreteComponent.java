package design_pattern.Decorator;

//具体组件
public class ConcreteComponent extends Component{

	@Override
	public void describe() {
		System.out.println(description);
	}
}
