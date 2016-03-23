/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.Filters;

import java.util.ArrayList;

/**
 *
 * @author aalnawai
 */
public class Filter {
    ArrayList Filters = new ArrayList();
    public Filter() {
    }
    public void addNewFilter(Filter newFilter){
        Filters.add(newFilter);
    }
    public ArrayList getFilters(){
        return Filters;
    }
}
