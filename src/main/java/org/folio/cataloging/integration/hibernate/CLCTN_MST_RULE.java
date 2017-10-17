package org.folio.cataloging.integration.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import librisuite.business.common.DataAccessException;
import librisuite.business.common.Persistence;
import librisuite.business.common.PersistenceState;
import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Session;

import com.casalini.cataloguing.business.DAOCollectionRule;
import com.libricore.librisuite.common.HibernateUtil;

public class CLCTN_MST_RULE implements Persistence 
{
	private static final long serialVersionUID = 2522128570785338271L;

	static DAOCollectionRule dao = new DAOCollectionRule();
	private List recordCollectionList = new ArrayList();  /** contiene CLCTN_MST_RULE_TMP **/
	private List recordsList = new ArrayList();           /** contiene CLCTN_MST_RULE_RECORD **/
	private List collectionsList = new ArrayList();       /** contiene CLCTN_MST_RULE_REL **/
	
	private Integer ruleId;
	private String ruleDescription;
	private String level;
	private String dataType;
	private String dataPublRange;
	private Date dataUploadFrom;
	private Date dataUploadTo;
	private Date dataInsert;
	private Date dataUpdate;
	private Date dataProcessing;
	private String flagProcessing;
	
	private PersistenceState persistenceState = new PersistenceState();

	public CLCTN_MST_RULE() {
		super();
	}

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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ruleId.intValue();
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CLCTN_MST_RULE other = (CLCTN_MST_RULE) obj;
		if (ruleId != other.ruleId)
			return false;
		return true;
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

	public void generateNewKey() throws DataAccessException {
	}

	public static DAOCollectionRule getDao() {
		return dao;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataPublRange() {
		return dataPublRange;
	}

	public void setDataPublRange(String dataPublRange) {
		this.dataPublRange = dataPublRange;
	}

	public Date getDataInsert() {
		return dataInsert;
	}

	public void setDataInsert(Date dataInsert) {
		this.dataInsert = dataInsert;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Date getDataProcessing() {
		return dataProcessing;
	}

	public void setDataProcessing(Date dataProcessing) {
		this.dataProcessing = dataProcessing;
	}

	public String getFlagProcessing() {
		return flagProcessing;
	}

	public void setFlagProcessing(String flagProcessing) {
		this.flagProcessing = flagProcessing;
	}

	public Date getDataUploadFrom() {
		return dataUploadFrom;
	}

	public void setDataUploadFrom(Date dataUploadFrom) {
		this.dataUploadFrom = dataUploadFrom;
	}

	public Date getDataUploadTo() {
		return dataUploadTo;
	}

	public void setDataUploadTo(Date dataUploadTo) {
		this.dataUploadTo = dataUploadTo;
	}

	public List getRecordCollectionList() {
		return recordCollectionList;
	}

	public void setRecordCollectionList(List recordCollectionList) {
		this.recordCollectionList = recordCollectionList;
	}

	public List getRecordsList() {
		return recordsList;
	}

	public void setRecordsList(List recordsList) {
		this.recordsList = recordsList;
	}

	public List getCollectionsList() {
		return collectionsList;
	}

	public void setCollectionsList(List collectionsList) {
		this.collectionsList = collectionsList;
	}	
}