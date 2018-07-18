/*
 * Created on May 6, 2004
 * */
package org.folio.cataloging.dao.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.folio.cataloging.business.common.ConfigHandler;
import org.folio.cataloging.business.common.Defaults;
import org.folio.cataloging.business.descriptor.Descriptor;
import org.folio.cataloging.business.descriptor.SkipInFiling;
import org.folio.cataloging.business.descriptor.SortFormParameters;
import org.folio.cataloging.dao.AbstractDAO;
import org.folio.cataloging.dao.DAOSubjectDescriptor;
import org.folio.cataloging.model.Subfield;
import org.folio.cataloging.shared.CorrelationValues;
import org.folio.cataloging.util.StringText;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Hibernate class for table SBJCT_HDG
 * 
 * @author paulm
 * @version $Revision: 1.17 $, $Date: 2006/09/27 08:24:29 $
 * @since 1.0
 */
public class SBJCT_HDG extends Descriptor implements Serializable, SkipInFiling {
	@Override
	public int getAuthoritySourceCode() {
		
		return getSourceCode();
	}

	public void setAuthoritySourceCode(int authoritySourceCode) {
		setSourceCode(authoritySourceCode);
	}

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(SBJCT_HDG.class);
	private String copyFromHeadingType;
	private Integer copyFromHeadingNumber;
	private int skipInFiling;
	private int typeCode;
	private int sourceCode;
	private String secondarySourceCode;
	private ConfigHandler configHandler = ConfigHandler.getInstance();

	/**
	 * 
	 * Class constructor - establishes default values for new subjects
	 * 
	 * 
	 * @since 1.0
	 */
	public SBJCT_HDG() {
		super();
		// setAccessPointLanguage(Defaults.getShort("subject.accessPointLanguage"));
		setScriptingLanguage(Defaults.getString("subject.scriptingLanguage"));
		setTypeCode(Defaults.getShort("subject.typeCode"));
		// setSourceCode(Defaults.getShort("subject.sourceCode"));
		// TODO add other defaults
		setVerificationLevel(Defaults.getChar("subject.verificationLevel"));

		setDefaultSourceCode();

	}

	/**
	 * Getter for typeCode
	 * 
	 * @return typeCode
	 */
	public int getTypeCode() {
		return typeCode;
	}

	/**
	 * Setter for typeCode
	 * 
	 * @param s
	 *            typeCode
	 */
	public void setTypeCode(int s) {
		typeCode = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.libricore.librisuite.business.rdms.Descriptor#getReferenceClass()
	 */
	public Class getReferenceClass(Class targetClazz) {
		if (targetClazz == this.getClass()) {
			return SBJCT_REF.class;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#getDefaultBrowseKey()
	 */
	public String getDefaultBrowseKey() {
		return "9P0";
	}

	public String getNextNumberKeyFieldCode() {
		return "SH";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#getDAO()
	 */
	public AbstractDAO getDAO() {
		return new DAOSubjectDescriptor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#getAccessPointClass()
	 */
	public Class getAccessPointClass() {
		return SubjectAccessPoint.class;
	}

	/**
	 * 
	 */
	public Integer getCopyFromHeadingNumber() {
		return copyFromHeadingNumber;
	}

	/**
	 * 
	 */
	public String getCopyFromHeadingType() {
		return copyFromHeadingType;
	}

	/**
	 * 
	 */
	public String getSecondarySourceCode() {
		return secondarySourceCode;
	}

	/**
	 * 
	 */
	public int getSkipInFiling() {
		return skipInFiling;
	}

	/**
	 * 
	 */
	public int getSourceCode() {
		return sourceCode;
	}

	/**
	 * 
	 */
	public void setCopyFromHeadingNumber(Integer integer) {
		copyFromHeadingNumber = integer;
	}

	/**
	 * 
	 */
	public void setCopyFromHeadingType(String string) {
		copyFromHeadingType = string;
	}

	/**
	 * 
	 */
	public void setSecondarySourceCode(String string) {
		if (SubjectSource.isOtherSource(getSourceCode())) {
			secondarySourceCode = string;
		} else {
			secondarySourceCode = null;
		}
	}

	/**
	 * 
	 */
	public void setSkipInFiling(short s) {
		skipInFiling = s;
	}

	/**
	 * 
	 */
	public void setSourceCode(int s) {
		sourceCode = s;
		if (!SubjectSource.isOtherSource(s)) {
			setSecondarySourceCode(null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#getCategory()
	 */
	public int getCategory() {
		return 18;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#getCorrelationValues()
	 */
	public CorrelationValues getCorrelationValues() {
		return new CorrelationValues(typeCode, CorrelationValues.UNDEFINED,
				sourceCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#setCorrelationValues(librisuite.business.common.CorrelationValues)
	 */
	public void setCorrelationValues(CorrelationValues v) {
		typeCode = v.getValue(1);
		sourceCode = v.getValue(3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#getSortFormParameters()
	 */
	public SortFormParameters getSortFormParameters() {
		return new SortFormParameters(100, 103, getTypeCode(), 0,
				getSkipInFiling());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see librisuite.hibernate.Descriptor#getHeadingNumberSearchIndex()
	 */
	public String getHeadingNumberSearchIndexKey() {
		return "229P";
	}

	public String getLockingEntityType() {
		return "SH";
	}

	public void setDefaultSourceCode() {
		short sourceCode = 0;
		sourceCode = new Short(configHandler.findValue("t_sbjct_hdg_src",
				"subject.typeCode"));
		setSourceCode(sourceCode);
		short accLang = 0;
		accLang = new Short(configHandler.findValue("t_lang_of_acs_pnt_sbjct",
				"subject.accessPointLanguage"));
		setAccessPointLanguage(accLang);

	}

	@Override
	public String buildBrowseTerm() {
		String returnString = new String();
		StringText text = new StringText(getStringText());
		Iterator iter = text.getSubfieldList().iterator();
		while (iter.hasNext()) {
			Subfield aStringTextSubField = (Subfield) iter.next();
			String content = aStringTextSubField.getContent();
			String code = aStringTextSubField.getCode();
			if (code.equals("v") || code.equals("x") || code.equals("y")
					|| code.equals("z")) {
				returnString = returnString.trim();
				returnString = returnString.concat("--");
				returnString = returnString.concat(content);
			} else {
				returnString = returnString.concat(content);
				returnString = returnString.concat(" ");
			}
		}

		return returnString.trim();
	}
}
