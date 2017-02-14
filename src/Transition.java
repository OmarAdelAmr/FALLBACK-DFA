
public class Transition
{
	private String start;
	private String operator;
	private String end;

	public Transition(String start, String operator, String end)
	{
		this.start = start;
		this.operator = operator;
		this.end = end;
	}

	public String toString()
	{
		return start + "," + operator + "," + end;
	}

	public String getStart()
	{
		return start;
	}

	public void setStart(String start)
	{
		this.start = start;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getEnd()
	{
		return end;
	}

	public void setEnd(String end)
	{
		this.end = end;
	}

}
