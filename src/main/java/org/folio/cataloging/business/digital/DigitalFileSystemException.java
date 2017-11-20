package org.folio.cataloging.business.digital;

import org.folio.cataloging.exception.LibrisuiteException;

public class DigitalFileSystemException extends LibrisuiteException {

	public DigitalFileSystemException() {
		super();
	}
	
	public DigitalFileSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public DigitalFileSystemException(String message) {
		super(message);
	}

	public DigitalFileSystemException(Throwable cause) {
		super(cause);
	}

}
