package state.algorithm.composition;

import state.algorithm.Measurement;
import state.algorithm.MeasurementUtil;

import java.util.Collection;


public class LowPassFilter implements IMeasurementFilter {

	@Override
	public Collection<Measurement> filter(Collection<Measurement> measurements) {
		return MeasurementUtil.WhereLessThanXandY(measurements, 100, 100);
	}

}
