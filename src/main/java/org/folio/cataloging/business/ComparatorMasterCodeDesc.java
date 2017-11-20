package org.folio.cataloging.business;

import java.util.Comparator;

public class ComparatorMasterCodeDesc implements Comparator 
{
	 public int compare(Object emp1, Object emp2)
	 { 
//---->  Descending sorting
	     Integer master1Code = new Integer(((MasterListElement)emp1).getIdCollection());        
	     Integer master2Code = new Integer(((MasterListElement)emp2).getIdCollection());
	     return master2Code.compareTo(master1Code);
	 }
}
