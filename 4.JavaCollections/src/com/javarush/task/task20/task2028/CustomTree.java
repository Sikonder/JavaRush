package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList implements Cloneable,Serializable{
    Entry<String> root;
    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        //System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        //System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    public boolean add(String s) {
        Queue<Entry<String>> queue = new ConcurrentLinkedQueue<>();
        if (root == null) {
            root = new Entry<>("");
            root.lineNumber = 0;
        }
        queue.add(root);
        int maxLine = 0;
        while (queue.size() != 0) {
            Entry<String> currentElement = queue.remove();
            if (currentElement.lineNumber > maxLine) {
                maxLine = currentElement.lineNumber;
            }
            if (currentElement.leftChild != null) {
                queue.add(currentElement.leftChild);
            }
            if (currentElement.rightChild != null) {
                queue.add(currentElement.rightChild);
            }
        }
        queue.add(root);
        while (queue.size() != 0) {
            Entry<String> currentElement = queue.remove();
            if (currentElement.lineNumber >= maxLine - 1) {
                Entry<String> entry = new Entry<>(s);
                entry.lineNumber = currentElement.lineNumber + 1;
                if (currentElement.leftChild == null) {
                    currentElement.leftChild = entry;
                    currentElement.checkChildren();
                    return true;
                } else if (currentElement.rightChild == null) {
                    currentElement.rightChild = entry;
                    currentElement.checkChildren();
                    return true;
                }
            }
            if (currentElement.leftChild != null) {
                queue.add(currentElement.leftChild);
            }
            if (currentElement.rightChild != null) {
                queue.add(currentElement.rightChild);
            }
        }
        return false;
    }
    @Override
    public boolean remove(Object o) {
        String s = (String) o;
        Queue<Entry<String>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        while (queue.size() != 0) {
            Entry<String> currentElement = queue.remove();
            if (currentElement.leftChild != null) {
                if (currentElement.leftChild.elementName.equals(s)) {
                    currentElement.leftChild = null;
                    currentElement.checkChildren();
                    return true;
                } else {
                    queue.add(currentElement.leftChild);
                }
            }
            if (currentElement.rightChild != null) {
                if (currentElement.rightChild.elementName.equals(s)) {
                    currentElement.rightChild = null;
                    currentElement.checkChildren();
                    return true;
                } else {
                    queue.add(currentElement.rightChild);
                }
            }
        }
        return false;
    }
    @Override
    public int size() {
        int size = 0;
        Queue<Entry<String>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        while (queue.size() != 0) {
            Entry<String> currentElement = queue.remove();
            if (currentElement.leftChild != null) {
                size++;
                queue.add(currentElement.leftChild);
            }
            if (currentElement.rightChild != null) {
                size++;
                queue.add(currentElement.rightChild);
            }
        }
        return size;
    }






    public String get(int index) {
        throw new UnsupportedOperationException();
        //return null;
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
        //return super.addAll(index, c);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
        //return super.subList(fromIndex, toIndex);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
        //super.removeRange(fromIndex, toIndex);
    }

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;


        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren=true;
            this.availableToAddRightChildren=true;
        }

        void checkChildren(){
            if(leftChild!=null){
                availableToAddLeftChildren=false;
            }
            if(rightChild!=null){
                availableToAddRightChildren=false;
            }
        }
        boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren||availableToAddRightChildren;
        }
    }


}
