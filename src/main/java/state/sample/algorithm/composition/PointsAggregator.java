package state.algorithm.composition;

import state.algorithm.Measurement;

import java.util.ArrayList;
import java.util.Collection;


public class PointsAggregator {

	protected final Collection<Measurement> measurements;
	private final IMeasurementFilter filter;
	private final IAggregationStrategy aggregator;

	public PointsAggregator(Collection<Measurement> measurements, IMeasurementFilter filter,
			IAggregationStrategy aggregator) {
		this.measurements = measurements;
		this.filter = filter;
		this.aggregator = aggregator;
	}

	public Measurement aggregate() {
		Collection<Measurement> local = new ArrayList<Measurement>(measurements);
		local = filter.filter(local);
		return aggregator.aggregate(local);
	}

}
