package ams;

public class InvalidException extends Exception {
	InvalidException() {}
	InvalidException(String message) {
		super(message);
	}
}
