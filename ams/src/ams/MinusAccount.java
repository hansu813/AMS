package ams ;

/**
 * 마이너스 계좌
 * @author 김한수
 *
 */
public class MinusAccount extends Account {

	public String accountType = Accounts.MINUSACCOUNT.getAccountType();
	
	private long borrowMoney;
	
	public MinusAccount() {}
	public MinusAccount(String accountNum, String accountOwner, int passwd) {
		super(accountNum, accountOwner, passwd);
		this.borrowMoney = 0;
	}
	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}
	
	public long getBorrowMoney() {
		return borrowMoney;
	}
	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}
	
	
	@Override
	public String getAccountType() {
		return accountType;
	}
	@Override
	public long getRestMoney() {
		return super.getRestMoney() - getBorrowMoney();
	}
	@Override
	public String toString() {
		String text = super.toString();
		return text + String.format("\t%-,8d", getBorrowMoney());
	}
	@Override
	public long withdraw(long money) throws InvalidException {
		return getRestMoney() - money;
	}
	
	public long loan (long money) throws InvalidException {
		if(getRestMoney() + getBorrowMoney() > 50000000) {
			throw new InvalidException("잔액이 50,000,000원을 넘길 수 없습니다.");
		}
		this.borrowMoney = money;
		return getRestMoney() - money;
	}
}
