//Vaishakhi Kulkarni
//vpk140230

//package SkipList;

//Class to store the previous reference 
public class PreviousData<T> {

	SkipNode<T> data = null;
	SkipNode<T>[] prevs = null;

	@SuppressWarnings("unchecked")
	// Parameterized Constructor and creates a new node as per the level
	// specified
	PreviousData(SkipNode<T> data, int level) {
		this.data = data;
		this.prevs = (SkipNode<T>[]) new SkipNode[level];
	}

}