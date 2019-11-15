package uk.org.mattford.scoutlink.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Server {

	private LinkedHashMap<String, Conversation> conversations;
	private int status = 0;
    private ArrayList<String> channelList = new ArrayList<>();
	
	public final static int STATUS_DISCONNECTED = 0;
	public final static int STATUS_CONNECTED = 1;
	
	public Server() {
		this.conversations = new LinkedHashMap<>();
	}
	
	public Conversation getConversation(String name) {

		if (conversations.containsKey(name)) {
			return conversations.get(name);
		}
		return null;
	}

    public ArrayList<String> getChannelList() {
        return channelList;
    }
	
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public LinkedHashMap<String, Conversation> getConversations() {
		return this.conversations;
	}
	
	public void addConversation(Conversation conv) {
		conversations.put(conv.getName(), conv);
	}
	
	public void removeConversation(String name) {
		conversations.remove(name);
	}
	
	public void clearConversations() {
		conversations.clear();
	}

	public Conversation getActiveConversation() {
		for(Map.Entry<String,Conversation> conv : conversations.entrySet()) {
			if (conv.getValue().isSelected()) {
				return conv.getValue();
			}
		}
		return null;
	}
}
