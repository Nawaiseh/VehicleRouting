package utils;

import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//====================================================================================
public class PrintFile extends OutputStreamWriter {
    public PrintFile(String filename) throws IOException {
        super(
                new BufferedOutputStream(
                new FileOutputStream(filename)), "8859_1");
    }
    public PrintFile(File file)
            throws IOException {
        this(file.getPath());
    }
    public void writeToFile(String xx) {
        try {
            this.write(xx + "\n");
            //this.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            //DSystem.DSystem.println(ex);
        }

    }
    public void writeToFileWithoutNextLine(String xx) {
        try {
            this.write(xx);
            //this.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
//====================================================================================

