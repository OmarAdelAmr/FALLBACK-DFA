
public class OutputFormat
{
	private String subString;
	private String category;

	public OutputFormat(String subString, String category)
	{
		this.subString = subString;
		this.category = category;
	}

	public String toString()
	{
		if (category.equals(""))
		{
			return subString;
		} else
		{
			return category + "," + subString;
		}

	}

	public String getSubString()
	{
		return subString;
	}

	public void setSubString(String subString)
	{
		this.subString = subString;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

}
