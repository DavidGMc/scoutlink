package uk.org.mattford.scoutlink.model;

public class Message {

	public String sender;
	public String text;
	
	public Message(String sender, String text) {
		this.sender = sender;
		this.text = text;
	}
}