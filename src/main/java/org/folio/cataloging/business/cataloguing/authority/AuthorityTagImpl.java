/*
 * (c) LibriCore
 * 
 * Created on Oct 31, 2005
 * 
 * AuthorityTag.java
 */
package org.folio.cataloging.business.cataloguing.authority;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.folio.cataloging.business.cataloguing.bibliographic.MarcCorrelationException;
import org.folio.cataloging.business.cataloguing.bibliographic.PersistsViaItem;
import org.folio.cataloging.business.cataloguing.common.Catalog;
import org.folio.cataloging.business.cataloguing.common.Tag;
import org.folio.cataloging.business.cataloguing.common.TagImpl;
import org.folio.cataloging.business.cataloguing.common.Validation;
import org.folio.cataloging.dao.DAOAuthorityCorrelation;
import org.folio.cataloging.dao.DAOAuthorityValidation;
import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.SubfieldCodeComparator;
import org.folio.cataloging.dao.persistence.AUT;
import org.folio.cataloging.dao.persistence.Correlation;
import org.folio.cataloging.dao.persistence.CorrelationKey;

/**
 * @author paulm
 * @version $Revision: 1.4 $, $Date: 2005/12/21 08:30:32 $
 * @since 1.0
 */
public class AuthorityTagImpl extends TagImpl {

	private int authorityNumber;

	private static final DAOAuthorityCorrelation daoCorrelation = new DAOAuthorityCorrelation();

	private static final DAOAuthorityValidation daoValidation = new DAOAuthorityValidation();

	public int getItemNumber() {
		return getAuthorityNumber();
	}

	public void setItemNumber(int itemNumber) {
		setAuthorityNumber(itemNumber);
	}

	/**
	 * 
	 * @since 1.0
	 */
	public int getAuthorityNumber() {
		return authorityNumber;
	}

	/**
	 * 
	 * @since 1.0
	 */
	public void setAuthorityNumber(int i) {
		authorityNumber = i;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getAuthorityNumber();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see TagImpl#getMarcEncoding(Tag)
	 */
	public CorrelationKey getMarcEncoding(Tag t) throws DataAccessException,
			MarcCorrelationException {

		CorrelationKey key = daoCorrelation.getMarcEncoding(t.getCategory(),
				getHeadingType(t), t.getCorrelation(1), t.getCorrelation(2), t
						.getCorrelation(3));

		if (key == null) {
			throw new MarcCorrelationException();
		}

		return key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see TagImpl#getHeadingType(Tag)
	 */
	public String getHeadingType(Tag t) {
		return ((AUT) ((PersistsViaItem) t).getItemEntity()).getHeadingType();
	}

	public Validation getValidation(Tag t) throws MarcCorrelationException,
			DataAccessException {
		CorrelationKey key = getMarcEncoding(t);
		return daoValidation.load(key.getMarcTag(), t.getHeadingType(), t
				.getCategory());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see TagImpl#getCatalog()
	 */
	public Catalog getCatalog() {
		return new AuthorityCatalog();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see TagImpl#getValidEditableSubfields(short)
	 */
	public Set getValidEditableSubfields(short category) {
		Set set = new TreeSet(new SubfieldCodeComparator());
		switch (category) {
		case 6:
			set.add("d");
			break;
		case 15:
			set.addAll(Arrays.asList(new String[] { "d", "5" }));
			break;
		default:
			set.addAll(Arrays.asList(new String[] { "i", "e", "j", "4"}));
			break;
		}
		return set;
	}

	@Override
	public Correlation getCorrelation(String tagNumber, char indicator1,
			char indicator2, short category) throws DataAccessException {
		return daoCorrelation.getFirstAuthorityCorrelation(tagNumber, indicator1,
				indicator2, category);
	}

}
