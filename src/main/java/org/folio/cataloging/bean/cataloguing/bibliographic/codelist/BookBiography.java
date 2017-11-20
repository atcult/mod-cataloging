/*
 * (c) LibriCore
 * 
 * $Author: wimc $
 * $Date: 2005/03/10 08:45:04 $
 * $Locker:  $
 * $Name:  $
 * $Revision: 1.2 $
 * $Source: /source/LibriSuite/src/librisuite/bean/cataloguing/bibliographic/codelist/BookBiography.java,v $
 * $State: Exp $
 */
package org.folio.cataloging.bean.cataloguing.bibliographic.codelist;

import org.folio.cataloging.dao.persistence.T_BOOK_BGPHY;

/**
 * @author Wim Crols
 * @version $Revision: 1.2 $, $Date: 2005/03/10 08:45:04 $
 * @since 1.0
 */
public class BookBiography extends CodeListBean {

	public BookBiography() {
		super(T_BOOK_BGPHY.class);
	}

}
