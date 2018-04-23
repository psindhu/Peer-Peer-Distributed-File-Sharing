

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class FileContent implements Serializable{
	private static final long serialVersionUID = 1L;

	private List<String> senders = new ArrayList<String>();
	private List<String> recievers = new ArrayList<String>();
	private byte[] content;
	public List<String> getSenders() {
		return senders;
	}
	public void setSenders(List<String> senders) {
		this.senders = senders;
	}
	public List<String> getRecievers() {
		return recievers;
	}
	public void setRecievers(List<String> recievers) {
		this.recievers = recievers;
	}
	
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	@Override
	public String toString() {
		String s = "";
		for(String r:getRecievers()){
			s = s+"~~Receivers ==" + r +":"+"\n";
		}
		for(String d:getSenders()){
			s = s+"~~Senders ==" + d +":" + "\n";
		}
		return s;
	}
	

}
