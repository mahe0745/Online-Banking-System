package in.ineuron.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
	private int senderAccountNumber;
	private int receiverAccountNumber;
	private int amount;
	private LocalDateTime date;

	public Transaction(int senderAccountNumber, int receiverAccountNumber, int amount, LocalDateTime date) {
		super();
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.amount = amount;
		this.date = date;
	}

	public int getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(int senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public int getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(int receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
