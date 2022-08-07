package ams;

/**
 * 파일입출력을 추가한
 * 가상 계좌 관리 시스템
 * @author 김한수
 *
 */
public class Account {
//	상수
	public static final String BANKNAME = "Korea Bank";
	
//	인스턴스 변수
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;
	private String accountType = "입출금 계좌";
	
//	생성자
	public Account() {}
	public Account(String accountNum, String accountOwner, int passwd) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = 0;
	}
	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}
	
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
	public long getRestMoney() {
		return restMoney;
	}
	public static String getBankName() {
		return BANKNAME;
	}
	public String getAccountType() {
		return accountType;
	}

//	메서드
/**
 * 비밀번호 체크
 * @param passwd
 * @return boolean
 */
	public boolean checkPW(int passwd) {
		boolean check;
		check = (this.passwd == passwd) ? true : false;
		return check;
	}
	
	@Override
	public String toString() {
		String text = String.format("%-10s\t%-12s\t%-6s\t%-,8d\t", getAccountType(), getAccountNum(), getAccountOwner(),
				getRestMoney());
		return text;
	}

/**
 * 입금 기능	
 * @param money
 * @return long
 * @throws InvalidException
 */
	public long deposit(long money) throws InvalidException {
		if(money <= 0) { 
			InvalidException e = new InvalidException("입금 금액은 0원 이하이일 수 없습니다.");
		}
		else {
			restMoney += money;
		}
		return restMoney;
	}
	
/**
 * 출금 기능
 * @param money
 * @return long
 * @throws InvalidException
 */
	public long withdraw(long money) throws InvalidException {
		InvalidException e;
		if(money <= 0) {
			e = new InvalidException("출금 금액은 0원 이하이일 수 없습니다.");
		} else if (money > restMoney) {
			e = new InvalidException("잔액이 부족합니다.");
		} else {
			restMoney -= money;
		}
		return restMoney;
	}
	
}
