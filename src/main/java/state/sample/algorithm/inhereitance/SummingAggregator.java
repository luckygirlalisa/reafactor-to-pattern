package state.algorithm.inhereitance;

import state.algorithm.Measurement;

import java.util.Collection;

import static state.algorithm.MeasurementUtil.sumX;
import static state.algorithm.MeasurementUtil.sumY;


public class SummingAggregator extends PointsAggregator {

	public SummingAggregator(Collection<Measurement> measurements) {
		super(measurements);
	}

	@Override
	protected Collection<Measurement> filterMeasurements() {
		return measurements;
	}

	@Override
	protected Measurement aggregateMeasurements() {
		return new Measurement(sumX(measurements), sumY(measurements));
	}
}
