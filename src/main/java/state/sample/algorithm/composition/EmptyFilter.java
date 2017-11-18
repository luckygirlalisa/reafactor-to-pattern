package state.algorithm.composition;


import state.algorithm.Measurement;

import java.util.Collection;

public class EmptyFilter implements IMeasurementFilter {

	@Override
	public Collection<Measurement> filter(Collection<Measurement> measurements) {
		return measurements;
	}

}
