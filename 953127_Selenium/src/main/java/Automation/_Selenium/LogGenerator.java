package Automation._Selenium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LogGenerator {

	
public static synchronized String GenerateLog(String Method) throws IOException {
	String pattern = "dd MMMMM yyyy HH:mm:ss";
	SimpleDateFormat simpleDateFormat =
	        new SimpleDateFormat(pattern);

	String date = simpleDateFormat.format(new Date());
	System.out.println(date);	
	date = date.replace(" ", "_");
	date = date.replace(":", "_");
	
	String ReportName= Method+date;
	
	FileWriter fw = new FileWriter("Reports/"+ReportName+".html");
	
	// create the IO strem on that file
	BufferedWriter bw = new BufferedWriter(fw);

	bw.close();
	
	return "Reports/"+ReportName+".html";
	
	}

public static synchronized void UpdateLog(String ReportPath,String log) throws IOException {
	
	BufferedWriter out = new BufferedWriter(new FileWriter(ReportPath,true));
	out.append("<TD ALIGN=LEFT VALIGN=BOTTOM BGCOLOR=WHITE WIDTH=5%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><DIV ID=\"SCROLLBAR\">" + log + "</DIV></FONT></TD>" + "\n");
	out.flush();
	out.close();
	//out.write("\n");
}
	




public static void main(String...args) throws IOException { 
	String pattern = "dd MMMMM yyyy HH:mm:ss";
	SimpleDateFormat simpleDateFormat =
	        new SimpleDateFormat(pattern);

	String date = simpleDateFormat.format(new Date());
		
	date = date.replace(" ", "_");
	date = date.replace(":", "_");
	String dskf = "jkfj";
	date = date+dskf;
	System.out.println(date);

	Random rand = new Random(); //instance of random class
	int upperbound = 100;

	
	int generatedNumber = rand.nextInt(upperbound); 
	
FileWriter fw = new FileWriter("Reports/"+date+"_"+generatedNumber+".log");
	
	// create the IO strem on that file
	BufferedWriter out = new BufferedWriter(fw);

	out.append("<TR>" + "\n");
	out.append("<TD ALIGN=LEFT VALIGN=BOTTOM BGCOLOR=WHITE WIDTH=5%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><DIV ID=\"SCROLLBAR\">" + 1 + "</DIV></FONT></TD>" + "\n");
	out.append("<TD ALIGN=LEFT VALIGN=BOTTOM BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><DIV ID=\"SCROLLBAR\">" + "Descriprtion" + "</DIV></FONT></TD>" + "\n");
	out.append("<TD ALIGN=LEFT VALIGN=BOTTOM BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><DIV ID=\"SCROLLBAR\">" + "strExpected" + "</DIV></FONT></TD>" + "\n");
	out.append("<TD ALIGN=LEFT VALIGN=BOTTOM BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><DIV ID=\"SCROLLBAR\">" + "strActual" + "</DIV></FONT></TD>" + "\n");
				
	out.close();

	}
	
}
