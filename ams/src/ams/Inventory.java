package ams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

	private Map<String, Account> accounts;

	public Inventory() {
		accounts = new LinkedHashMap<>();
	}
	public Inventory(Map<String, Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 계좌 추가 생성
	 * 
	 * @param account
	 */
	public void create(Account account) {
		if(account instanceof MinusAccount) {
			accounts.put(((MinusAccount) account).getAccountNum(), account);
		} else {
			accounts.put(account.getAccountNum(), account);
		}
	}
	
	public void update(Account account) {
		String accountType = account.getAccountType();
		String accountOwner = account.getAccountOwner();
		String accountNum = account.getAccountNum();
		int passwd = account.getPasswd();
		long restMoney = account.getRestMoney();
		long borrowMoney = 0;
		if(account instanceof MinusAccount) {
			borrowMoney = ((MinusAccount) account).getBorrowMoney();
			create(new MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney));
		} else {
			create(new Account("accountNum", "accountOwner", passwd, restMoney));
		}
	}
	
	/**
	 * 전체 계좌 목록 반환
	 * 
	 * @return Collection<Account>
	 */
	public List<Account> readList() {
		List<Account> list = new ArrayList<>();
		accounts.forEach((String, Account) -> 
			list.add(Account));
		
		return list;
	}

	/**
	 * 계좌 번호로 검색
	 * 
	 * @param accountNum
	 * @return Account
	 */
	public Account findNum(String accountNum) {
		return accounts.get(accountNum);
	}

	/**
	 * 예금주명으로 검색
	 * 
	 * @param accountOwner
	 * @return
	 */
	public List<Account> findOwner(String accountOwner) {
		List<Account> searchList = new ArrayList<>();
		Collection<Account> findList = accounts.values();
		Iterator<Account> iter = findList.iterator();
		while (iter.hasNext()) {
			Account account = iter.next();
			if (account.getAccountOwner().equals(accountOwner)) {
				searchList.add(account);
			}
		}
		return searchList;
	}

	/**
	 * 계좌 삭제
	 * 
	 * @param accountNum
	 * @return Account
	 */
	public Account remove(String accountNum) {
		return accounts.remove(accountNum);
	}
}
