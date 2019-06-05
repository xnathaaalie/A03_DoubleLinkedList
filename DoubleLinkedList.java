package A03_DoubleLinkedList_fertig;

public class DoubleLinkedList<T>
{

	private Node<T> first,last, current; 
	
    /**
     * Einf�gen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {
    	Node<T> node = new Node(a); 
    	
    	if(first == null) //first element
    	{
    		first = node;
    		last = node; 
    	}
    	else //at least one element is already in list
    	{
	    	last.setNext(node);
	    	node.setPrevious(last);
	    	last = node; 
    	}
    }

    /**
     * Internen Zeiger f�r next() zur�cksetzen
     */
    public void reset() {
    	current = first; 
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
    	current = last; 
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
    	return first; 
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
    	return last; 
    }
    
    /**
     * Gibt aktuelle <T> zur�ck und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {

    	if(current == null)
    		return null; 
    	
    	Node<T> toReturn = current; 
    	current = current.getNext(); 
    	return toReturn.getData(); 
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {

    	if(current == null)
    		return null; 
    	
    	Node<T> toReturn = current; 
    	current = current.getPrevious(); 
    	return toReturn.getData(); 
    }
    
    /**
     * Current-Pointer auf n�chste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
    	if(current == null) //do nothing if current is not set
    		return; 
    	
    	current = current.getNext(); 
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

    	if(current == null) //do nothing if current is not set
    		return; 
    	
    	current = current.getPrevious(); 
    	
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu �ndern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

    	if(current == null)
    		throw new CurrentNotSetException(); 
    	
    	return current.getData(); 
    }

    /**
     * Gibt <T> an bestimmter Position zur�ck
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        Node<T> node = first; 
        for(int i = 0; i < pos; i++)
        {
        	if(node == null)
        		return null; 
        	node = node.getNext(); 
        }
        
        return node.getData(); 
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {
    	
    	if(first == null) //no element in list
    		return; 

    	Node<T> nodeToRemove = first; 
    	
    	for(int i = 1; i < pos; i++)
    	{
    		if(nodeToRemove == null) //list is smaller than position
    			return; 
    		
    		nodeToRemove = nodeToRemove.getNext(); 
    	}
    	
    	
    	Node<T> previous = nodeToRemove.getPrevious(); 
    	Node<T> next = nodeToRemove.getNext(); 
    	
    	if(next != null)
    		next.setPrevious(previous);
    	if(previous != null)
    		previous.setNext(next);
    	
    	if(nodeToRemove == current)
    		current = null; 
    	if(nodeToRemove == first) //removed item was first element. set new first to the next
    		first = next; 
    	if(nodeToRemove == last) //removed item was last element. set new first to the previous
    		last = previous; 
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
    	if(current == null)
    		throw new CurrentNotSetException(); 
    	
    	Node<T> previous = current.getPrevious();
    	Node<T> next = current.getNext();
    	
    	if(next == null)
    	{
    		if(previous != null) //theres a previous element in the list
    		{
    			if(last == current) //removed item was the last one
    				last = previous; 
    			
    			current = previous; 
        		current.setNext(null); //the next element of now current was removed
    		}
    		else
    		{
    			//no elements in list. set all pointers to null
    			current = null; 
    			last = null; 
    			first = null; 
    		}
    	}
    	else
    	{
    		if(current == first) //removed element was the first one
    			first = next; 
    		
    		current = next; 
    		if(previous != null)
    		{
	    		current.setPrevious(previous);
	    		previous.setNext(current);
    		}
    		else
    		{
    			current.setPrevious(null);
    		}
    	}
    }
    
    /**
     * Die Methode f�gt die �bergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingef�gte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

    	if(current == null)
    		throw new CurrentNotSetException(); 
    	
    	Node<T> node = new Node(a); 
    	Node<T> oldAfterCurrent = current.getNext(); 
    	
    	if(oldAfterCurrent == null)
    	{
    		current.setNext(node);
    		node.setPrevious(current);
    		current = node; 
    	}
    	else
    	{
    		current.setNext(node);
    		node.setNext(oldAfterCurrent);
    		oldAfterCurrent.setPrevious(node);
    		node.setPrevious(current);
    		current = node; 
    	}
    }
}
