/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author aalnawai
 */
public class SaveOpenDialog {

    private String Excel_FilterName = "Microsoft Excel 97-2003";
    private String XML_FilterName = "XML Document";
    private String Direct_FilterName = "Direct File";
    private String Excel_Extension = ".XLSX";
    private String XML_Extension = ".XML";
    private String Direct_Extension = ".DAT";
    private JFileChooser SaveDialog = null;

    public enum FilterType {

        XML, Excel, Direct
    }

    public enum DialogType {

        SAVE_DIALOG, OPEN_DIALOG
    }

    public enum PathType {

        FILES_ONLY, FILES_AND_DIRECTORIES, DIRECTORIES_ONLY
    }

    public SaveOpenDialog(String Title, String DefaultPath, DialogType dialogType, PathType pathType) {
        SaveDialog = new JFileChooser(DefaultPath);
        int SaveOpen = (dialogType == DialogType.OPEN_DIALOG) ? JFileChooser.OPEN_DIALOG : JFileChooser.SAVE_DIALOG;
        int File_Directory_Both = (pathType == PathType.FILES_ONLY) ? JFileChooser.FILES_ONLY : ((pathType == PathType.DIRECTORIES_ONLY) ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_AND_DIRECTORIES);
        SaveDialog.setDialogType(SaveOpen);
        SaveDialog.setFileSelectionMode(File_Directory_Both);
        SaveDialog.setDialogTitle(Title);
        SaveDialog.removeChoosableFileFilter(SaveDialog.getFileFilter());
    }

    private void setFilter(final String FilterName, final String FilterExtension) {
        SaveDialog.addChoosableFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return FilterName;
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getPath().toUpperCase().endsWith(FilterExtension.replace(".", ""));
            }
        });
    }

    public void RegisterFilter(FilterType filterType) {
        if (filterType == FilterType.Direct) {
            setFilter(Direct_FilterName, Direct_Extension);
        } else if (filterType == FilterType.XML) {
            setFilter(XML_FilterName, XML_Extension);
        } else if (filterType == FilterType.Excel) {
            setFilter(Excel_FilterName, Excel_Extension);
        }

    }

    public FilePath getFilePath() {

        FilePath FilePath = new FilePath();
        int returnVal = SaveDialog.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            String fileName = SaveDialog.getSelectedFile().getPath();
            boolean _Excel = fileName.toUpperCase().endsWith(Excel_Extension);
            boolean _XML = fileName.toUpperCase().endsWith(XML_Extension);
            boolean _Direct = fileName.toUpperCase().endsWith(Direct_Extension);
            if (_Excel) {
                File file = new File(fileName);
                if (file.exists()) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                            "File already exists.\n Do you want to Overwrite it?",
                            "File Already Exists",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
                        FilePath.DocumentType = FilterType.Excel;
                        FilePath.Path = fileName;
                        return FilePath;
                    } else {
                        return null;
                    }
                }
            } else if (_XML) {
                File file = new File(fileName);
                if (file.exists()) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                            "File already exists.\n Do you want to Overwrite it?",
                            "File Already Exists",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
                        FilePath.DocumentType = FilterType.XML;
                        FilePath.Path = fileName;
                        return FilePath;
                    } else {
                        return null;
                    }
                }
            } else if (_Direct) {
                File file = new File(fileName);
                if (file.exists()) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                            "File already exists.\n Do you want to Overwrite it?",
                            "File Already Exists",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
                        FilePath.DocumentType = FilterType.Direct;
                        FilePath.Path = fileName;
                        return FilePath;
                    } else {
                        return null;
                    }
                }
            } else {
                if (SaveDialog.getFileFilter().getDescription().equals(Excel_FilterName)) {
                 //   fileName += Excel_Extension;
                    File file = new File(fileName);
                    FilePath.DocumentType = FilterType.Excel;
                    FilePath.Path = fileName;
                    if (file.exists() && !file.isDirectory()) {
                        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                                "File already exists.\n Do you want to Overwrite it?",
                                "File Already Exists",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {

                            return FilePath;
                        } else {
                            return null;
                        }
                    }
                } else if (SaveDialog.getFileFilter().getDescription().equals(XML_FilterName)) {
                  //  fileName += XML_Extension;
                    File file = new File(fileName);
                    FilePath.DocumentType = FilterType.XML;
                    FilePath.Path = fileName;
                    if (file.exists() && !file.isDirectory()) {
                        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                                "File already exists.\n Do you want to Overwrite it?",
                                "File Already Exists",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {

                            return FilePath;
                        } else {
                            return null;
                        }
                    }
                } else if (SaveDialog.getFileFilter().getDescription().equals(Direct_FilterName)) {
                  //  fileName += Direct_Extension;
                    File file = new File(fileName);
                    FilePath.DocumentType = FilterType.Direct;
                    FilePath.Path = fileName;
                    if (file.exists() && !file.isDirectory()) {
                        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                                "File already exists.\n Do you want to Overwrite it?",
                                "File Already Exists",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {

                            return FilePath;
                        } else {
                            return null;
                        }
                    }
                }
                return FilePath;
            }
        }
        return null;
    }
}
