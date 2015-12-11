package ua.nure.infostroy.kovaljov.command;

public class HelpCommand implements Command {

	@Override
	public void execute(String path) {
		printHelp();
	}
	private static void printHelp() {
		System.out.println("1. -i (--input) - path to the input file "
				+ "(e.g. C:\\Program Files\\Java\\input.txt). Type: String, Required: true");
		System.out.println("2. -t (--task) – task to execute. "
				+ "Type: Enum, Required: true, Permitted values: frequency, " + "length, duplicates");
	}
}
