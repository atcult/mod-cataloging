package org.folio.cataloging.business.codetable;

import java.io.Serializable;
import java.util.List;

/**
 * Holds a single entry for select/option lists where the valueProperty is a String.
 *
 * @author paulm
 * @author agazzarini
 * @since 1.0
 */
public class ValueLabelElement<V> implements Comparable<ValueLabelElement>, Serializable {
	private V value;
	private String label;

	/**
	 * Decodes the incoming value among a given set of key/value pairs.
	 *
	 * @param value the search criterion.
	 * @param elements the search set.
	 * @return the label associated with the matching element, null otherwise.
	 */
	public static String decode(final String value, final List<ValueLabelElement> elements) {
		return elements.stream()
				.filter(element -> element.getValue().equals(value))
				.findFirst()
				.map(ValueLabelElement::getLabel)
				.orElse(null);
	}
	
	public ValueLabelElement() {}

	/**
	 * Builds a new {@link ValueLabelElement} with the given pair.
	 *
	 * @param value the element value.
	 * @param label the element label.
	 */
	public ValueLabelElement(final V value, final String label){
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public V getValue() {
		return value;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public void setValue(final V value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ValueLabelElement)
				&& ((ValueLabelElement)obj).value.equals(value)
				&& ((ValueLabelElement)obj).label.equals(label);
	}

	@Override
	public int hashCode() {
		return value.hashCode() + label.hashCode();
	}


	@Override
	public String toString() {
		return "(" + value + " = " + label + ")";
	}

	@Override
	public int compareTo(final ValueLabelElement pair) {
		if (pair == null) return -1;
		return label.compareTo(pair.getLabel());
	}
}