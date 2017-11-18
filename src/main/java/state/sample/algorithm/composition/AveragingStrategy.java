package state.sample.algorithm.composition;

import state.sample.algorithm.Measurement;
import state.sample.algorithm.MeasurementUtil;

import java.util.Collection;


public class AveragingStrategy implements IAggregationStrategy {

	@Override
	public Measurement aggregate(Collection<Measurement> measurements) {
		return new Measurement(MeasurementUtil.averageX(measurements), MeasurementUtil.averageY(measurements));
	}

}
