package org.folio.cataloging.business.cataloguing.bibliographic;

import org.folio.cataloging.business.cataloguing.common.AccessPoint;
import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.PersistentObjectWithView;
import org.folio.cataloging.business.common.UserViewHelper;
import org.folio.cataloging.util.StringText;

public abstract class BibliographicAccessPoint extends AccessPoint implements PersistentObjectWithView 
{
	private String materialSpecified;
	private UserViewHelper userViewHelper = new UserViewHelper();

	public void setStringText(StringText stringText) {
		materialSpecified = stringText.getSubfieldsWithCodes("3").toString();
		super.setStringText(stringText);
	}

	public StringText getStringText() {
		StringText result = super.getStringText();
		result.parse(materialSpecified);
		return result;
	}

	public BibliographicAccessPoint() {
		super();
	}

	public BibliographicAccessPoint(int itemNumber) {
		super(itemNumber);
	}

	public void generateNewKey() throws DataAccessException {
		super.generateNewKey();
		if (getDescriptor().isNew()) {
			getDescriptor().getKey().setUserViewString(getUserViewString());
		}
		setUserViewString(getDescriptor().getKey().getUserViewString());
	}

	public String getMaterialSpecified() {
		return materialSpecified;
	}

	public String getUserViewString() {
		return userViewHelper.getUserViewString();
	}

	public void setUserViewString(String s) {
		userViewHelper.setUserViewString(s);
	}

	public int getBibItemNumber() {
		return getItemNumber();
	}

	public void setBibItemNumber(int itemNumber) {
		setItemNumber(itemNumber);
	}

	public void setMaterialSpecified(String string) {
		materialSpecified = string;
	}

	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			BibliographicAccessPoint anApf = (BibliographicAccessPoint)obj;
			return this.getUserViewString().equals(anApf.getUserViewString());
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return super.hashCode() + getUserViewString().hashCode();
	}
}