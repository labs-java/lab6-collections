package com.bodkasoft.collection;

/**
 * A generic Node class used in singly linked lists. Each node contains a reference to the data
 * of type T and a reference to the next node in the list.
 *
 * @param <T> the type of the data stored in this node
 */
public class Node<T> {

    /**
     *  The data element stored in this node.
     */
    public T data;

    /**
     *  The reference to the next node in the list, or null if this is the last node.
     */
    public Node<T> next;

    /**
     * Constructs a new node with the given data and sets the next node reference to null.
     *
     * @param data the data to be stored in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
