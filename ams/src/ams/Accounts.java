package ams;

public enum Accounts {
	ALL("전체 계좌"), ACCOUNT("입출금 계좌"), MINUSACCOUNT("마이너스 계좌");

	String accountType;

	Accounts(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}
}
