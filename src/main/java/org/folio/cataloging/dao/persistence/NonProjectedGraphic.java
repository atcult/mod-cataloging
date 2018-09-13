package org.folio.cataloging.dao.persistence;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.dao.SystemNextNumberDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author paulm
 * @author nbianchini
 * @since 1.0
 */
public class NonProjectedGraphic extends PhysicalDescription {
	private char colourCode;
	private char primarySupportMaterialCode;
	private char secondarySupportMaterialCode;
	private char obsolete1;

	/*
		private char colourCode = 'u';
	private char primarySupportMaterialCode = 'u';
	private char secondarySupportMaterialCode = 'u';
	private char obsolete1;

	 */

	public NonProjectedGraphic() {
		super();
		setHeaderType(27);
		//setGeneralMaterialDesignationCode('k');
		//setSpecificMaterialDesignationCode('u');
	}

	/* (non-Javadoc)
	 * @see FixedField#getDisplayString()
	 */
	public String getDisplayString() {
		String result =
			""
				+ getGeneralMaterialDesignationCode()
				+ getSpecificMaterialDesignationCode()
				+ " "
				+ getColourCode()
				+ getPrimarySupportMaterialCode()
				+ getSecondarySupportMaterialCode();
		return result;
	}

	/* (non-Javadoc)
	 * @see librisuite.business.cataloguing.bibliographic.Tag#generateNewKey()
	 */
  public void generateNewKey(final Session session) throws DataAccessException, HibernateException {
		SystemNextNumberDAO dao = new SystemNextNumberDAO();
		setKeyNumber(dao.getNextNumber("X6", session));
	}

	/* (non-Javadoc)
	 * @see librisuite.business.cataloguing.bibliographic.PhysicalDescription#isNonProjectedGraphic()
	 */
	public boolean isNonProjectedGraphic() {
		return true;
	}

	/**
	 *
	 * @since 1.0
	 */
	public char getColourCode() {
		return colourCode;
	}

	/**
	 *
	 * @since 1.0
	 */
	public char getPrimarySupportMaterialCode() {
		return primarySupportMaterialCode;
	}

	/**
	 *
	 * @since 1.0
	 */
	public char getSecondarySupportMaterialCode() {
		return secondarySupportMaterialCode;
	}

	/**
	 *
	 * @since 1.0
	 */
	public void setColourCode(char c) {
		colourCode = c;
	}

	/**
	 *
	 * @since 1.0
	 */
	public void setPrimarySupportMaterialCode(char c) {
		primarySupportMaterialCode = c;
	}

	/**
	 *
	 * @since 1.0
	 */
	public void setSecondarySupportMaterialCode(char c) {
		secondarySupportMaterialCode = c;
	}

	/**
	 *
	 * @since 1.0
	 */
	public char getObsolete1() {
		return obsolete1;
	}

	/**
	 *
	 * @since 1.0
	 */
	public void setObsolete1(char c) {
		obsolete1 = c;
	}

	public Element generateModelXmlElementContent(Document xmlDocument) {
		Element content = null;
		if (xmlDocument != null) {
			content = xmlDocument.createElement("content");
			content.setAttribute("generalMaterialDesignationCode", "" + getGeneralMaterialDesignationCode());
			content.setAttribute("specificMaterialDesignationCode", "" + getSpecificMaterialDesignationCode());
			content.setAttribute("colourCode", "" + getColourCode());
			content.setAttribute("primarySupportMaterialCode", "" + getPrimarySupportMaterialCode());
			content.setAttribute("secondarySupportMaterialCode", "" + getSecondarySupportMaterialCode());
		}
		return content;
	}

	public void parseModelXmlElementContent(Element xmlElement) {
		Element content = (Element) xmlElement.getChildNodes().item(0);
		setGeneralMaterialDesignationCode(content.getAttribute("generalMaterialDesignationCode").charAt(0));
		setSpecificMaterialDesignationCode(content.getAttribute("specificMaterialDesignationCode").charAt(0));
		setColourCode(content.getAttribute("colourCode").charAt(0));
		setPrimarySupportMaterialCode(content.getAttribute("primarySupportMaterialCode").charAt(0));
		setSecondarySupportMaterialCode(content.getAttribute("secondarySupportMaterialCode").charAt(0));
	}

}
