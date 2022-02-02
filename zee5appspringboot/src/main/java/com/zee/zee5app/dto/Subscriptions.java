package com.zee.zee5app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zee.zee5app.exception.InvalidAmountException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "subs")
public class Subscriptions {
	
	@Id
	@Column(name = "subId")
	private String id;
	
	@NotBlank
	private String dateOfPurchase;
	
	@NotBlank
	private String expiryDate;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String type;
	
	@NotBlank
	private String paymentMode;
	
	@NotNull
	private boolean autoRenewal;
	
	@Setter(value = AccessLevel.NONE)
	@NotNull
	private int amount;
	
	@NotBlank
	private String regId;
	
	public void setAmount(int amount) throws InvalidAmountException {
		if(amount < 100) {
			throw new InvalidAmountException("Amount Insufficient");
		}
		this.amount = amount;
	}
	
}
