package model;

import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "item")
public class Item {
	private long id;
	private String name, description;
	private URI thumbnailUri, imageUri, placeBidUri;
	private Date startDate, endDate;
	private Money highestBid;
	private Owner highestBidOwner, owner;
	private ItemStatus status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public URI getThumbnailUri() {
		return thumbnailUri;
	}

	public void setThumbnailUri(URI thumbnailUri) {
		this.thumbnailUri = thumbnailUri;
	}

	public URI getImageUri() {
		return imageUri;
	}

	public void setImageUri(URI imageUri) {
		this.imageUri = imageUri;
	}

	public URI getPlaceBidUri() {
		return placeBidUri;
	}

	public void setPlaceBidUri(URI placeBidUri) {
		this.placeBidUri = placeBidUri;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Money getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(Money highestBid) {
		this.highestBid = highestBid;
	}

	public Owner getHighestBidOwner() {
		return highestBidOwner;
	}

	public void setHighestBidOwner(Owner highestBidOwner) {
		this.highestBidOwner = highestBidOwner;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Item(){
		id = 0;
		name = "No Name";
		description = "No Description Available";
		status = ItemStatus.WAIT;
	}

	public Item(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public Item(int id, String name, String description,
			Date startDate, Date endDate, Money highestBid,
			Owner highestBidOwner, Owner owner, ItemStatus status){
		this(id, name);
		
		this.description = description;
		this.owner = owner;
		this.startDate = startDate;
		this.endDate = endDate;
		this.highestBid = highestBid;
		this.highestBidOwner = highestBidOwner;
		this.status = status;
	}
}
