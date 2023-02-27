package sml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * A "Labels" holds each label with its address in the machine in a hashmap.
 * During its lifetime, new labels can be added.
 * @author Telmuun Enkhbold
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) throws IOException {
		Objects.requireNonNull(label);
		if (labels.containsKey(label)) {
			String errorMessage = "The following label is duplicated: " + label;
			System.out.println(errorMessage);
			throw new IOException(errorMessage);
		}
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// Where can NullPointerException be thrown here?
		// NullPointerException can be thrown here when label is null.
		// Or, it can be thrown in the next steps when label does not exist.
		Objects.requireNonNull(label);
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels other) {
			return Objects.equals(this.labels, other.labels);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels);
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
