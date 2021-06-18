package com.zipc.garden.rdf.compact;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.jena.dboe.base.file.Location;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.tdb2.DatabaseMgr;

public class Main {
    private static final int EXIT_CODE_SUCCESS = 0;

    private static final int EXIT_CODE_ERR_ARG_WRONG = 1;

    private static final int EXIT_CODE_ERR_DIL_NOT_EXISTS = 2;

    private static final int EXIT_CODE_ERR_UNKNOWN = 99;

    public static void main(String[] arg) {
        try {
            if (arg == null || arg.length != 1) {
                System.err.println("compactコマンドを実行するディレクトリを引数で指定してください。");
                System.err.println("eg) java -jar CompactDatabase.jar /home/fuseki/fuseki/run/databases/garden/");
                System.exit(EXIT_CODE_ERR_ARG_WRONG);
                return;
            }
            Path dirPath = Paths.get(arg[0]);
            if (!Files.exists(dirPath)) {
                System.err.println("指定されたディレクトリが存在しません。");
                System.exit(EXIT_CODE_ERR_DIL_NOT_EXISTS);
                return;
            }
            Location dir = Location.create(arg[0]);
            DatasetGraph dsg = DatabaseMgr.connectDatasetGraph(dir);
            DatabaseMgr.compact(dsg);
            System.exit(EXIT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(EXIT_CODE_ERR_UNKNOWN);
        }

    }
}
