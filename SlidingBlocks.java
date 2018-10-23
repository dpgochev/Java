package blocks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class SlidingBlocks<T> {

	public SlidingBlocks() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter N:");
		int N = sc.nextInt();
		this.dim = (int) Math.sqrt(N + 1);
		this.matrix = new Integer[dim][dim];
		this.finishedMatrix = new Integer[dim][dim];
		HeuristicsComparator newcomp = new HeuristicsComparator();
		SlidingBlocks.queue = new PriorityQueue(16, newcomp);

		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				System.out.println("Enter block " + i + " " + j);
				int next = sc.nextInt();
				matrix[i][j] = next;
				this.currentMatrix.put(next, new Pair(i, j));

			}
		}

		int k = 1;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				finishedMatrix[i][j] = k;

				if (k == N + 1) {

					Pair a = new Pair(i, j);
					zeroIndex = a;
					SlidingBlocks.originalMatrix.put(0, a);
				} else {
					SlidingBlocks.originalMatrix.put(k, new Pair(i, j));
				}

				k++;
			}
		}

		SlidingBlocks.queue.add(currentMatrix);
		set.add(currentMatrix);

		finishedMatrix[dim - 1][dim - 1] = 0;

		sc.close();
	}

	HashMap<Integer, Pair> currentMatrix = new HashMap<Integer, Pair>();
	static HashMap<Integer, Pair> originalMatrix = new HashMap<Integer, Pair>();
	static HashMap<Integer[][], Boolean> visitedStates = new HashMap<Integer[][], Boolean>();
	static Set<HashMap<Integer, Pair>> set = new HashSet<>();
	static PriorityQueue<HashMap<Integer, Pair>> queue;

	int dim;
	Integer[][] matrix;
	Integer[][] finishedMatrix;
	Pair zeroIndex;

	public HashMap<Integer, Pair> getHashMap(Integer[][] mat) {
		HashMap<Integer, Pair> ret = new HashMap<Integer, Pair>();
		int dim = mat.length;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				ret.put(mat[i][j], new Pair(i, j));
			}
		}
		return ret;

	}

	public static int heuristics(HashMap<Integer, Pair> currentMatrix, HashMap<Integer, Pair> originalMatrix) {
		int n = originalMatrix.size() - 1;
		int heuristics = 0;
		int x1;
		int x2;
		int y1;
		int y2;

		for (int i = 1; i <= n; i++) {

			x1 = originalMatrix.get(i).getFirst();
			x2 = currentMatrix.get(i).getFirst();
			y1 = originalMatrix.get(i).getSecond();
			y2 = currentMatrix.get(i).getSecond();
			heuristics = heuristics + Math.abs(x1 - x2) + Math.abs(y1 - y2);

		}
		return heuristics;

	}

	public static boolean possibleMoveLeft(HashMap<Integer, Pair> mat) {

		int i = mat.get(0).getFirst();
		int j = mat.get(0).getSecond() - 1;
		int dim = (int) Math.sqrt(mat.size());

		if ((i < 0 || i >= dim) || (j < 0 || j >= dim)) {
			return false;
		}
		return true;

	}

	public static boolean possibleMoveRight(HashMap<Integer, Pair> mat) {
		int i = mat.get(0).getFirst();
		int j = mat.get(0).getSecond() + 1;
		int dim = (int) Math.sqrt(mat.size());
		if ((i < 0 || i >= dim) || (j < 0 || j >= dim)) {
			return false;
		}
		return true;

	}

	public static boolean possibleMoveDown(HashMap<Integer, Pair> mat) {
		int i = mat.get(0).getFirst() + 1;
		int j = mat.get(0).getSecond();

		int dim = (int) Math.sqrt(mat.size());
		if ((i < 0 || i >= dim) || (j < 0 || j >= dim)) {
			return false;
		}
		return true;

	}

	public static boolean possibleMoveUp(HashMap<Integer, Pair> mat) {
		int i = mat.get(0).getFirst() - 1;
		int j = mat.get(0).getSecond();
		int dim = (int) Math.sqrt(mat.size());
		if ((i < 0 || i >= dim) || (j < 0 || j >= dim)) {
			return false;
		}
		return true;

	}

	public static HashMap<Integer, Pair> deepCopy(HashMap<Integer, Pair> original) {
		HashMap<Integer, Pair> copy = new HashMap<Integer, Pair>();

		// iterate over the map copying values into new map
		for (Map.Entry<Integer, Pair> entry : original.entrySet()) {
			Pair a = new Pair(entry.getValue().getFirst(), entry.getValue().getSecond());
			copy.put(entry.getKey(), a);
		}

		return copy;
	}

	public void generating2() {

		HashMap<Integer, Pair> first;
		HashMap<Integer, Pair> second;
		int i;
		int j;
		int key = 0;
		while (true) {
			first = queue.poll();

			second = deepCopy(first);
			if (heuristics(first, SlidingBlocks.originalMatrix) == 0) {
				System.out.println("Game ready");
				break;
			}
		}
	}

	public void generating() {

		HashMap<Integer, Pair> first;
		Stack<Node<String>> stack = new Stack<Node<String>>();
		int i;
		int j;
		int key = 0;

	
		Node<String> current=new Node("top");
		stack.add(current);

		while (true) {

			first = queue.poll();
			current = stack.pop();
			HashMap<Integer, Pair> left = deepCopy(first);
			HashMap<Integer, Pair> right = deepCopy(first);
			HashMap<Integer, Pair> down = deepCopy(first);
			HashMap<Integer, Pair> up = deepCopy(first);
			if (heuristics(first, SlidingBlocks.originalMatrix) == 0) {
				System.out.println("Game ready");
				
				break;
			}

			if (possibleMoveLeft(first)) {

				i = first.get(0).getFirst();
				j = first.get(0).getSecond();
				for (int x : first.keySet()) {
					if (first.get(x).equals(new Pair(i, j - 1))) {
						key = x;
						break;
					}
				}
				if (key == 0) {
					throw new RuntimeException();
				}
				left.get(key).setFirst(i);
				left.get(key).setSecond(j);
				left.get(0).setFirst(i);
				left.get(0).setSecond(j - 1);

				if (!set.contains(left)) {
					Node<String> lefty = new Node<>("left");
					lefty.setParent(current);
					stack.add(lefty);

					queue.add(left);
					set.add(left);
					//System.out.println("left");
				}

			}
			if (possibleMoveRight(first)) {

				i = first.get(0).getFirst();
				j = first.get(0).getSecond();
				for (int x : first.keySet()) {
					if (first.get(x).equals(new Pair(i, j + 1))) {
						key = x;
						break;
					}
				}
				if (key == 0) {
					throw new RuntimeException();
				}
				right.get(key).setFirst(i);
				right.get(key).setSecond(j);
				right.get(0).setFirst(i);
				right.get(0).setSecond(j + 1);

				if (!set.contains(right)) {
					Node<String> righty = new Node<>("right");
					righty.setParent(current);
					stack.add(righty);

					queue.add(right);
					set.add(right);
					//System.out.println("right");
				}
			}

			if (possibleMoveDown(first)) {

				i = first.get(0).getFirst();
				j = first.get(0).getSecond();
				for (int x : first.keySet()) {
					if (first.get(x).equals(new Pair(i + 1, j))) {
						key = x;
						break;
					}
				}
				if (key == 0) {
					throw new RuntimeException();
				}
				down.get(key).setFirst(i);
				down.get(key).setSecond(j);
				down.get(0).setFirst(i + 1);
				down.get(0).setSecond(j);

				if (!set.contains(down)) {
					Node<String> downy = new Node<>("down");
					downy.setParent(current);
					stack.add(downy);
					queue.add(down);
					set.add(down);
				//	System.out.println("down");
				}
			}
			if (possibleMoveUp(first)) {

				i = first.get(0).getFirst();
				j = first.get(0).getSecond();
				for (int x : first.keySet()) {
					if (first.get(x).equals(new Pair(i - 1, j))) {
						key = x;
						break;
					}
				}
				if (key == 0) {
					throw new RuntimeException();
				}
				up.get(key).setFirst(i);
				up.get(key).setSecond(j);
				up.get(0).setFirst(i - 1);
				up.get(0).setSecond(j);

				if (!set.contains(up)) {
					Node<String> uppy = new Node<>("up");
					uppy.setParent(current);
					stack.add(uppy);
					queue.add(up);
					set.add(up);
				//	System.out.println("up");
				}
			}

		}
		while(current.getParent()!=null) {
			System.out.println(current.getParent().getData());
			current=current.getParent();
		}
		
		
		
	}

	public static <T> void print(T[][] matrix) {

		int n = matrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {

		SlidingBlocks a = new SlidingBlocks();

		print(a.matrix);

		print(a.finishedMatrix);

		// System.out.println(SlidingBlocks.set);
		System.out.println(a.currentMatrix);

		System.out.println(a.originalMatrix);

		// System.out.println(a.heuristics(a.currentMatrix,
		// SlidingBlocks.originalMatrix));

		a.generating();

	}

}
