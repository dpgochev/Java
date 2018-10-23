package blocks;

import java.util.Comparator;
import java.util.HashMap;
import blocks.SlidingBlocks;

public class HeuristicsComparator implements Comparator<HashMap<Integer, Pair>> {

	@Override
	public int compare(HashMap<Integer, Pair> first, HashMap<Integer, Pair> second) {
		if (SlidingBlocks.heuristics(first, SlidingBlocks.originalMatrix) < SlidingBlocks.heuristics(second,
				SlidingBlocks.originalMatrix))
			return -1;
		if (SlidingBlocks.heuristics(first, SlidingBlocks.originalMatrix) > SlidingBlocks.heuristics(second,
				SlidingBlocks.originalMatrix))
			return 1;
		else
			return 0;
	}

}
