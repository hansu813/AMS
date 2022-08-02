package ams;
/**
 * 마이너스 계좌 class
 * @author 김한수
 *
 */
public class MinusAccount extends Account {
	private long borrowMoney;
	private String accountType = "마이너스 계좌";
	public MinusAccount() {}
	public MinusAccount(String accountNum, String accountOwner, int passwd) {
		super(accountNum, accountOwner, passwd);
		this.borrowMoney = 0;
	}
	
//	Setter and Getter
	public long getBorrowMoney() {
		return borrowMoney;
	}
	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}
	
//	Method Overriding
	@Override
	public long getRestMoney() {
		return super.getRestMoney() - getBorrowMoney();
	}
	@Override
	public String toString() {
		String sf = String.format("%-10s\t%-12s\t%-6s\t%-,8d\t%-,8d", getAccountType(), 
				getAccountNum(), getAccountOwner(), getRestMoney(), getBorrowMoney());
		
		return sf;
	}
	
	/**
	 * 대출받기 기능
	 * @param money
	 * @return
	 * @throws InvelidException
	 */
	public long loan(long money) throws InvelidException {
		if(getRestMoney() + getBorrowMoney() > 50000000) {
			throw new InvelidException("잔액이 50,000,000원을 넘길 수 없습니다.");
		}
		return getRestMoney() - money;
	}
}
