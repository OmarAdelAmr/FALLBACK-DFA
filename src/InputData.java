import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputData
{
	private ArrayList<String> inputStrings;

	public InputData(String input_data_file_name) throws IOException
	{
		inputStrings = new ArrayList<String>();
		FileReader fr = new FileReader(input_data_file_name);
		BufferedReader br = new BufferedReader(fr);
		String currentLine;
		while ((currentLine = br.readLine()) != null)
		{
			if (currentLine.contains("input"))
			{
				inputStrings.add(br.readLine());
			}
		}
		br.close();
	}

	public ArrayList<String> getInputStrings()
	{
		return inputStrings;
	}

	public void setInputStrings(ArrayList<String> inputStrings)
	{
		this.inputStrings = inputStrings;
	}

}
