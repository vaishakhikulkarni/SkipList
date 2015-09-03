//package SkipList;

//Vaishakhi Kulkarni
//vpk140230

//Create the Node which has data and next is used for reference
public class SkipNode<T> {

	T data;
	SkipNode<T>[] next = null;
	int level;

	// Default Constructor
	public SkipNode() {
		data = null;
		next = null;
	}

	@SuppressWarnings("unchecked")
	// Parameterized Constructor and creates new node as per level specified
	public SkipNode(T value, int level) {
		this.data = value;
		this.next = (SkipNode<T>[]) new SkipNode[level];

	}

}
