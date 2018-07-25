package org.folio.cataloging.dao.persistence;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.PersistenceState;
import org.folio.cataloging.business.common.PersistentObjectWithView;
import org.folio.cataloging.business.common.UserViewHelper;
import org.folio.cataloging.dao.AbstractDAO;
import org.folio.cataloging.dao.DAODescriptor;
import org.folio.cataloging.dao.DAOPublTag;
import org.folio.cataloging.dao.DAOPublisherDescriptor;
import org.folio.cataloging.util.StringText;

import java.io.Serializable;

public class PUBL_TAG implements PersistentObjectWithView {
//	@Override
	public String toString() {
		return "PUBL_TAG(" + getPublisherTagNumber() + ", "
				+ getUserViewString() + ", " + getPublisherHeadingNumber()
				+ ", " + getSequenceNumber() + ", " + getOtherSubfields() + ")";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(PUBL_TAG.class);

	private int publisherTagNumber = -1;
	private PersistenceState persistence = new PersistenceState();
	private UserViewHelper uvh = new UserViewHelper();
	private int sequenceNumber;
	private PUBL_HDG descriptor = new PUBL_HDG();
	private String otherSubfields;
	private Integer publisherHeadingNumber;
	private static final DAOPublTag theDAO = new DAOPublTag();
	private static final DAODescriptor thePublisherDescriptor = new DAOPublisherDescriptor();

	public void evict() throws DataAccessException {
		evict(this);
	}

	public DAODescriptor getDescriptorDAO() {
		return thePublisherDescriptor;
	}

	public void generateNewKey() throws DataAccessException {
		logger
				.error("PUBL_TAG keys are generated by the owning PublisherTagDescriptor");
		throw new IllegalArgumentException();
	}

	public String getStringText() {
		String result = new String();
		//ADD new
		result= new StringText(getOtherSubfields()).getSubfieldsWithCodes("368").toString();
		if (getDescriptor() != null) {
			result = result + getDescriptor().getStringText();
		}
		if(getOtherSubfields()!=null)
		 result = result + new StringText(getOtherSubfields()).getSubfieldsWithoutCodes("368").toString();
		return result;
	}

	//@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof PUBL_TAG) {
			PUBL_TAG obj = (PUBL_TAG) arg0;
			return this.getPublisherTagNumber() == obj.getPublisherTagNumber()
					&& this.getUserViewString().equals(obj.getUserViewString())
					&& this.getSequenceNumber() == obj.getSequenceNumber();
		}
		return false;
	}

	public AbstractDAO getDAO() {
		return theDAO;
	}

	/**
	 * @return the content of subfield c
	 * 
	 * @since 1.0
	 */
	public String getDate() {
		return new StringText(getOtherSubfields()).getSubfieldsWithCodes("c")
				.toDisplayString();
	}

	public PUBL_HDG getDescriptor() {
		return descriptor;
	}

	public String getOtherSubfields() {
		return otherSubfields;
	}

	public Integer getPublisherHeadingNumber() {
		return publisherHeadingNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	//@Override
	public int hashCode() {
		return getPublisherTagNumber() * getSequenceNumber();
	}

	public void setDescriptor(PUBL_HDG heading) {
		this.descriptor = heading;
		if (heading != null) {
			setPublisherHeadingNumber(new Integer(heading.getHeadingNumber()));
		}
	}

	public void setStringText(String stringText) {
		StringText s = new StringText(stringText);
		getDescriptor().setStringText(
				s.getSubfieldsWithoutCodes("cefg").toString());
		setOtherSubfields(s.getSubfieldsWithCodes("cefg").toString());
	}

	public void setOtherSubfields(String otherSubfields) {
		this.otherSubfields = otherSubfields;
	}

	public void setPublisherHeadingNumber(Integer publisherHeadingNumber) {
		this.publisherHeadingNumber = publisherHeadingNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public void evict(Object obj) throws DataAccessException {
		persistence.evict(obj);
	}

	public int getUpdateStatus() {
		return persistence.getUpdateStatus();
	}

	public boolean isChanged() {
		return persistence.isChanged();
	}

	public boolean isDeleted() {
		return persistence.isDeleted();
	}

	public boolean isNew() {
		return persistence.isNew();
	}

	public boolean isRemoved() {
		return persistence.isRemoved();
	}

	public void markChanged() {
		persistence.markChanged();
	}

	public void markDeleted() {
		persistence.markDeleted();
	}

	public void markNew() {
		persistence.markNew();
	}

	public void markUnchanged() {
		persistence.markUnchanged();
	}

	public boolean onDelete(Session arg0) throws CallbackException {
		return persistence.onDelete(arg0);
	}

	public void onLoad(Session arg0, Serializable arg1) {
		persistence.onLoad(arg0, arg1);
	}

	public boolean onSave(Session arg0) throws CallbackException {
		return persistence.onSave(arg0);
	}

	public boolean onUpdate(Session arg0) throws CallbackException {
		return persistence.onUpdate(arg0);
	}

	public void setUpdateStatus(int i) {
		persistence.setUpdateStatus(i);
	}

	public String getUserViewString() {
		return uvh.getUserViewString();
	}

	public void setUserViewString(String string) {
		uvh.setUserViewString(string);
	}

	public int getPublisherTagNumber() {
		return publisherTagNumber;
	}

	public void setPublisherTagNumber(int publisherTagNumber) {
		this.publisherTagNumber = publisherTagNumber;
	}

}
