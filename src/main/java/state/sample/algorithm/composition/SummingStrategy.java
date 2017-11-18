package state.sample.algorithm.composition;


import state.sample.algorithm.Measurement;
import state.sample.algorithm.MeasurementUtil;

import java.util.Collection;


public class SummingStrategy implements IAggregationStrategy {

	@Override
	public Measurement aggregate(Collection<Measurement> measurements) {
		return new Measurement(MeasurementUtil.sumX(measurements), MeasurementUtil.sumY(measurements));
	}
}
