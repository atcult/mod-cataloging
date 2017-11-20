/*
 * (c) LibriCore
 * 
 * $Author: wimc $
 * $Date: 2005/03/10 08:50:51 $
 * $Locker:  $
 * $Name:  $
 * $Revision: 1.2 $
 * $Source: /source/LibriSuite/src/librisuite/bean/cataloguing/bibliographic/codelist/MotionPictureConfiguration.java,v $
 * $State: Exp $
 */
package org.folio.cataloging.bean.cataloguing.bibliographic.codelist;

import org.folio.cataloging.dao.persistence.T_MP_CONFIG;

/**
 * @author Wim Crols
 * @version $Revision: 1.2 $, $Date: 2005/03/10 08:50:51 $
 * @since 1.0
 */
public class MotionPictureConfiguration extends CodeListBean {

	public MotionPictureConfiguration() {
		super(T_MP_CONFIG.class);
	}

}
