package com.bodkasoft.collection;

import com.bodkasoft.ingredient.Ingredient;
import com.bodkasoft.ingredient.Vegetable;

import java.util.*;

public class VegetableSet<T extends Ingredient> implements Set<T> {

    private Node<T> head;
    private int size;

    public VegetableSet() {
        head = null;
        size = 0;
    }

    public VegetableSet(T vegetable){
        head = new Node<>(vegetable);
    }

    public VegetableSet(Collection<T> vegetables){
        this();
        addAll(vegetables);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext(){
                return current != null;
            }

            @Override
            public T next(){
                if (!hasNext()){
                    throw new NoSuchElementException("No more elements");
                }
                T vegetable = current.data;
                current = current.next;
                return vegetable;
            }
        };
    }

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
            if (current.data.equals(o)){
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

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

    @Override
    public void clear() {
        Iterator<T> it = iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
    }

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

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c){
            if (!contains(o)){
                return false;
            }
        }
        return true;
    }

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
