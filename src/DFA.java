import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DFA
{

	private ArrayList<String> states;
	private ArrayList<String> alphabet;
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	private String start_state;
	private ArrayList<String> accept_states = new ArrayList<String>();
	private ArrayList<Label> labels = new ArrayList<Label>();
	private ArrayList<RegularDefinition> regular_definitions = new ArrayList<RegularDefinition>();

	public DFA(String dfa_input_file_name) throws IOException
	{
		construct_dfa_from_file(dfa_input_file_name);
	}

	private void construct_dfa_from_file(String dfa_input_file_name) throws IOException
	{
		FileReader fr = new FileReader(dfa_input_file_name);
		BufferedReader br = new BufferedReader(fr);
		String currentLine;
		while ((currentLine = br.readLine()) != null)
		{
			if (currentLine.equals("#set of states"))
			{
				String temp_states = br.readLine();
				states = new ArrayList<String>(Arrays.asList(temp_states.split(",")));
			} else if (currentLine.equals("#alphabet"))
			{
				String temp_alpha = br.readLine();
				alphabet = new ArrayList<String>(Arrays.asList(temp_alpha.split(",")));
			} else if (currentLine.equals("#transitions"))
			{
				while (!currentLine.equals("#start state"))
				{
					currentLine = br.readLine();
					String[] temp_transition = currentLine.split(",");
					if (temp_transition.length == 3)
					{
						transitions.add(new Transition(temp_transition[0], temp_transition[1], temp_transition[2]));
					}

				}
			} else if (currentLine.equals("#set of accept states"))
			{
				String temp_accept_states = br.readLine();
				accept_states = new ArrayList<String>(Arrays.asList(temp_accept_states.split(",")));
			} else if (currentLine.equals("#label"))
			{
				while (!currentLine.equals("#regulardefinition"))
				{
					currentLine = br.readLine();
					String[] temp_label = currentLine.split(",");
					if (temp_label.length == 2)
					{
						labels.add(new Label(temp_label[0], temp_label[1]));
					}

				}
			}

			if (currentLine.equals("#start state"))
			{
				start_state = br.readLine();
			} else if (currentLine.equals("#regulardefinition"))
			{
				while (currentLine != null)
				{
					currentLine = br.readLine();
					if (currentLine != null)
					{
						String[] temp_definition = currentLine.split(",");
						regular_definitions.add(new RegularDefinition(temp_definition[0], temp_definition[1]));
					}

				}
			}

		}
		br.close();
	}

	public ArrayList<OutputFormat> convert_from_label_to_regularDefinition(ArrayList<OutputFormat> input)
	{
		ArrayList<OutputFormat> result = new ArrayList<OutputFormat>();
		for (int i = 0; i < input.size(); i++)
		{
			boolean has_regular_def = false;
			for (int j = 0; j < labels.size(); j++)
			{
				if (input.get(i).getCategory().equals(labels.get(j).getState()))
				{

					for (int k = 0; k < regular_definitions.size(); k++)
					{
						if (labels.get(j).getOperator().equals(regular_definitions.get(k).getOperator()))
						{
							result.add(new OutputFormat(input.get(i).getSubString(),
									regular_definitions.get(k).getState()));
							has_regular_def = true;
						}
					}

				}
			}
			if (!has_regular_def)
			{
				result.add(input.get(i));
			}
		}
		// TODO
		return result;
	}

	public ArrayList<String> getStates()
	{
		return states;
	}

	public void setStates(ArrayList<String> states)
	{
		this.states = states;
	}

	public ArrayList<String> getAlphabet()
	{
		return alphabet;
	}

	public void setAlphabet(ArrayList<String> alphabet)
	{
		this.alphabet = alphabet;
	}

	public ArrayList<Transition> getTransitions()
	{
		return transitions;
	}

	public void setTransitions(ArrayList<Transition> transitions)
	{
		this.transitions = transitions;
	}

	public String getStart_state()
	{
		return start_state;
	}

	public void setStart_state(String start_state)
	{
		this.start_state = start_state;
	}

	public ArrayList<String> getAccept_states()
	{
		return accept_states;
	}

	public void setAccept_states(ArrayList<String> accept_states)
	{
		this.accept_states = accept_states;
	}

	public ArrayList<Label> getLabels()
	{
		return labels;
	}

	public void setLabels(ArrayList<Label> labels)
	{
		this.labels = labels;
	}

	public ArrayList<RegularDefinition> getRegular_definitions()
	{
		return regular_definitions;
	}

	public void setRegular_definitions(ArrayList<RegularDefinition> regular_definitions)
	{
		this.regular_definitions = regular_definitions;
	}

}
