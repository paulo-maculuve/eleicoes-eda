package eleicoes.fila;

public class Node {
	Object item;
	Node next;

//constructor
	public Node(Object anItem) {
		item = anItem;
		next = null;
	}

//métodos de acesso
	public Object getItem() {
		return item;
	}

	public void setItem(Object anItem) {
		item = anItem;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node theNext) {
		next = theNext;
	}
}
