package state.sample.algorithm.inhereitance;


import state.sample.algorithm.Measurement;
import state.sample.algorithm.MeasurementUtil;

import java.util.Collection;


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
		return new Measurement(MeasurementUtil.averageX(measurements), MeasurementUtil.averageY(measurements));

	}
}
