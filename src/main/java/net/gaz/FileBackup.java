package net.gaz;


import java.io.*;
import java.util.Map;

public class FileBackup {

    public static void main(String[] args) {
        final String srcEnvName = "SOURCE_FILE";
        final String dstEnvName = "DESTINATION_FILE";
        String SRC = null;
        String DST = null;

        // check arguments for source and destination
        // either from command line or from environment variables
        if (args.length == 2) {
             SRC = args[0];
             DST = args[1];

        } else  {
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                if (envName.equalsIgnoreCase(srcEnvName)) { SRC = env.get(envName); }
                if (envName.equalsIgnoreCase(dstEnvName)) { DST = env.get(envName); }
            }
        }

        if(SRC == null || DST == null){
            System.out.println("Usage: java -jar FileBackup <source> <destination>");
            System.exit(1);
        }

        copy(SRC, DST);
    }

    private static void copy(String src, String dst){
        try(BufferedInputStream bi = new BufferedInputStream(new FileInputStream(src));
            BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(dst))) {

            while(bi.available() > 0) {
                bo.write(bi.read());
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
