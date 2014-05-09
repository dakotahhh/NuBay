package model;

import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "owner")
public class Owner {
	private int id;
	private URI detailsUri;
	
	public Owner(){
		id = 0;
	}
	
	public Owner(int id, URI detailsUri){
		this.id = id;
		this.detailsUri = detailsUri;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public URI getDetailsUri() {
		return detailsUri;
	}
	public void setDetailsUri(URI detailsUri) {
		this.detailsUri = detailsUri;
	}
}
