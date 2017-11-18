package state.algorithm.composition;

import state.algorithm.Measurement;
import state.algorithm.MeasurementUtil;

import java.util.Collection;


public class AveragingStrategy implements IAggregationStrategy {

	@Override
	public Measurement aggregate(Collection<Measurement> measurements) {
		return new Measurement(MeasurementUtil.averageX(measurements), MeasurementUtil.averageY(measurements));
	}

}
