package state.algorithm.composition;
import state.algorithm.Measurement;

import java.util.Collection;


public interface IMeasurementFilter {

	Collection<Measurement> filter(Collection<Measurement> measurements);

}
