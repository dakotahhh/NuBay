package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "money")
public class Money {
	private float amount;
	private MoneyType type;
	
	public Money(){
		type = MoneyType.USD;
	}
	
	public Money(float amount, MoneyType type){
		this.amount = amount;
		this.type = type;
	}
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public MoneyType getType() {
		return type;
	}
	public void setType(MoneyType type) {
		this.type = type;
	}
}
