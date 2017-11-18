package state.algorithm.inhereitance;


import state.algorithm.Measurement;

import java.util.Collection;

import static state.algorithm.MeasurementUtil.averageX;
import static state.algorithm.MeasurementUtil.averageY;


public class AveragingAggregator extends PointsAggregator {

	public AveragingAggregator(Collection<Measurement> measurements) {
		super(measurements);
	}

	@Override
	protected Collection<Measurement> filterMeasurements() {
		return measurements;
	}

	@Override
	protected Measurement aggregateMeasurements() {
		return new Measurement(averageX(measurements), averageY(measurements));

	}
}
