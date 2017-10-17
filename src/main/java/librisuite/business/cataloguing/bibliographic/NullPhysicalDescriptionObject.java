/*
 * (c) LibriCore
 * 
 * Created on Oct 15, 2004
 * 
 * Map.java
 */
package librisuite.business.cataloguing.bibliographic;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author paulm
 * @version $Revision: 1.3 $, $Date: 2005/12/01 13:50:04 $
 * @since 1.0
 */
public class NullPhysicalDescriptionObject extends PhysicalDescription {

	@Override
	public Element generateModelXmlElementContent(Document xmlDocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parseModelXmlElementContent(Element xmlElement) {
		// TODO Auto-generated method stub
		
	}

}
