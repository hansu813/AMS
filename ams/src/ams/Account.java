package ams;

import java.util.Formatter;
/**
 * 입출금 계좌 클래스
 * @author 김한수
 *
 */
public class Account {
	public static String bankName = "Korea IT Bank";
	public static final long MAX_MONEY = 50000000;
	
	private String accountType = "입출금 통장";
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;
	private long money;
	
	StringBuffer sb = new StringBuffer();
	Formatter format = new Formatter(sb);
	
	public Account() {}
	public Account(String accountNum, String accountOwner, int passwd) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = 0;
	}
	
//	Getter and Setter
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public int getPasswd() {
		return passwd;
	}
	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}
	public long getRestMoney() {
		return restMoney;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public String getAccountType() {
		return accountType;
	}
	
//	Method Overriding
	@Override
	public String toString() {
		String sf = String.format("%-10s\t%-12s\t%-6s\t%-,8d", getAccountType(), 
				getAccountNum(), getAccountOwner(), getRestMoney());
		
		return sf;
	}
	/**
	 * 입금하기 Method
	 * @param money
	 * @return 잔액
	 * @throws InvelidException
	 */
	public long deposit(long money) throws InvelidException {
		if(money <= 0) {
			throw new InvelidException("입금하고자 하는 금액은 0원이거나 음수일 수 없습니다.");
		}
		restMoney += money;
		return restMoney;
	}
	
	/**
	 * 출금하기 Method
	 * @param money
	 * @return 잔액
	 * @throws InvelidException
	 */
	public long withdraw(long money) throws InvelidException {
		if(money <= 0) {
			throw new InvelidException("출금하고자 하는 금액은 0원이거나 음수일 수 없습니다.");
		}
		if(restMoney < money) {
			throw new InvelidException("현재 잔액보다 더 큰 금액을 입금할 수 없습니다.");
		}
		restMoney -= money;
		return restMoney;
	}
	
	/**
	 * 비밀번호 확인
	 * @param passwd
	 * @return boolean
	 */
	public boolean cheakPasswd(int passwd) {
		return this.passwd == passwd;
	}
}
