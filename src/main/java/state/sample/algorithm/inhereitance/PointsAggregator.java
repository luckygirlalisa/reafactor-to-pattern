package state.algorithm.inhereitance;

import state.algorithm.Measurement;

import java.util.Collection;


public abstract class PointsAggregator {

	protected abstract Collection<Measurement> filterMeasurements();
	protected abstract Measurement aggregateMeasurements();

	protected Collection<Measurement> measurements;

	protected PointsAggregator(Collection<Measurement> measurements) {
		this.measurements = measurements;
	}

	public Measurement aggregate() {
		measurements = filterMeasurements();
		return aggregateMeasurements();
	}

}
