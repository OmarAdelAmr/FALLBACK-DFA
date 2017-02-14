import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class mainApp
{
	private static DFA dfa;
	private static InputData inputDate;
	private static ArrayList<OutputFormat> output_fallback;
	private static ArrayList<String> states_stack;
	private static ArrayList<String> temp = new ArrayList<String>();
	private static int start_index = 0;
	private static int end_index = 0;
	private static String output_file_content = "";

	private static ArrayList<OutputFormat> solution(String input_string)
	{
		output_fallback = new ArrayList<OutputFormat>();
		states_stack = new ArrayList<String>();

		start_index = 0;
		end_index = 0;
		states_stack.add(dfa.getStart_state());
		boolean has_failed = false;

		A: while (start_index < input_string.length() && !has_failed)
		{
			if (end_index == input_string.length())
			{
				start_index = end_index;
			}
			while (end_index <= input_string.length())
			{
				if (end_index == input_string.length())
				{
					if (dfa.getAccept_states().contains(states_stack.get(states_stack.size() - 1)))
					{
						output_fallback.add(new OutputFormat(input_string.substring(start_index, end_index),
								states_stack.get(states_stack.size() - 1)));
						break A;
					} else
					{
						go_back(input_string);
					}
				} else
				{
					String current_alpha = input_string.charAt(end_index) + "";
					if (dfa.getAlphabet().contains(current_alpha))
					{
						boolean has_transition = false;
						C: for (int i = 0; i < dfa.getTransitions().size(); i++)
						{
							if (dfa.getTransitions().get(i).getOperator().equals(current_alpha) && dfa.getTransitions()
									.get(i).getStart().equals(states_stack.get(states_stack.size() - 1)))
							{
								has_transition = true;
								states_stack.add(dfa.getTransitions().get(i).getEnd());
								end_index++;
								break C;

							}

						}
						if (!has_transition)
						{
							boolean has_accept_state = go_back(input_string);
							if (!has_accept_state)
							{
								System.out.println("No accept state in the stack");
								has_failed = true;
								go_back(input_string);
								break A;
							}
						} else
						{

						}
					} else
					{
						go_back(input_string);
						if (output_fallback.size() == 0)
						{
							output_fallback.add(new OutputFormat("Error: Input lexemes don't match the language!", ""));
						} else
						{
							output_fallback.add(new OutputFormat(", Error!", ""));
						}

						has_failed = true;

						break A;
					}
				}

			}

		}

		return dfa.convert_from_label_to_regularDefinition(output_fallback);

	}

	public static boolean go_back(String input_string)
	{
		boolean has_accept_state = false;
		D: for (int i = states_stack.size() - 1; i > 0; i--)
		{
			if (dfa.getAccept_states().contains(states_stack.get(i)))
			{
				has_accept_state = true;
				temp.add(input_string.substring(start_index, end_index));
				output_fallback
						.add(new OutputFormat(input_string.substring(start_index, end_index), states_stack.get(i)));
				start_index = end_index;
				states_stack.clear();
				states_stack.add(dfa.getStart_state());

				break D;
			} else
			{
				states_stack.remove(i);
				end_index--;
			}
		}
		return has_accept_state;
	}

	public static void create_output_file(ArrayList<OutputFormat> res, int output_index)
	{

		output_file_content += "output" + (output_index + 1) + ":\n";
		for (int i = 0; i < res.size(); i++)
		{
			output_file_content += res.get(i) + " ";
		}
		output_file_content += "\n";
	}

	public static void main(String[] args) throws IOException
	{
		dfa = new DFA("fallbackdfa.in");
		inputDate = new InputData("Lab2.in");

		for (int i = 0; i < inputDate.getInputStrings().size(); i++)
		{
			ArrayList<OutputFormat> res = solution(inputDate.getInputStrings().get(i));
			create_output_file(res, i);
		}

		String output_file = "Lab2.out";
		PrintWriter fw = new PrintWriter(new FileWriter(output_file));
		fw.print(output_file_content);
		fw.close();
		System.out.println(output_file_content);

	}

}
