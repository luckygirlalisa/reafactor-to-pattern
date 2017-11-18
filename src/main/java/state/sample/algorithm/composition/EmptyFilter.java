package state.sample.algorithm.composition;


import state.sample.algorithm.Measurement;

import java.util.Collection;

public class EmptyFilter implements IMeasurementFilter {

	@Override
	public Collection<Measurement> filter(Collection<Measurement> measurements) {
		return measurements;
	}

}
