package cosmetic.utils.log;

public interface LogBuilder {
	
	public void addLogLine(String args[]);
	
	public void addExceptionLine(String message);
	
	public void finishLog();
	
	public void startLog();
	
}
