package state.algorithm.composition;


import state.algorithm.Measurement;

import java.util.Collection;

import static state.algorithm.MeasurementUtil.sumX;
import static state.algorithm.MeasurementUtil.sumY;


public class SummingStrategy implements IAggregationStrategy {

	@Override
	public Measurement aggregate(Collection<Measurement> measurements) {
		return new Measurement(sumX(measurements), sumY(measurements));
	}
}
