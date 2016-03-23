/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author aalnawai
 */
public class Key implements Comparable {

    public Integer ID1 = -1;
    public Integer ID2 = -1;

    public Key(int newID1) {
        ID1 = newID1;
    }

    public Key(Integer newID1, Integer newID2) {
        ID1 = newID1;
        ID2 = newID2;
    }

    public Key() {
    }

    public boolean equals(Key Key) {
        if (ID2 == -1) {
            return (int) ID1 == (int) Key.ID1;
        } else {
            boolean R1 = (int) ID1 == (int) Key.ID1;
            boolean R2 = (int) ID2 == (int) Key.ID2;
            return R1 && R2;
        }
    }

    public int compareTo(Object KeyObject) {
        Key Key = (Key) KeyObject;
        int R1 = ID1.compareTo(Key.ID1);
        int R2 = ID2.compareTo(Key.ID2);
        return (R1 != 0) ? R1 : R2;
    }

    @Override
    public String toString() {
        if (ID2 == -1) {
            return String.format("%d", ID1);
        } else {
            return String.format("%d-%d", ID1, ID2);
        }
    }
}
