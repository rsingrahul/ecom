package com.tariq.backend;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new App().getPath();
    }
    public void getPath(){
    	try {
			String path = URLDecoder.decode(this.getClass().getClassLoader().getResource("").getPath(), "UTF-8");//.split("/WEB-INF/")[0];
			System.out.println("path path path :::"+path);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
}
