//package SkipList;

/** @author Vaishakhi Kulkarni
 *  Net Id:vpk140230
 *  Implement the skip list data structure
 *  Do not use data structures from Java's library.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import SkipNode;
import PreviousData;

//Driver program and the skeleton for the skip list implementation.
public class SkipListImplfinal<T extends Comparable<? super T>> implements
		SkipList<T> {

	// Considered fix size for max level
	public static int maxlvl = 13;
	// Create head as start part for skipList and tail is used as end point in
	// skipList.
	public SkipNode<T> head;
	public SkipNode<T> tail;

	public static int size = 0; // Initially size is zero

	// SkipList Constructor
	// Create new SkipNode as head and tail and then connect head with tail in
	// all the levels
	public SkipListImplfinal() {
		head = new SkipNode<T>(null, maxlvl);
		tail = new SkipNode<T>(null, maxlvl);
		for (int i = 0; i < maxlvl; i++) { // Connect all head with tail
			head.next[i] = tail;
		}
	}

	public static void main(String[] args) {

		Scanner sc = null;
		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		String operation = "";
		long operand = 0;
		int modValue = 997;
		long result = 0;
		Long returnValue = null;
		SkipListImplfinal<Long> skipList = new SkipListImplfinal<Long>();
		// Initialize the timer
		long startTime = System.currentTimeMillis();

		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Add":
				operand = sc.nextLong();
				skipList.add(operand);
				result = (result + 1) % modValue;
				break;
			case "Ceiling": {
				operand = sc.nextLong();
				returnValue = skipList.ceiling(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "FindIndex": { // Find Index is not implemented
				operand = sc.nextLong();
				returnValue = skipList.ceiling(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}

			case "First": {
				returnValue = skipList.first();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Last": {
				returnValue = skipList.last();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Floor": {
				operand = sc.nextLong();
				returnValue = skipList.floor(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Contains": {
				operand = sc.nextLong();
				if (skipList.contains(operand))
					result = (result + 1) % modValue;
				break;
			}
			case "Remove":
				operand = sc.nextLong();
				if (skipList.remove(operand))
					result = (result + 1) % modValue;

				break;

			}
		}

		// End Time
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println(" Result" + result + " Time: " + elapsedTime);

	}

	// Helper Function to find the reference and return path to each node
	public PreviousData<T> find(T element) {

		SkipNode<T> p = new SkipNode<T>();
		p = head;
		PreviousData<T> prev = new PreviousData<T>(null, maxlvl);

		for (int level = maxlvl - 1; level >= 0; level--) {
			int cmp = 0;
			if (p.next[level] != tail)
				cmp = p.next[level].data.compareTo(element); 
			while (p.next[level] != tail && cmp < 0) {
				p = p.next[level];
				if (p.next[level] != tail) {
					cmp = p.next[level].data.compareTo(element);
				}
			}
			prev.prevs[level] = p;
		}
		if (p.next[0] != tail) {

			if (p.next[0].data.compareTo(element) == 0) { // Element present in the list
				prev.data = p.next[0];
				return (prev);
			} else {//Element not present in SkipList then return null
				prev.data = null;
				return (prev);
			}

		}
		return prev;
	}

	// Randomly choice levels in SkipList
	public int randomLevel() {
		int level = 1;
		while (level < maxlvl) {
			Random rand = new Random();
			int b = rand.nextInt(2);
			if (b == 0)
				break;
			else
				level++;
		}
		return level;
	}

	// --------------------Override methods----------------------------------
	@Override
	// To add new element in the SkipList
	public void add(T x) {
		int l = randomLevel();

		try {
			PreviousData<T> p = find(x);
			if (p.data == null) {
				SkipNode<T> current = new SkipNode<T>(x, l);
				for (int i = l - 1; i >= 0; i--) {
					current.next[i] = p.prevs[i].next[i];
					p.prevs[i].next[i] = current;
				}
				size = size + 1;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
		}
	}

	@Override
	// To find out ceiling element in SkipList
	public T ceiling(T x) {
		@SuppressWarnings("unchecked")
		SkipNode<T> current[] = new SkipNode[maxlvl];
		PreviousData<T> ceiling = find(x);

		if (x.equals(ceiling.prevs[0].next[0].data)) { 
			for (int i = 0; i < maxlvl - 1; i++) {
				if (x.equals(ceiling.prevs[i].next[i].data)) {
					current[i] = ceiling.prevs[i].next[i];
				}
			}
			return current[0].next[0].data;
		} else
			return null;
	}

	@Override
	// Verify whether the element is present in the skiplist
	public boolean contains(T x) {

		// Call find function to check whether the element is present in the
		// skipList
		PreviousData<T> check = find(x);
		if (check.data != null) { // If check has value other than null that
									// means that element is present in SkipList
			return true;
		} else {// If check has reference as null that means element is not
				// present
			return false;
		}
	}

	@Override
	public T findIndex(int n) {
		return null;
	}

	@Override
	// To find first element in the skipList
	public T first() {
		Boolean check = isEmpty();
		if (check) { // Check whether the SkipList is empty.If it is it should
						// exception
			try {
				throw new Exception("SkipList is Empty");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {// If SkipList is not empty then display the first smallest
				// element from the list which is at lowest level
			return head.next[0].data;
		}
		return null;
	}

	@Override
	// To find out the floor element in SKipList
	public T floor(T x) {
		PreviousData<T> floor = find(x);
		if (x.equals(floor.prevs[0].next[0].data)) {
			return floor.prevs[0].data;
		} else
			return null;
	}

	@Override
	// To check whether SkipList is empty
	public boolean isEmpty() {
		if (head.next[0] == tail) // If at lowest level head is connected to
									// tail then it is SkipList is empty
			return true;
		else
			return false;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	// To find last element in the SkipList
	public T last() {
		Boolean check = isEmpty();
		if (check) { // Check whether the SkipList is empty.If it is it should
						// exception
			try {
				throw new Exception("SkipList is Empty");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			SkipNode<T> last = head;
			for (int i = maxlvl - 1; i >= 0; i--) {
				while (last.next[i] != tail) {
					last = last.next[i];
				}
			}
			return last.data;
		}

		return null;
	}

	@Override
	public void rebuild() {

	}

	@SuppressWarnings("unused")
	@Override
	// To remove element from SkipList
	public boolean remove(T x) {
		PreviousData<T> p = find(x);
		if (p.data == null) { // Element not present in the SkipList
			return false;
		} else {	// Element present in the SkipList
			size--;
			int i = 0;
			for (i = 0; i < maxlvl; i++) {
				if (p.prevs[i].next[i] == p.data) {
					p.prevs[i].next[i] = p.data.next[i];
					return true;
				} else {
					break;
				}

			}
			return true;
		}
	}

	@Override
	// To find Size of SkipList
	public int size() {
		return size;
	}

}
