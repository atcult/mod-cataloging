package org.folio.cataloging.business.common.group;

import org.folio.cataloging.business.cataloguing.bibliographic.MarcCorrelationException;
import org.folio.cataloging.business.cataloguing.common.Tag;
import org.folio.cataloging.business.common.DataAccessException;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;


public class RegExpGroup extends MarcTagGroup {
	private RE regexp;
	
	public RegExpGroup(boolean canSort, boolean singleSort, String pattern) throws RESyntaxException {
		super(canSort, singleSort);
		regexp = new RE(pattern);
	}

	public boolean contains(Tag t) throws MarcCorrelationException, DataAccessException {
		return regexp.match(t.getMarcEncoding().getMarcTag());
	}
	
	
	
	public static void main(String[] args) {
		String pattern = "^52[0124] ";
		RE regexp = new RE(pattern);
		
	    System.out.println("Value: " + regexp.match("520 "));
	}
	
}
