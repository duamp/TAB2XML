package store;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import utility.MusicXMLCreator;

public class Record {
	private int measureNumber;
	private MusicXMLCreator mxlc;
	private String notes;
	
	public Record() {
		/* 1. Parse music XML
		 * 2. Store basic attributes
		 */	
	}
	
	public void storeMeasureNumber() {
		//Scanner s = new Scanner(mxlc.getXmlString());
		//s.findInLine(Pattern.compile("<step>."));
	}
	
}