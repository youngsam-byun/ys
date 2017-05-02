package com.ys.app.util.factory;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YS on 2017-05-02.
 */

@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RowMapperFactory {

    private static final String PUBLIC_CLASS = "public class ";
    private static final String PRIVATE = "private ";
    private static final String path="C:\\YS\\Github\\ys\\src\\main\\java\\com\\ys\\app\\model\\Category.java";

    private static File f;
    private static BufferedReader br;
    private static FileReader fr;
    private static String  className;
    private static String  classVariableName;
    private static List<String> types;
    private static List<String> names;


    @BeforeClass
    public static void init() throws IOException {

        types=new ArrayList<>();
        names=new ArrayList<>();

        f = new File(path);
        fr = new FileReader(f);
        br = new BufferedReader(fr);

        String line=null;

        while((line=br.readLine())!=null){

            if(line.contains(PUBLIC_CLASS)){
                processClassName(line);

            }else if(line.contains(PRIVATE)){

                processTypesAndVariables(line);


            }else
                continue;

        }

    }

    private static void processTypesAndVariables(String line) {
        int s=line.indexOf(PRIVATE);
        s=s+PRIVATE.length();
        int e=line.indexOf(" ",s);
        String t=line.substring(s,e);

        int s2=e+1;
        int e2=line.indexOf(";",s2);
        String n=line.substring(s2,e2);

        types.add(t);
        names.add(n);
    }

    private static void processClassName(String line) {
        int s=line.indexOf(PUBLIC_CLASS);
        s=s+PUBLIC_CLASS.length();
        int e=line.indexOf("{",s);
        className=line.substring(s,e);

        String first=String.valueOf(className.toLowerCase().toCharArray()[0]);
        String rest=className.substring(1,className.length());
        classVariableName=first+rest;

    }

    @Test
    public  void A_displayClassNameAndClassVariableName(){
        System.out.println(className);
        System.out.println(classVariableName);

    }

    @Test
    public  void B_displayTypeAndVariables(){

        for(int i=0;i<types.size();i++){
            System.out.println(types.get(i)+" : " +names.get(i));
        }
    }

    @Test
    public  void C_createRowMapper(){
        StringBuilder sb = new StringBuilder();

        sb.append("mport org.springframework.jdbc.core.namedparam.MapSqlParameterSource;").append("\n")
                .append("import java.sql.ResultSet;").append("\n")
                .append("import java.sql.SQLException;").append("\n")
                .append(PUBLIC_CLASS)
                .append(className).append("RowMapper extends BaseRowMapper<").append(className).append("> {").append("\n")
                .append("@Override").append("\n")
                .append("public ").append(className).append(" ")
                .append("mapRow(Resultset rs,int rowNum) throws SQLException {").append("\n")
                .append(className).append(" ").append(classVariableName).append("= new ").append(className).append("();").append("\n")
                .append(classVariableName).append(".set")

                .append("}");


        ClassLoader classLoader = this.getClass().getClassLoader();





    }
    @Test
    public  void D_createRepository(){

    }

    @Test
    public  void E_createRepositoryImpl(){

    }

    @Test
    public  void F_createService(){

    }

    @Test
    public  void G_CreateServiceImpl(){

    }
}
