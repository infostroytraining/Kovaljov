package ua.nure.infostroy.kovaljov.jcommander;

import com.beust.jcommander.Parameter;
import ua.nure.infostroy.kovaljov.analyzer.Task;
import ua.nure.infostroy.kovaljov.command.HelpCommand;

public class JCommanderProvider {
	
	@Parameter(names = {"-i","--input"}, description = "Path to the input", required = true)
    public String input;
	
	@Parameter(names = {"-t","--task"}, description = "Task to execute", required = true,converter = TaskConverter.class)
	public Task task;
	
	@Parameter(names = {"--help"},  required = false)
    public HelpCommand help;
	
	@Parameter(names = {"-p","--parallel"}, description = "executes the task in parallel", required = false, arity=0)
	public boolean parallel = false;
	
}
