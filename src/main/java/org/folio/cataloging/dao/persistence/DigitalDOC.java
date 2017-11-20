package org.folio.cataloging.dao.persistence;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Session;

import org.folio.cataloging.dao.DAOCasDigAdmin;
import org.folio.cataloging.dao.common.HibernateUtil;

import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.Persistence;
import org.folio.cataloging.business.common.PersistenceState;

public class DigitalDOC implements Persistence {

	static DAOCasDigAdmin dao = new DAOCasDigAdmin();
	private PersistenceState persistenceState = new PersistenceState();
	
	private String relativePath;
	private String fileName;
	//private Blob   fullText;
	private Integer bibItemNumber;
	private Date   uploadDate;
	private Integer fileSize;
	private Integer userViewCode;
	
	public PersistenceState getPersistenceState() {
		return persistenceState;
	}

	public void setPersistenceState(PersistenceState state) {
		persistenceState = state;
	}

	public void evict(Object obj) throws DataAccessException {
		persistenceState.evict(obj);
	}

	public void evict() throws DataAccessException {
		evict((Object)this);
	}
	
	public HibernateUtil getDAO() {
		return dao;
	}

	public int getUpdateStatus() {
		return persistenceState.getUpdateStatus();
	}

//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + bibItemNumber;
//		return result;
//	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DigitalDOC other = (DigitalDOC) obj;
		if (relativePath+fileName != other.relativePath+other.fileName)
			return false;
		return true;
	}

	public boolean isChanged() {
		return persistenceState.isChanged();
	}

	public boolean isDeleted() {
		return persistenceState.isDeleted();
	}

	public boolean isNew() {
		return persistenceState.isNew();
	}

	public boolean isRemoved() {
		return persistenceState.isRemoved();
	}

	public void markChanged() {
		persistenceState.markChanged();
	}

	public void markDeleted() {
		persistenceState.markDeleted();
	}

	public void markNew() {
		persistenceState.markNew();
	}

	public void markUnchanged() {
		persistenceState.markUnchanged();
	}
	
	public boolean onDelete(Session arg0) throws CallbackException {
		return persistenceState.onDelete(arg0);
	}

	public void onLoad(Session arg0, Serializable arg1) {
		persistenceState.onLoad(arg0, arg1);
	}

	public boolean onSave(Session arg0) throws CallbackException {
		return persistenceState.onSave(arg0);
	}

	public boolean onUpdate(Session arg0) throws CallbackException {
		return persistenceState.onUpdate(arg0);
	}

	public void setUpdateStatus(int i) {
		persistenceState.setUpdateStatus(i);
	}

	/* (non-Javadoc)
	 * @see librisuite.business.common.Persistence#generateNewKey()
	 */
	public void generateNewKey() throws DataAccessException {
		// not applicable for this class
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

//	public Blob getFullText() {
//		return fullText;
//	}
//
//	public void setFullText(Blob fullText) {
//		this.fullText = fullText;
//	}

	public Integer getBibItemNumber() {
		return bibItemNumber;
	}

	public void setBibItemNumber(Integer bibItemNumber) {
		this.bibItemNumber = bibItemNumber;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUploadDateString() {
		if (getUploadDate() != null) {
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			return formatter.format(getUploadDate());
		}
		else {
			return "";
		}
	}
	
	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getUserViewCode() {
		return userViewCode;
	}

	public void setUserViewCode(Integer userViewCode) {
		this.userViewCode = userViewCode;
	}

}
