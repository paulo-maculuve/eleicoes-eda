package eleicoes.fila;

public class LinkedQueue {
	public Node front;
	Node rear;
	int count;

//cria uma lista vazia
	public LinkedQueue() {
		front = null;
		rear = null;
		count = 0;
	}

//verifica se a fila está vazia
	public boolean empty() {
		return front == null;
	}

//insere novo elemento na fila
	public void enqueue(Object item) {
		Node newNode = new Node(item);
		if (rear == null) {
			front = newNode;
			rear = newNode;
		} else {
			rear.setNext(newNode);
			rear = newNode;
		}
		count++;
	}

	// Remover o elemento que está na frente da fila.
	public Object dequeue() {
		if (front == null)
			return null;
		else {
			Node temp = front;
			front = front.getNext();
			if (front == null)
				rear = null;
			count--;
			return temp.getItem();
		}
	}

	// Devolver o tamanho da fila
	public int getSize() {
		return count;
	}

	public void print() {
		Node temp = front;
		do {
			System.out.println(temp.getItem());
			temp = temp.getNext();
		} while (temp != null);
	}

}
