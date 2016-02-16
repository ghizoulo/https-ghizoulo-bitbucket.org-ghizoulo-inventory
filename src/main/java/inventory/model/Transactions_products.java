package inventory.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTIONS_PRODUCTS")
public class Transactions_products {
	private int id;
	private int transaction_id;
	
	public Transactions_products() {
	}
	
	public Transactions_products(int id, int transaction_id){
		this.id = id;
		this.transaction_id = transaction_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	
}
