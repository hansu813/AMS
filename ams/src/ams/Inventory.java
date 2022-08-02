package ams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 은행 계좌 관리 프로그램의 CRUD
 * @author 김한수
 *
 */
public class Inventory {
	private Map<String, Account> accounts;
	
	public Inventory() {
		accounts = new LinkedHashMap<>();
	}
	
	/**
	 * 전체 계좌 반환
	 * @return List<Account>
	 */
	public List<Account> getAccounts() {
		List<Account> list = new ArrayList<>();
		for(Entry<String, Account> entrySet : accounts.entrySet()) {
			list.add(entrySet.getValue());
		}
		return list;
	}
	
	public int getCount() {
		return accounts.size();
	}
	
	/**
	 * 신규 계좌 등록
	 * @param account
	 */
	public void open(Account account) {
		accounts.put(account.getAccountNum(), account);
	}
	
	/**
	 * 계좌번호로 계좌 찾기
	 * @param accountNum
	 * @return List<Account>
	 */
	public Account findNum(String accountNum) {
		return accounts.get(accountNum);
	}
	/**
	 * 예금주명으로 계좌 찾기
	 * @param accountOwner
	 * @return List<Account>
	 */
	public List<Account> findOwner(String accountOwner) {
		List<Account> findList = new ArrayList<>();
		Collection<Account> list = accounts.values();
		Iterator<Account> iter = list.iterator();
		while(iter.hasNext()) {
			Account account = iter.next();
			if(account.getAccountOwner().equals(accountOwner)) 
				findList.add(account);
		}
		return findList;
	}
	/**
	 * 계좌 삭제하기
	 * @param accountNum
	 * @return Account
	 */
	public Account remove(String accountNum) {
		return accounts.remove(accountNum);
	}
}
