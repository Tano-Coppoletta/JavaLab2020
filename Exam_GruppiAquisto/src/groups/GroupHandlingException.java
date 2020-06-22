package groups;

@SuppressWarnings("serial")
public class GroupHandlingException extends Exception {
    public GroupHandlingException() {
    }
	public GroupHandlingException(String reason) {
		super(reason);
	}
}
