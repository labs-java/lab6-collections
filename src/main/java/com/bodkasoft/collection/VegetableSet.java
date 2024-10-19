package com.bodkasoft.collection;

import com.bodkasoft.ingredient.Ingredient;

import java.util.*;

/**
 * A custom implementation of the Set interface that stores elements in a singly linked list.
 * The elements in the set are of a generic type T, which extends the Ingredient class.
 * It provides standard Set operations like add, remove, clear, contains, and size,
 * while maintaining uniqueness of the elements.
 *
 * @param <T> the type of elements in this set, which must extend Ingredient
 */
public class VegetableSet<T extends Ingredient> implements Set<T> {

    /**
     * The head node of the singly linked list representing the set.
     */
    private Node<T> head;

    /**
     *  The number of elements in the set.
     */
    private int size;

    /**
     * Default constructor that creates an empty VegetableSet.
     */
    public VegetableSet() {
        head = null;
        size = 0;
    }

    /**
     * Constructor that initializes the set with a single vegetable.
     *
     * @param vegetable the initial vegetable to add to the set
     */
    public VegetableSet(T vegetable){
        head = new Node<>(vegetable);
    }

    /**
     * Constructor that initializes the set with a collection of vegetables.
     *
     * @param vegetables the collection of vegetables to add to the set
     */
    public VegetableSet(Collection<T> vegetables){
        this();
        addAll(vegetables);
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the size of the set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if the set is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if the set contains the specified element.
     *
     * @param o the object to check for in the set
     * @return true if the object is present in the set, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        Iterator<T> it = iterator();
        if (o == null){
            while (it.hasNext()){
                if (it.next() == null){
                    return true;
                }
            }
        }else{
            while (it.hasNext()){
                if (o.equals(it.next())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this set.
     *
     * @return an Iterator object to iterate over the elements in the set
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            private Node<T> lastReturned = null;
            private boolean canRemove = false;

            @Override
            public boolean hasNext(){
                return current != null;
            }

            @Override
            public T next(){
                if (!hasNext()){
                    throw new NoSuchElementException("No more elements");
                }
                lastReturned = current;
                current = current.next;
                canRemove = true;
                return lastReturned.data;
            }

            @Override
            public void remove(){
                if(!canRemove){
                    throw new NoSuchElementException("No more elements");
                }

                if (lastReturned == head){
                    head = head.next;
                }else{
                    Node<T> temp = head;
                    while (temp != null && temp.next != lastReturned) {
                        temp = temp.next;
                    }
                    if (temp != null) {
                        temp.next = lastReturned.next;
                    }
                }
                lastReturned = null;
                canRemove = false;
                size--;
            }
        };
    }

    /**
     * Converts the elements of the set into an array.
     *
     * @return an array containing all the elements in the set
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;

        while (current != null){
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * Adds a vegetable to the set if it's not already present.
     *
     * @param vegetable the vegetable to add
     * @return true if the vegetable was added, false otherwise
     */
    @Override
    public boolean add(T vegetable) {
        if (contains(vegetable)){
            return false;
        }
        Node<T> newNode = new Node<>(vegetable);
        if (head == null){
            head = newNode;
        }else {
            Node<T> current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    /**
     * Removes the specified element from the set, if it is present.
     *
     * @param o the object to be removed
     * @return true if the object was removed, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        if (head == null){
            return false;
        }
        if (head.data.equals(o)){
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null){
            if (current.next.data.equals(o)){
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Adds all the elements in the specified collection to the set if they're not already present.
     *
     * @param vegetables the collection of vegetables to add
     * @return true if any of the elements were added, false otherwise
     */
    @Override
    public boolean addAll(Collection<? extends T> vegetables) {
        boolean modified = false;
        for (T vegetable : vegetables){
            if (add(vegetable)){
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all the elements from the set.
     */
    @Override
    public void clear() {
        Iterator<T> it = iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
    }

    /**
     * Removes all the elements from the set that are contained in the specified collection.
     *
     * @param c the collection containing elements to be removed
     * @return true if any elements were removed, false otherwise
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> it = iterator();
        while (it.hasNext()){
            if (c.contains(it.next())){
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements in the set that are contained in the specified collection.
     *
     * @param c the collection containing elements to retain
     * @return true if any elements were removed, false otherwise
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Returns true if the set contains all the elements in the specified collection.
     *
     * @param c the collection to check for containment
     * @return true if the set contains all the elements, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c){
            if (!contains(o)){
                return false;
            }
        }
        return true;
    }

    /**
     * Converts the elements of the set into an array of the specified type.
     *
     * @param a the array into which the elements of the set are to be stored, if it is large enough
     * @param <E> the runtime type of the array to contain the set elements
     * @return an array containing all the elements in the set
     */
    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size){
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        Object[] result = a;
        int index = 0;
        Node<T> current = head;
        while (current != null){
            result[index++] = current.data;
            current = current.next;
        }
        if (a.length > size){
            a[size] = null;
        }
        return a;
    }
}
