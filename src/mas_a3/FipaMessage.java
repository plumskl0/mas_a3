package mas_a3;

public class FipaMessage {

	// Performative enum
	public enum Performative {
		accept, agree, cancel, confirm, disconfirm, failure, inform
	}
	
	public String sender;
	public String receiver;
	public String content;
	public int conversationId;
	
}
