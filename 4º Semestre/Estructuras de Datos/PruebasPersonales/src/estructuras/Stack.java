package estructuras;

import java.util.Collection;

public interface Stack<E> extends Collection<E> {
	
	E pull();
	
	void push(E element);
	
}
