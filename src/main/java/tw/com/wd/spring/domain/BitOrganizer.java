package tw.com.wd.spring.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BitOrganizer")
public class BitOrganizer {	
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEQ")
    private Long seq;
	
    @Column(name = "recordDate")
	public Date recordDate;
    
    @Column(name = "itemName")
	public String itemName;
    
    @Column(name = "price")
	public int price;
    
    public BitOrganizer() {
    	super();
    }
	
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Date getRecordDate() {
		return recordDate;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder strBuilder = new StringBuilder();
		strBuilder
			.append("BitOrganizer :")
			.append("[RecordDate:").append(sdf.format(getRecordDate())).append("]")
			.append("[ItemName:").append(getItemName()).append("]")
			.append("[Price:").append(getPrice()).append("]");
		return strBuilder.toString();
	}
}
