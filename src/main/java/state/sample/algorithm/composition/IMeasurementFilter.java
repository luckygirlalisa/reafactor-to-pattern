package state.sample.algorithm.composition;
import state.sample.algorithm.Measurement;

import java.util.Collection;


public interface IMeasurementFilter {

	Collection<Measurement> filter(Collection<Measurement> measurements);

}
