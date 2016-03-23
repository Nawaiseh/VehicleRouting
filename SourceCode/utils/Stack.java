/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.ArrayList;

/**
 *
 * @author TRL@SMU
 */
public class Stack<E> extends ArrayList<E>{

    public Stack(){
        super();
    }

    public synchronized void Push(E Item){
        add(Item);
    }
    public synchronized E Pop(){
        E item = get(size()-1);
        remove(item);
        return item;
    }
}
