/*
 * (c) LibriCore
 * 
 * Created on Jul 2, 2004
 * 
 * DataAccessException.java
 */
package librisuite.business.common;

import librisuite.business.exception.LibrisuiteException;


/**
 * @author paulm
 * @version %I%, %G%
 * @since 1.0
 */
public class RelationshipTagException extends LibrisuiteException {

	/**
	 * @see librisuite.business.exception.LibrisuiteException#LibrisuiteException()
	 */
	public RelationshipTagException() {
		super();
	}

	/**
	 * @see librisuite.business.exception.LibrisuiteException#LibrisuiteException(String)
	 */
	public RelationshipTagException(String message) {
		super(message);
	}

	/**
	 * @see librisuite.business.exception.LibrisuiteException#LibrisuiteException(String, Throwable)
	 */
	public RelationshipTagException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see librisuite.business.exception.LibrisuiteException#LibrisuiteException(Throwable)
	 */
	public RelationshipTagException(Throwable cause) {
		super(cause);
	}

}
