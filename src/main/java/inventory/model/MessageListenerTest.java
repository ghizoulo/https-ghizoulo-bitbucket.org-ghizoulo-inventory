/*
 * cette classe permet de lire les tags détectés par l'antenne
 */
package inventory.model;

import java.util.ArrayList;
import java.util.List;

import com.alien.enterpriseRFID.notify.Message;
import com.alien.enterpriseRFID.notify.MessageListener;
import com.alien.enterpriseRFID.notify.MessageListenerService;
import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.tags.Tag;

import inventory.presentation.TransactionBean;

public class MessageListenerTest implements MessageListener,Runnable  {

	private List<String> listTag;
	private long runTime;
	private long startTime;
/**
 * Constructor.
 */
public MessageListenerTest() throws Exception {
	
	 	AlienClass1Reader reader = new AlienClass1Reader();
		// Set up the message listener service
		MessageListenerService service = new MessageListenerService(4000);
		service.setMessageListener(this);
		service.startService();
		System.out.println("Message Listener has Started");

		// Instantiate a new reader object, and open a connection to it on COM1
		//AlienClass1Reader reader = new AlienClass1Reader("COM1");
	
		reader.setConnection("127.0.0.1", 23);
		reader.setUsername("alien");
		reader.setPassword("password");
		
	  	reader.open();
	
	  System.out.println("Configuring Reader");
	
	  // Set up Notification.
	  // Use this host's IPAddress, and the port number that the service is listening on.
	  // getHostAddress() may find a wrong (wireless) Ethernet interface, so you may
	  // need to substitute your computers IP address manually.
	
	  reader.setNotifyAddress("127.0.0.1", service.getListenerPort());
	  reader.setNotifyFormat(AlienClass1Reader.XML_FORMAT); // Make sure service can decode it.
	  reader.setNotifyTrigger("TrueFalse"); // Notify whether there's a tag or not
	  reader.setNotifyMode(AlienClass1Reader.ON);
	
	  // Set up AutoMode
	  reader.autoModeReset();
	  reader.setAutoStopTimer(1000); // Read for 1 second
	  reader.setAutoMode(AlienClass1Reader.ON);
	
	  // Close the connection and spin while messages arrive
	  reader.close();
	
	  setRunTime(10000);
	  setStartTime(System.currentTimeMillis());
	  listTag = new ArrayList<String>();
	  do {
	
	    Thread.sleep(1000);
	
	  } while(service.isRunning() && (TransactionBean.isArretTest()));
	  
	  TransactionBean.setListPorductTags(getListTag()); 
	  
	  // Reconnect to the reader and turn off AutoMode and TagStreamMode.
	  System.out.println("\nResetting Reader");
	  reader.open();
	  reader.autoModeReset();
	  reader.setNotifyMode(AlienClass1Reader.OFF);
	  reader.close();
	}
	
	
	/**
	 * A single Message has been received from a Reader.
	 *
	 * @param message the notification message received from the reader
	 */
	public void messageReceived(Message message){
	
	  System.out.println("\nMessage Received:");
	    
	  if (message.getTagCount() == 0) {
	    System.out.println("(No Tags)");
	  } else {
	    for ( int i = 0; i < message.getTagCount(); i++) {
	      Tag tag = message.getTag(i);
	      System.out.println(tag.toLongString());
	      try {
	    	  if(listTag.contains(tag.getTagID())){
		    	  System.out.println("Ce tag existe déja! ");
		      }
		      else{
		    	  listTag.add(tag.getTagID());
		    	  System.out.println("ce tag a été bien ajouté ");
		      }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	  }
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public List<String> getListTag() {
		return listTag;
	}

	public void setListTag(List<String> listTag) {
		this.listTag = listTag;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}
	
	} // end of class MessageListenerTest