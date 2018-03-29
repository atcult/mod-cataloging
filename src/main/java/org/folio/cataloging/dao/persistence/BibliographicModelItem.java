/*
 * (c) LibriCore
 * 
 * Created on 2004/11/30
 * 
 * $Author: Paulm $
 * $Date: 2005/12/01 13:50:04 $
 * $Locker:  $
 * $Name:  $
 * $Revision: 1.7 $
 * $Source: /source/LibriSuite/src/librisuite/business/cataloguing/bibliographic/BibliographicModelItem.java,v $
 * $State: Exp $
 */
package org.folio.cataloging.dao.persistence;

import java.io.Serializable;

import org.folio.cataloging.dao.persistence.ModelItem;
import org.folio.cataloging.business.common.Persistence;
import org.folio.cataloging.dao.BibliographicModelItemDAO;

/**
 * Represents a Model/Template for initiating new bibliographic item
 *
 * @author paulm
 * @author carment
 * @since 1.0
 */
public class BibliographicModelItem extends ModelItem implements Persistence, Serializable {

	/**
	 * Gets the dao.
	 *
	 * @return the dao
	 */
	public BibliographicModelItemDAO getDAO() {
		return new BibliographicModelItemDAO();
	}



}
