/*
 * (c) Carmen
 * 
 * Created on 01/09/2008
 * 
  */
package librisuite.hibernate;

import java.io.Serializable;

import librisuite.business.common.DataAccessException;
import librisuite.business.common.Persistence;
import librisuite.business.common.PersistenceState;
import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Session;

import com.casalini.cataloguing.business.DAOCollectionMaster;
import com.libricore.librisuite.common.HibernateUtil;


public  class  CLCTN_MST_CSTMR implements Persistence{
	
	private static final long serialVersionUID = -3104033992000315406L;
	
	private int collectionCode;
	private String customerCode;	

	public String getCustomerCode() {
		return customerCode;
	}


	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}


	public int getCollectionCode() {
		return collectionCode;
	}


	public void setCollectionCode(int collectionCode) {
		this.collectionCode = collectionCode;
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + collectionCode;
		result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CLCTN_MST_CSTMR other = (CLCTN_MST_CSTMR) obj;
		if (collectionCode != other.collectionCode)
			return false;
		if (customerCode == null) {
			if (other.customerCode != null)
				return false;
		} else if (!customerCode.equals(other.customerCode))
			return false;
		return true;
	}

	//-----------------------------
	// Persistence and LifeCycle
	//-----------------------------
	private PersistenceState persistenceState = new PersistenceState();
	
	public void evict() throws DataAccessException {
		persistenceState.evict(this);
	}

	public void generateNewKey() throws DataAccessException {
		// MIKE: this value should be assigned when all the objects are available (eng, ita, etc...) 
		// sequence = ((DAOCodeTable)getDAO()).suggestNewKey((CodeTable)this);
		
	}

	public HibernateUtil getDAO() {
		return new DAOCollectionMaster();
	}

	public int getUpdateStatus() {
		return persistenceState.getUpdateStatus();
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

	public void setUpdateStatus(int i) {
		persistenceState.setUpdateStatus(i);
	}

	public boolean onDelete(Session arg0) throws CallbackException {
		return persistenceState.onDelete(arg0);
	}

	public void onLoad(Session arg0, Serializable arg1) {
		persistenceState.onLoad(arg0,arg1);
	}

	public boolean onSave(Session arg0) throws CallbackException {
		return persistenceState.onSave(arg0);
	}

	public boolean onUpdate(Session arg0) throws CallbackException {
		return persistenceState.onUpdate(arg0);
	} 
	//-------------------------------
	// END Persistence and LifeCycle
	//-------------------------------
}
