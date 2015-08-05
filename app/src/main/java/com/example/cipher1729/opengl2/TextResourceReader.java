package com.example.cipher1729.opengl2;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextResourceReader {


    public static String readTextFileFromResource(Context context, int resourceId) throws IOException {
        StringBuilder body = new StringBuilder();
        InputStreamReader inputStream = new InputStreamReader(context.getResources().openRawResource(resourceId));
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        String nextLine;
        while( (nextLine = bufferedReader.readLine())!=null)
        {
            body.append(nextLine);
            body.append("\n");
        }
        return body.toString();
    }

}