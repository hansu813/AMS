package ams;

public class InvelidException extends Exception {
	public InvelidException() {
		this("오류 발생. 관리자(shn813@naver.com)에게 문의바랍니다.");
	}
	public InvelidException(String message) {
		super(message);
	}
}
