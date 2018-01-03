package mas_a3;

public class FipaMessage {

	// Performative enum
	public enum Performative {
		accept, agree, cancel, confirm, disconfirm, failure, inform, register
	}
	
	public Performative perfomative;
	
	public String sender;
	public String receiver;
	public Object content;
	public int conversationId;
	
}
