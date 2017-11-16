/*
 * (c) LibriCore
 * 
 * Created on Jan 6, 2006
 * 
 * SeeReferenceTag.java
 */
package librisuite.business.cataloguing.authority;

import librisuite.business.common.CorrelationValues;
import librisuite.hibernate.ReferenceType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author paulm
 * @version $Revision: 1.1 $, $Date: 2006/01/11 13:36:22 $
 * @since 1.0
 */
public class SeeReferenceTag extends SeeSeeAlsoReference {
	private static final Log logger = LogFactory.getLog(SeeReferenceTag.class);
	/**
	 * Class constructor
	 *
	 * 
	 * @since 1.0
	 */
	public SeeReferenceTag() {
		super();
	}

	public boolean correlationChangeAffectsKey(CorrelationValues v) {
		logger.debug("does " + v + " affect my key? ");
		if (!super.correlationChangeAffectsKey(v)) {
			logger.debug("super says no");
			logger.debug("position is " + getRefTypeCorrelationPosition());
			logger.debug(
				"value is " + v.getValue(getRefTypeCorrelationPosition()));
			logger.debug(
				"result is "
					+ !ReferenceType.isSeenFrom(
						v.getValue(getRefTypeCorrelationPosition())));
			return !ReferenceType.isSeenFrom(
				v.getValue(getRefTypeCorrelationPosition()));
		} else {
			return true;
		}
	}

}
