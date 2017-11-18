package state.sample.algorithm.composition;

import state.sample.algorithm.Measurement;
import state.sample.algorithm.MeasurementUtil;

import java.util.Collection;


public class LowPassFilter implements IMeasurementFilter {

	@Override
	public Collection<Measurement> filter(Collection<Measurement> measurements) {
		return MeasurementUtil.WhereLessThanXandY(measurements, 100, 100);
	}

}
