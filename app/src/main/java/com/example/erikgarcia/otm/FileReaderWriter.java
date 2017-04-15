package com.example.erikgarcia.otm;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Maverick on 10/4/2016.
 */

public class FileReaderWriter {

    private Context context;

    public FileReaderWriter(Context context){
        this.context=context;
    }

    public String loginCheck(String dir, String user, String pass){

        String line = "";
        String temp = (user + ':' + pass);

        String root = Environment.getExternalStorageDirectory().toString();
        File fDir = new File(root + "/subgen/"+dir);
        fDir.mkdirs();
        File file = new File(fDir, "loginSheet.txt");

        try{
            if (file.exists()) {

                BufferedReader br = new BufferedReader(new FileReader(file));

                while((line = br.readLine()) != null){
                    if (line.contains(temp)) {
                        String temp2 = line;
                        String temp3[] = line.split(" ");
                        br.close();
                        return temp3[1];

                    }
                }
                br.close();

            }

        } catch (FileNotFoundException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            return "";
        } catch (IOException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            return "";
        }

        return null;
    }

    //this does login check then pulls filename matching
    public void accountDetailsObtain(String dir, String user, String pass) {
        String fileName = loginCheck(dir, user, pass);

        String root = Environment.getExternalStorageDirectory().toString();
        File fDir = new File(root + "/subgen/"+dir);
        fDir.mkdirs();

        File file = new File(fDir, fileName);

        if (fileName == null) {
            System.out.println("login does not exist");
            return;
        }

        if (fileName != null) {
            try {
                Scanner input;
                File dataFile = new File(fileName);
                input = new Scanner(new FileReader(file));
                String temp = ".txt";
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    if (line.contains(temp)) {
                        System.out.println(line);
                        //this is where you obtain all file names
                    }
                }
                input.close();
            } catch (IOException e){

            }
        }
    }

    public void accountDetailsAppend(String user, String pass, String fileNameAppend){}
    public void accountDetailsRemove(String user, String pass, String fileNameRemove){}
    public void searchAppend(String fileNameAppend){}
    public void searchRemove(String fileNameRemove){}


    // Check if External Storage is Readable/Writable
    public boolean isExternalStorageReadable(){

        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }

        return false;
    }
    public boolean isExternalStorageWritable(){

        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }

        return false;
    }
}
