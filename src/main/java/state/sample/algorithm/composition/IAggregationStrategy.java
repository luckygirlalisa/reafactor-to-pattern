package state.algorithm.composition;

import state.algorithm.Measurement;

import java.util.Collection;


public interface IAggregationStrategy {

	Measurement aggregate(Collection<Measurement> measurements);

}
