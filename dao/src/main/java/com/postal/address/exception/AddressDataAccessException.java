package com.postal.address.exception;

public class AddressDataAccessException extends Exception {

	private static final long serialVersionUID = -7555737729592645839L;

	public AddressDataAccessException(final String message, final Throwable t) {
		super(message, t);
	}
}
