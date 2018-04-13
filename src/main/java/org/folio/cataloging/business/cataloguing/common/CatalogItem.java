/*
 * (c) LibriCore
 * 
 * Created on Oct 27, 2005
 * 
 * CatalogItem.java
 */
package org.folio.cataloging.business.cataloguing.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.folio.cataloging.business.cataloguing.bibliographic.FixedField;
import org.folio.cataloging.business.cataloguing.bibliographic.MarcCorrelationException;
import org.folio.cataloging.business.cataloguing.bibliographic.TagComparator;
import org.folio.cataloging.business.cataloguing.bibliographic.VariableField;
import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.filter.SameDescriptorTagFilter;
import org.folio.cataloging.business.common.filter.TagFilter;
import org.folio.cataloging.business.common.group.*;
import org.folio.cataloging.business.descriptor.Descriptor;
import org.folio.cataloging.dao.BibliographicModelItemDAO;
import org.folio.cataloging.dao.persistence.BibliographicModelItem;
import org.folio.cataloging.dao.persistence.CorrelationKey;
import org.folio.cataloging.dao.persistence.Model;
import org.folio.cataloging.dao.persistence.ModelItem;
import org.folio.cataloging.exception.DuplicateTagException;
import org.folio.cataloging.exception.MandatoryTagException;
import org.folio.cataloging.exception.ModCatalogingException;
import org.folio.cataloging.exception.ValidationException;
import org.folio.cataloging.model.Subfield;
import org.folio.cataloging.shared.Validation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author paulm
 * @version $Revision: 1.10 $, $Date: 2006/11/23 15:01:47 $
 * @since 1.0
 */
public abstract class CatalogItem implements Serializable {
	private static final Log logger =
		LogFactory.getLog(CatalogItem.class);

	protected List deletedTags = new ArrayList();

	protected ModelItem modelItem = null;

	protected List tags = new ArrayList();

	/**
	 * Class constructor
	 *
	 * 
	 * @since 1.0
	 */
	public CatalogItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Comparator tagComparator = new Comparator() {
		/* to compare tags
		 * based on Marc Tag number
		 */
		public int compare(Object obj1, Object obj2) {
			try {
				return ((Tag) obj1).getMarcEncoding().getMarcTag().compareTo(
					((Tag) obj2).getMarcEncoding().getMarcTag());
			} catch (ModCatalogingException e) {
				throw new RuntimeException("Error comparing tags");
			}
		}
	};

	public void addAllTags(Tag[] tags) {
		for (int i = 0; i < tags.length; i++) {
			addTag(tags[i]);
		}
	}

	public void addDeletedTag(Tag aTag) {
		if (!deletedTags.contains(aTag)) {
			deletedTags.add(aTag);
		}
	}

	/**
		 * adds the newTag to the list _AFTER_ the point i
		 *
		 */
	public void addTag(int i, Tag newTag) {
		if (tags.size() > i) {
			tags.add(i + 1, newTag);
		} else {
			tags.add(newTag);
		}
	}

	public void addTag(Tag newTag) {
		tags.add(newTag);
	}

	abstract public void checkForMandatoryTags() throws MandatoryTagException;

	/**
		 * Checks if the specified tag is illegally repeated in the item and
		 * throws an exception if so
		 * 
		 * @since 1.0
		 */
	public void checkRepeatability(int index)
		throws DataAccessException, MarcCorrelationException, DuplicateTagException {
		Tag t = getTag(index);
		Validation bv = t.getValidation();
		if (!bv.isMarcTagRepeatable()) {
			List l = new ArrayList(getTags());
			l.remove(index);
			Collections.sort(l, tagComparator);
			if (Collections.binarySearch(l, t, tagComparator) >= 0) {
				throw new DuplicateTagException(index);
			}
		}
	}

