package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by sikonder on 07.08.17.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Cloneable, Serializable, Set<E> {

    private final static Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        map = new HashMap<>((int) Math.ceil(Math.max(16, collection.size() / .75f)));
        addAll(collection);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e,PRESENT);
    }


    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.keySet().remove(o);
    }

    @Override
    public Object clone() {
        AmigoSet<E> amigoSet = new AmigoSet<>();

        try {
            
            amigoSet.addAll(this);
            amigoSet.map.putAll((Map) this.map.clone());
        }
        catch (Exception e){
            throw new InternalError();
        }

        return amigoSet;
    }


    private void writeObject(ObjectOutputStream s) throws IOException
    {
        s.defaultWriteObject();

        s.writeInt(map.keySet().size());
        for(E e:map.keySet()){
            s.writeObject(e);
        }
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
    }

    private void readObject(ObjectInputStream ois)
    {
        try
        {
            ois.defaultReadObject();

            Set<E> set = new HashSet<>();
            int size = (int) ois.readInt();
            for (int i = 0; i < size; i++)
            {
                set.add((E) ois.readObject());
            }

            int capacity = (int) ois.readInt();
            float loadFactor = (float) ois.readFloat();
            map = new HashMap<>(capacity, loadFactor);
            for (E elem : set)
            {
                map.put(elem, PRESENT);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    


}
