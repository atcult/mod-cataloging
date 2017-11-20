package org.folio.cataloging.dao.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.Persistence;
import org.folio.cataloging.business.common.PersistenceState;
import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Session;

import org.folio.cataloging.dao.DAOCollectionRuleCST;
import org.folio.cataloging.dao.common.HibernateUtil;

public class CLCTN_CST_RULE implements Persistence 
{
	private static final long serialVersionUID = 2522128570785338271L;

	static DAOCollectionRuleCST dao = new DAOCollectionRuleCST();
	
	private List recordCollectionList = new ArrayList();  /** contiene CLCTN_CST_RULE_TMP **/
	private List recordsList = new ArrayList();           /** contiene CLCTN_CST_RULE_RECORD **/
	
//	private List recordsListFromCollectionSource = new ArrayList(); /** contiene CLCTN_CST_RULE_RECORD **/
	
	private Integer ruleId;
	private String ruleDescription;
	private String level;
	private String dataType;
	private String dataPublRange;
	private Date dataUploadFrom;
	private Date dataUploadTo;
	private String dataEmbRange;
	private Date dataEmbFrom;
	private Date dataEmbTo;
	private Date dataInsert;
	private Date dataUpdate;
	private Date dataProcessing;
	private String flagProcessing;
	
	private String customerCode;
	private Long collectionSource; 
	private Long collectionTarget;
	
	
	private PersistenceState persistenceState = new PersistenceState();

	public CLCTN_CST_RULE() {
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
		CLCTN_CST_RULE other = (CLCTN_CST_RULE) obj;
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

	public static DAOCollectionRuleCST getDao() {
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

	public String getDataEmbRange() {
		return dataEmbRange;
	}

	public void setDataEmbRange(String dataEmbRange) {
		this.dataEmbRange = dataEmbRange;
	}

	public Date getDataEmbFrom() {
		return dataEmbFrom;
	}

	public void setDataEmbFrom(Date dataEmbFrom) {
		this.dataEmbFrom = dataEmbFrom;
	}

	public Date getDataEmbTo() {
		return dataEmbTo;
	}

	public void setDataEmbTo(Date dataEmbTo) {
		this.dataEmbTo = dataEmbTo;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Long getCollectionSource() {
		return collectionSource;
	}

	public void setCollectionSource(Long collectionSource) {
		this.collectionSource = collectionSource;
	}

	public Long getCollectionTarget() {
		return collectionTarget;
	}

	public void setCollectionTarget(Long collectionTarget) {
		this.collectionTarget = collectionTarget;
	}

//	public List getRecordsListFromCollectionSource() {
//		return recordsListFromCollectionSource;
//	}

	/**
	 * Il metodo trasformo la lista di amicusNumber ritornata in lista di CLCTN_CST_RULE_RECORD
	 * @param recordsListFromCollectionSource
	 */
	public void setRecordsListFromCollectionSource(List recordsListFromCollectionSource) 
	{
		List recordRuleList = new ArrayList();
		CLCTN_CST_RULE_RECORD ruleRecord = null; 
		for (int i = 0; i < recordsListFromCollectionSource.size(); i++) {
			ruleRecord = new CLCTN_CST_RULE_RECORD(getRuleId(), Long.valueOf((String)recordsListFromCollectionSource.get(i)));
			recordRuleList.add(ruleRecord);
		}
		Collections.sort(recordRuleList);
		setRecordsList(recordRuleList);
	}
}