	/**
	 * Finds the first tag occurrence of the given tag number.  If a length shorter than
	 * 3 is given (e.g. "1"), then the first tag starting with a 1 will be returned
	 * 
	 * @since 1.0
	 */
	public Tag findFirstTagByNumber(String s) {
		try {
			Tag result = null;
			Iterator iter = getTags().iterator();
			while (iter.hasNext()) {
				result = (Tag) iter.next();
				if (result
					.getMarcEncoding()
					.getMarcTag()
					.substring(0, s.length())
					.equals(s)) {
					return result;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @return the Amicus number for the item (aut or bib)
	 */
	public Integer getAmicusNumber() {
		return getItemEntity().getAmicusNumber();
	}

	/**
		 * 
		 */
	public List getDeletedTags() {
		return deletedTags;
	}

	public abstract ItemEntity getItemEntity();

	/**
		 * @since 1.0
		 */
	public ModelItem getModelItem() {
		return modelItem;
	}

	public int getNumberOfTags() {
		return tags.size();
	}

	public Tag getTag(int i) {
		//TODO constrain i
		return (Tag) tags.get(i);
	}

	public abstract TagImpl getTagImpl();

	/**
		 * 
		 */
	public List getTags() {
		return tags;
	}

	abstract public int getUserView();

	/**
		 * 
		 * @since 1.0
		 */
	public char getVerificationLevel() {
		return getItemEntity().getVerificationLevel();
	}

	/**
		 * remove a tag from the deletedTags list (by Object)
		 *
		 */
	public void removeDeletedTag(Tag tag) {
		deletedTags.remove(tag);
	}

	/**
		 * remove a tag from the Item (by Object)
		 *
		 */
	public void removeTag(Tag tag) {
		tags.remove(tag);
	}

	/**
		 * 
		 */
	public void setDeletedTags(List list) {
		deletedTags = list;
	}

	public abstract void setItemEntity(ItemEntity item);

	/**
		 * @throws DataAccessException 
	 * @since 1.0
		 */
	public void setModelItem(Model model) throws DataAccessException {
		BibliographicModelItemDAO dao = new BibliographicModelItemDAO();
		/*if(dao.getModelUsageByItem(this.getAmicusNumber().intValue())){
		   this.modelItem = dao.load(this.getAmicusNumber().intValue());
		   this.modelItem.markChanged();
		}
		else{
		  this.modelItem = new BibliographicModelItem();
		  this.modelItem.markNew();
		}
		*/
		this.modelItem.setItem(this.getAmicusNumber().longValue());
		this.modelItem.setModel(model);
		this.modelItem.setRecordFields(model.getRecordFields());
	}

	public void setModelItemNoAN(Model model) {
		this.modelItem = new BibliographicModelItem();
		this.modelItem.markNew();
		this.modelItem.setModel(model);
		this.modelItem.setRecordFields(model.getRecordFields());
	}
	/**
		 * replace an old tag with a new one in the bibItem
		 *
		 */
	public void setTag(Tag oldTag, Tag newTag) {
		if (getAmicusNumber() != null) {
			newTag.setItemNumber(getAmicusNumber().intValue());
		}
		tags.set(tags.indexOf(oldTag), newTag);
	}

	/**
		 * @param set
		 */
	public void setTags(List set) {
		tags = set;
	}

	/**
		 * 
		 * @since 1.0
		 */
	public void setVerificationLevel(char verificationLevel) {
		getItemEntity().setVerificationLevel(verificationLevel);
	}
   /**
    * @ deprecated use ({@link #sortTags()}
    */
	public void simpleSortTags() {
		Collections.sort(getTags(), new TagComparator());
	}
	
	public void sortTags() {
		try {
			// raggruppa i tag
			LinkedHashMap ht = populateGroups();
			// ordina i gruppi in
			List groupList = new ArrayList(ht.values());
			Collections.sort(groupList, new GroupComparator());
			// riaccoda i tag ordinati
			List tagSet = unlist(groupList);
			// sostituiscel'elenco dei tag del catalogo con quello ordinato
			setTags(tagSet);
	
		} catch (MarcCorrelationException e) {
			logger.warn("MarcCorrelationException in sortTags");
		} catch (DataAccessException e) {
			logger.warn("DataAccessException in sortTags");
		}
	}

	/**
	 * Rimette i tag in seguenza ordinando preventivamente i gruppi
	 * in base al sequence number
	 * @param groupList
	 * @return
	 */
	private List unlist(List groupList) {
		List tagSet = new ArrayList();
		Iterator it = groupList.iterator();
		while (it.hasNext()) {
			Object elem = (Object) it.next();
			if(elem instanceof Tag) tagSet.add(elem);
			else {
				TagContainer container = (TagContainer)elem;
				container.sort();
				tagSet.addAll((container).getList());
			}
		}
		return tagSet;
	}

	/**
	 * Riorganizza i tag in gruppi
	 * @return
	 * @throws MarcCorrelationException
	 * @throws DataAccessException
	 */
	private LinkedHashMap populateGroups() throws MarcCorrelationException, DataAccessException {
		LinkedHashMap ht = new LinkedHashMap();
		GroupManager groupManager = BibliographicGroupManager.getInstance();
		Iterator it = tags.iterator();
		while (it.hasNext()) {
			Tag tag = (Tag) it.next();
			TagGroup group = groupManager.getGroup(tag);
			if(group==null) {
				ht.put(tag, new UniqueTagContainer(tag));
				continue;
			}
			TagContainer tc = (TagContainer) ht.get(group);
			if(tc==null) {
				tc = new MultiTagContainer();
				ht.put(group, tc);
			}
			tc.add(tag);
		}
		return ht;
	}

	public byte[] toMarc()
		throws MarcCorrelationException, DataAccessException {
		DecimalFormat n4 = new DecimalFormat("0000");
		DecimalFormat n5 = new DecimalFormat("00000");
		ByteArrayOutputStream directory = new ByteArrayOutputStream();
		ByteArrayOutputStream body = new ByteArrayOutputStream();
		ByteArrayOutputStream record = new ByteArrayOutputStream();
		String entry;
		Leader leaderTag = (Leader) getTag(0);
		leaderTag.getItemEntity().setCharacterCodingSchemeCode('a');
		String leader = leaderTag.getDisplayString();
		Tag aTag;
		CorrelationKey correlation;
		Iterator iter = this.getTags().iterator();
		iter.next(); // skip leader
		try {
			while (iter.hasNext()) {
				aTag = (Tag) iter.next();
				correlation = aTag.getMarcEncoding();
				if (aTag.isFixedField()) {
					entry =
						((FixedField) aTag).getDisplayString()
							+ Subfield.FIELD_DELIMITER;
				} else {
					VariableField vTag = (VariableField) aTag;
					entry =""+
						correlation.getMarcFirstIndicator()
							+ correlation.getMarcSecondIndicator()
							+ vTag.getStringText().getMarcDisplayString(
								Subfield.SUBFIELD_DELIMITER)
							+ Subfield.FIELD_DELIMITER;
				}
				int offset = body.size();
				body.write(entry.getBytes("UTF-8"));
				directory.write(correlation.getMarcTag().getBytes("UTF-8"));
				directory.write(
					n4.format(body.size() - offset).getBytes("UTF-8"));
				directory.write(n5.format(offset).getBytes("UTF-8"));
			}
			directory.write(Subfield.FIELD_DELIMITER.getBytes("UTF-8"));
			body.write(Subfield.RECORD_DELIMITER.getBytes("UTF-8"));
			record.write(
				n5.format(
					body.size() + directory.size() + leader.length()).getBytes(
					"UTF-8"));
			record.write(leader.substring(5, 12).getBytes("UTF-8"));
			record.write(
				n5.format(directory.size() + leader.length()).getBytes(
					"UTF-8"));
			record.write(leader.substring(17).getBytes("UTF-8"));
			record.write(directory.toByteArray());
			record.write(body.toByteArray());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return record.toByteArray();
	}

	public void validate()
		throws ValidationException, DataAccessException, MarcCorrelationException {
		checkForMandatoryTags();
		for (int i = 0; i < getTags().size(); i++) {
			checkRepeatability(i);
			((Tag) getTag(i)).validate(i);
		}
	}

	public List findTags(TagFilter filter, Object optionalFilterParameter) {
		List/*<Tag>*/ tags = getTags();
		List/*<Tag>*/ filteredList = new ArrayList/*<Tag>*/();
		int indice = -1;
		Iterator it = tags.iterator();
		while(it.hasNext()){
			Tag current = (Tag)it.next();
				// JDK 1.5
				// for(Tag current:tags){
			indice++;
			try {
				if(filter.accept(current, optionalFilterParameter)){
				//if(current.getMarcEncoding().getMarcTag().equals(numTag)){
					filteredList.add(current);
				}
			} catch (MarcCorrelationException e) {
				logger.debug("MarcCorrelationException getting current tag");
				continue;
			} catch (DataAccessException e) {
				logger.debug("DataAccessException getting current tag");
				continue;
			} 
		}
		return filteredList;
	}
	public boolean isDecriptorAlreadyPresent(Descriptor descriptor, Tag current)
			throws MarcCorrelationException, DataAccessException {
		boolean isPresent = false;
		TagFilter filter = null;
		/* controllo pima del descrittore se ci sono due tag uguali */
		if(current instanceof AccessPoint)
		if (findTagsEqual(((AccessPoint)current).getFunctionCode()).size() >= 2) {
			filter = new SameDescriptorTagFilter();
			if(!descriptor.equals(((Browsable)current).getDescriptor()))
			   isPresent = !findTags(filter, descriptor).isEmpty();
		} else
			return isPresent = false;
		return isPresent;

	}

	public List findTagsEqual(int functionCode) throws MarcCorrelationException,
			DataAccessException {
		List/* <Tag> */tags = getTags();
		List/* <Tag> */filteredList = new ArrayList/* <Tag> */();
		Iterator it = tags.iterator();
		while (it.hasNext()) {
			Tag current = (Tag) it.next();
			if (current instanceof AccessPoint)
			if (((AccessPoint)current).getFunctionCode()==functionCode) {
				filteredList.add(current);
			}
		}
		return filteredList;
	}
	
	public List findTagsFixedEqual(String marcTag)
			throws MarcCorrelationException, DataAccessException {
		List/* <Tag> */tags = getTags();
		List/* <Tag> */filteredList = new ArrayList/* <Tag> */();
		Iterator it = tags.iterator();
		while (it.hasNext()) {
			Tag current = (Tag) it.next();
			if (current instanceof FixedField )
				if (((FixedField) current).getMarcEncoding().getMarcTag().equals(marcTag)) {
					filteredList.add(current);
				}
		}
		return filteredList;
	}
	public List findTagsVariableEqual(String marcTag)
			throws MarcCorrelationException, DataAccessException {
		List/* <Tag> */tags = getTags();
		List/* <Tag> */filteredList = new ArrayList/* <Tag> */();
		Iterator it = tags.iterator();
		while (it.hasNext()) {
			Tag current = (Tag) it.next();
			if (current instanceof VariableField)
				if (((VariableField) current).getMarcEncoding().getMarcTag()
						.equals(marcTag)) {
					filteredList.add(current);
				}
		}
		return filteredList;
	}
	 
	/**
	 * Metodo che restituisce i tag corrispondenti alla categoria passata
	 * @return
	 */
	public List findTagByCategory(short cat) 
	{
		List tagsList = new ArrayList();
		
		try {
			Tag result = null;
			Iterator iter = getTags().iterator();
			while (iter.hasNext()) {
				result = (Tag) iter.next();
				if (result.getMarcEncoding().getMarcTagCategoryCode()==(cat))
					tagsList.add(result);
			}
			return tagsList;
			
		} catch (Exception e) {
			return tagsList;
		}
	}
}
