package state.sample.algorithm.inheritance;

import state.sample.algorithm.Measurement;
import state.sample.algorithm.MeasurementUtil;

import java.util.Collection;


public class LowPassAveragingAggregator extends AveragingAggregator {

	public LowPassAveragingAggregator(Collection<Measurement> measurements) {
		super(measurements);
	}

	@Override
	protected Collection<Measurement> filterMeasurements() {
		return MeasurementUtil.WhereLessThanXandY(measurements, 100, 100);
	}
}
