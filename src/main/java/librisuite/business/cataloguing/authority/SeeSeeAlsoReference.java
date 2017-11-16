/*
 * (c) LibriCore
 * 
 * Created on Dec 5, 2005
 * 
 * SeeSeeAlsoReferences.java
 */
package librisuite.business.cataloguing.authority;

import org.w3c.dom.Element;

import com.libricore.librisuite.common.StringText;
import com.libricore.librisuite.common.Subfield;

/**
 * @author paulm
 * @version $Revision: 1.4 $, $Date: 2006/01/11 13:36:22 $
 * @since 1.0
 */
public abstract class SeeSeeAlsoReference extends AuthorityReferenceTag {
	
	private static final String VARIANT_CODES = "weij4";

	/**
	 * Class constructor
	 *
	 * 
	 * @since 1.0
	 */
	public SeeSeeAlsoReference() {
		super();
	}

	/* (non-Javadoc)
	 * @see librisuite.business.cataloguing.bibliographic.VariableField#getStringText()
	 */
	@Override
	public StringText getStringText() {
		String subw =
			""
				+ getReference().getPrintConstant()
				+ getReference().getAuthorityStructure()
				+ getReference().getEarlierRules()
				+ getReference().getNoteGeneration();
		StringText result = super.getStringText();
		result.addSubfield(new Subfield("w", subw));
		return result;
	}

	/* (non-Javadoc)
	 * @see librisuite.business.cataloguing.common.TagInterface#isHasSubfieldW()
	 */
	public boolean isHasSubfieldW() {
		return true;
	}


	/* (non-Javadoc)
	 * @see librisuite.business.cataloguing.common.TagInterface#parseModelXmlElementContent(org.w3c.dom.Element)
	 */
	public void parseModelXmlElementContent(Element xmlElement) {
		StringText s = StringText.parseModelXmlElementContent(xmlElement);
		String subw = s.getSubfieldsWithCodes("w").getSubfield(0).getContent();
		getReference().setPrintConstant(subw.charAt(0));
		getReference().setAuthorityStructure(subw.charAt(1));
		getReference().setEarlierRules(subw.charAt(2));
		getReference().setNoteGeneration(subw.charAt(3));
	}
	
	 public String getVariantCodes() {
			return VARIANT_CODES;
	}

}
