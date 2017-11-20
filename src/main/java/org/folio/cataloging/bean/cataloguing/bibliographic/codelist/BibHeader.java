/*
 * (c) LibriCore
 * 
 * Created on Nov 18, 2005
 * 
 * BibHeader.java
 */
package org.folio.cataloging.bean.cataloguing.bibliographic.codelist;

import org.folio.cataloging.dao.persistence.T_BIB_HDR;

/**
 * @author paulm
 * @version $Revision: 1.2 $, $Date: 2005/12/01 13:50:05 $
 * @since 1.0
 */
public class BibHeader extends CodeListBean {

	/**
	 * Class constructor
	 *
	 * @param clazz
	 * @since 1.0
	 */
	public BibHeader() {
		super(T_BIB_HDR.class);
	}

}
