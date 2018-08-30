package org.folio.cataloging.dao.persistence;

import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.dao.SystemNextNumberDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author paulm
 * @author nbianchini
 * @since 1.0
 */
public class Globe extends PhysicalDescription {
	private char colourCode;
	private char physicalMediumCode;
	private char typeOfReproductionCode;
	private Character obsolete1;
	private Character obsolete2;

	/*
	private char colourCode = 'a';
	private char physicalMediumCode = 'u';
	private char typeOfReproductionCode = 'u';
	private Character obsolete1;
	private Character obsolete2;
	 */
	public Globe() {
		super();
		setHeaderType(23);
		//setGeneralMaterialDesignationCode('d');
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
				+ getPhysicalMediumCode()
				+ getTypeOfReproductionCode();
		return result;
	}

	/* (non-Javadoc)
	 * @see librisuite.business.cataloguing.bibliographic.Tag#generateNewKey()
	 */
	public void generateNewKey() throws DataAccessException {
		SystemNextNumberDAO dao = new SystemNextNumberDAO();
		setKeyNumber(dao.getNextNumber("X1"));
	}

	/* (non-Javadoc)
	 * @see librisuite.business.cataloguing.bibliographic.PhysicalDescription#isGlobe()
	 */
	public boolean isGlobe() {
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
	public Character getObsolete1() {
		return obsolete1;
	}

	/**
	 * 
	 * @since 1.0
	 */
	public Character getObsolete2() {
		return obsolete2;
	}

	/**
	 * 
	 * @since 1.0
	 */
	public char getPhysicalMediumCode() {
		return physicalMediumCode;
	}

	/**
	 * 
	 * @since 1.0
	 */
	public char getTypeOfReproductionCode() {
		return typeOfReproductionCode;
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
	public void setObsolete1(Character character) {
		obsolete1 = character;
	}

	/**
	 * 
	 * @since 1.0
	 */
	public void setObsolete2(Character character) {
		obsolete2 = character;
	}

	/**
	 * 
	 * @since 1.0
	 */
	public void setPhysicalMediumCode(char c) {
		physicalMediumCode = c;
	}

	/**
	 * 
	 * @since 1.0
	 */
	public void setTypeOfReproductionCode(char c) {
		typeOfReproductionCode = c;
	}

	public Element generateModelXmlElementContent(Document xmlDocument) {
		Element content = null;
		if (xmlDocument != null) {
			content = xmlDocument.createElement("content");
			content.setAttribute("generalMaterialDesignationCode", "" + getGeneralMaterialDesignationCode());
			content.setAttribute("specificMaterialDesignationCode", "" + getSpecificMaterialDesignationCode());
			content.setAttribute("colourCode", "" + getColourCode());
			content.setAttribute("physicalMediumCode", "" + getPhysicalMediumCode());
			content.setAttribute("typeOfReproductionCode", "" + getTypeOfReproductionCode());
		}
		return content;
	}

	public void parseModelXmlElementContent(Element xmlElement) {
		Element content = (Element) xmlElement.getChildNodes().item(0);
		setGeneralMaterialDesignationCode(content.getAttribute("generalMaterialDesignationCode").charAt(0));
		setSpecificMaterialDesignationCode(content.getAttribute("specificMaterialDesignationCode").charAt(0));
		setColourCode(content.getAttribute("colourCode").charAt(0));
		setPhysicalMediumCode(content.getAttribute("physicalMediumCode").charAt(0));
		setTypeOfReproductionCode(content.getAttribute("typeOfReproductionCode").charAt(0));
	}

}
