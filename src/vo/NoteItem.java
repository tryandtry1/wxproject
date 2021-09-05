package vo;

import java.math.BigDecimal;
import java.sql.Date;

public class NoteItem {
	int id;
	String item_text;
	BigDecimal amount;
	Date date_paid;
	int category_id;
	int user_id;
	String category_text;
	public String getCategory_text() {
		return category_text;
	}
	public void setCategory_text(String category_text) {
		this.category_text = category_text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem_text() {
		return item_text;
	}
	public void setItem_text(String item_text) {
		this.item_text = item_text;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getDate_paid() {
		return date_paid;
	}
	public void setDate_paid(Date date_paid) {
		this.date_paid = date_paid;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
