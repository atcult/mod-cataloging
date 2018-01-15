/*
 * (c) LibriCore
 * 
 * Created on Nov 2, 2005
 * 
 * DAOAUT.java
 */
package org.folio.cataloging.dao;

import net.sf.hibernate.Session;
import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.RecordNotFoundException;
import org.folio.cataloging.dao.common.HibernateUtil;
import org.folio.cataloging.dao.persistence.AUT;

/**
 * @author paulm
 * @version $Revision: 1.3 $, $Date: 2005/12/12 12:54:36 $
 * @since 1.0
 */
public class DAOAUT extends HibernateUtil {
//TODO add update to hdg_aut_cnt on add and delete
	public AUT load(final Session session, final int id) throws DataAccessException {
		AUT itm = (AUT) get(session, AUT.class, new Integer(id));
		if (itm == null) {
			throw new RecordNotFoundException();
		}
		else {
			return itm;
		}
	}
}
