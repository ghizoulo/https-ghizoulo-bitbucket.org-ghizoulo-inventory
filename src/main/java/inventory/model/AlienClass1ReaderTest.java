/*
 * cette classe permet de détecter un tag et retourne les info sur le produit
 * author:  Ghizlane LOTFI
 * 08/2015
 */
package inventory.model;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionException;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionRefusedException;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.reader.AlienReaderNotValidException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;
import com.alien.enterpriseRFID.tags.Tag;

public class AlienClass1ReaderTest {

	private static AlienClass1Reader reader = new AlienClass1Reader();
	private static String id = "0";
	
	public static AlienClass1Reader getReader() {
		return reader;
	}

	public static void setReader(AlienClass1Reader reader) {
		AlienClass1ReaderTest.reader = reader;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		AlienClass1ReaderTest.id = id;
	}

	public static String getIDTag(){
		
		reader.setConnection("127.0.0.1", 23);
		reader.setUsername("alien");
		reader.setPassword("password");
		 

		  // Open a connection to the reader
		try {
			reader.open();
		} catch (AlienReaderConnectionRefusedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AlienReaderNotValidException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AlienReaderTimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AlienReaderConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		  // Ask the reader to read tags and print them
		Tag tagLists[]=null;
		try {
			tagLists = reader.getTagList();
		} catch (AlienReaderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		Tag tagList=null;
//		try {
//			tagList = reader.getTag();
//			
//		} catch (AlienReaderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (tagLists == null) {
			System.out.println("No Tag Found");
		} 
		else if(tagLists.length==1){
		    System.out.println("Tag found:");
			Tag tag = tagLists[0];
			id=tag.getTagID();
		 	System.out.println("ID:" + tag.getTagID() +
		                         ", Discovered:" + tag.getDiscoverTime() +
		                         ", Last Seen:" + tag.getRenewTime() +
		                         ", Antenna:" + tag.getAntenna() +
		                         ", Reads:" + tag.getRenewCount()
		                         );
			
		}
		else{
			System.out.println("Tag(s) found:");
			id="1";
		    for (int i=0; i<tagLists.length; i++) {
		      Tag tag = tagLists[i];
		      System.out.println("ID:" + tag.getTagID() +
		                         ", Discovered:" + tag.getDiscoverTime() +
		                         ", Last Seen:" + tag.getRenewTime() +
		                         ", Antenna:" + tag.getAntenna() +
		                         ", Reads:" + tag.getRenewCount()
		                         );
		    }
		  }

		  // Close the connection
		reader.close();
		return id;
	}
}
