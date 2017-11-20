package org.folio.cataloging.business.common.group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.folio.cataloging.business.cataloguing.bibliographic.MarcCorrelationException;
import org.folio.cataloging.business.cataloguing.common.Tag;
import org.folio.cataloging.business.common.DataAccessException;

public class MarcGroupManager implements GroupManager {

	private List groupList = new ArrayList/*<TagGroup>*/();

	public MarcGroupManager() {
		super();
	}

	public void add(TagGroup group) {
		groupList.add(group);
	}

	public TagGroup getGroup(Tag tag) throws MarcCorrelationException, DataAccessException {
		Iterator it = groupList.iterator();
		while (it.hasNext()) {
			TagGroup group = (TagGroup) it.next();
			if(group.contains(tag)) return group;
		}
		return null;
	}

	public boolean isSameGroup(Tag tag1, Tag tag2) throws MarcCorrelationException, DataAccessException {
		TagGroup group = getGroup(tag1);
		if(group==null) return false;
		return group.contains(tag2);
	}

}