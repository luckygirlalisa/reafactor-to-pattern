package state.sample.algorithm.composition;

import state.sample.algorithm.Measurement;

import java.util.Collection;


public interface IAggregationStrategy {

	Measurement aggregate(Collection<Measurement> measurements);

}
