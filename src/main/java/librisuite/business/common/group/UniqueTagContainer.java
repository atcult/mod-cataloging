package librisuite.business.common.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import librisuite.business.cataloguing.common.Tag;

public class UniqueTagContainer implements TagContainer {
	private Tag tag = null;
	
	public void add(Tag tag) {
		this.tag = tag;
	}

	public Tag getLeaderTag() {
		return tag;
	}

	// si può migliorare, teoricamente non si dovrebbe ricreare una lista
	public Iterator iterator() {
		List l = new ArrayList();
		l.add(tag);
		return l.iterator();
	}

	public int compareTo(Object arg0) {
		try {
			TagContainer tc0 = (TagContainer) arg0;
			return this.getLeaderTag().getMarcEncoding().getMarcTag()
			.compareTo(tc0.getLeaderTag().getMarcEncoding().getMarcTag());
		} catch (Exception e) {
			return 0;
		} 
	}

	public UniqueTagContainer(Tag tag) {
		super();
		this.tag = tag;
	}

	// si può migliorare, teoricamente non si dovrebbe ricreare una lista
	public Collection getList() {
		List l = new ArrayList();
		l.add(tag);
		return l;
	}

	public void sort() {
		return;
	}

	
}
