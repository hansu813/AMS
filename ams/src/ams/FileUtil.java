package ams;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

	/** 계좌 수 저장을 위한 파일 컬럼 사이즈 설정 */
	private static final int ACCOUNT_COUNT_LENGTH = 4;

	/** 레코드(계좌번호, 예금주, 계좌유형, 비밀번호, 잔액, 대출금액) */
	private static final int NUM_LENGTH = 26;
	private static final int OWNER_LENGTH = 10;
	private static final int TYPE_LENGTH = 14;
	private static final int PASSWD_LENGTH = 4;
	private static final int RESTMONEY_LENGTH = 8;
	private static final int BORROWMONEY_LENGTH = 8;

	/** 계좌 정보 저장을 위한 레코드 사이즈 : 64바이트 */
	public static final int RECORD_LENGTH = NUM_LENGTH + OWNER_LENGTH + TYPE_LENGTH + PASSWD_LENGTH + RESTMONEY_LENGTH
			+ BORROWMONEY_LENGTH;

	private static final String PATH = "account.dat";
	private int recordCount;

	RandomAccessFile file;
	Inventory inventory;

	public FileUtil() throws IOException {
		file = new RandomAccessFile(PATH, "rw");
		if(file.length() != 0) {
			recordCount = file.readInt();
		} else {
			recordCount = 0;
		}
	}

	public int getRecordCount() {
		return recordCount;
	}
	
	
	
	public void save(Collection<Account> accounts) throws IOException {
		List<Account> list = null;
		Account account = null;
		for (int i = 0; i < accounts.size(); i++) {
//			account = inventory.listCast().get(i);
			if(accounts.isEmpty())
				account = accounts.iterator().next();

			file.seek((recordCount * RECORD_LENGTH) + ACCOUNT_COUNT_LENGTH);

			String accountType = account.getAccountType();
			String accountNum = account.getAccountNum();
			String accountOwner = account.getAccountOwner();
			int passwd = account.getPasswd();
			long restMoney = account.getRestMoney();
			long borrowMoney = 0;
			if (account instanceof MinusAccount) {
				borrowMoney = ((MinusAccount) account).getBorrowMoney();
			}

			int charCount = accountType.length();
			for (int j = 0; j < (TYPE_LENGTH / 2); j++) {
				if (j < charCount) {
					file.writeChar(accountType.charAt(j));
				} else {
					file.writeChar(' ');
				}
			}

			charCount = accountNum.length();
			for (int j = 0; j < (NUM_LENGTH / 2); j++) {
				if (j < charCount) {
					file.writeChar(accountNum.charAt(j));
				} else {
					file.writeChar(' ');
				}
			}

			charCount = accountOwner.length();
			for (int j = 0; j < (OWNER_LENGTH / 2); j++) {
				if (j < charCount) {
					file.writeChar(accountOwner.charAt(j));
				} else {
					file.writeChar(' ');
				}
			}

			file.writeInt(passwd);
			file.writeLong(restMoney);
			file.writeLong(borrowMoney);

			file.seek(0);
			file.writeInt(++recordCount);
		}
	}
	
	public void save(Inventory inventory) throws IOException {
		List<Account> list = inventory.readList();
		Account account = null;
		Account tempAccount = null;
		for(int i = 0; i < list.size(); i++) {
			file.seek((recordCount * RECORD_LENGTH) + ACCOUNT_COUNT_LENGTH);
			tempAccount = list.get(i);
			String accountType = tempAccount.getAccountType();
			String accountNum = tempAccount.getAccountNum();
			String accountOwner = tempAccount.getAccountOwner();
			int passwd = tempAccount.getPasswd();
			long restMoney = tempAccount.getRestMoney();
			long borrowMoney = 0;
			if(account instanceof MinusAccount) {
				borrowMoney = ((MinusAccount)tempAccount).getBorrowMoney();
			}
			
			int charCount = accountType.length();
			for (int j = 0; j < (TYPE_LENGTH / 2); j++) {
				file.writeChar((j < charCount) ? accountType.charAt(j) : ' ');
			}
			charCount = accountNum.length();
			for (int j = 0; j < (NUM_LENGTH / 2); j++) {
				file.writeChar((j < charCount) ? accountNum.charAt(j) : ' ');
			}
			charCount = accountOwner.length();
			for (int j = 0; j < (OWNER_LENGTH / 2); j++) {
				file.writeChar((j < charCount) ? accountOwner.charAt(j) : ' ');
			}
			
			file.writeInt(passwd);
			file.writeLong(restMoney);
			file.writeLong(borrowMoney);
			
			file.seek(0);
			file.writeInt(++recordCount);
		}
	}

	public Map<String, Account> road() throws IOException {
		Map<String, Account> list = new LinkedHashMap<>();
		Account account = null;
		String accountType = "";
		String accountNum = "";
		String accountOwner = "";
		int passwd = 0;
		long restMoney = 0;
		long borrowMoney = 0;
		for (int i = 0; i <= recordCount; i++) {
			try {
			file.seek((i * RECORD_LENGTH) + ACCOUNT_COUNT_LENGTH);
			for (int j = 0; j < (TYPE_LENGTH / 2); j++) {
				accountType += file.readChar();
			}
			accountType = accountType.trim();
			for (int j = 0; j < (NUM_LENGTH / 2); j++) {
				accountNum += file.readChar();
			}
			accountNum = accountNum.trim();
			for (int j = 0; j < (OWNER_LENGTH / 2); j++) {
				accountOwner += file.readChar();
			}
			accountOwner = accountOwner.trim();
			passwd = file.readInt();
			restMoney = file.readLong();
			if (accountType.equals("마이너스 계좌")) {
				borrowMoney = file.readLong();
				account = new MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney);
			} else {
				account = new Account(accountNum, accountOwner, passwd, restMoney);
			}

			list.put(accountNum, account);
			} catch(IOException e) {
				break;
			}
		}
		remove();
		return list;
	}
	
	private void remove() {
		try {
			file.setLength(0);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		try {
			if(file != null) file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
