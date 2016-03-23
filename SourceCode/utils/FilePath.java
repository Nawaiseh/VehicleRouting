/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import utils.SaveOpenDialog.FilterType;

/**
 *
 * @author aalnawai
 */
public class FilePath {

  
    public String Path = "";
    public FilterType DocumentType = FilterType.Direct;

    public FilePath(String newPath, FilterType newFileType) {
        Path = newPath;
        DocumentType = newFileType;
    }

    public FilePath() {
    }
}
