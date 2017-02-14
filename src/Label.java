
public class Label
{

	private String state;
	private String operator;

	public Label(String state, String operator)
	{
		this.state = state;
		this.operator = operator;
	}

	public String toString()
	{
		return state + "," + operator;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

}
