package state.sample.algorithm.inheritance;

import state.sample.algorithm.Measurement;
import state.sample.algorithm.MeasurementUtil;

import java.util.Collection;


public class SummingAggregator extends state.sample.algorithm.inheritance.PointsAggregator {

	public SummingAggregator(Collection<Measurement> measurements) {
		super(measurements);
	}

	@Override
	protected Collection<Measurement> filterMeasurements() {
		return measurements;
	}

	@Override
	protected Measurement aggregateMeasurements() {
		return new Measurement(MeasurementUtil.sumX(measurements), MeasurementUtil.sumY(measurements));
	}
}
