package main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import Connection.Connection;

public class Main{

	public static void main(String[] args) throws ParserConfigurationException, FileNotFoundException {

		//create the xml document object
		Document xmlDoc = xmlDocInitializer();
		
		//input your opc ua xml data there, including number of items, server url, name, etc.
		xmlDoc = nodesBuilder(xmlDoc);
		
		//generate the upc ua xml file at current location
		output(xmlDoc);
	}
	
	public static Document xmlDocInitializer() throws ParserConfigurationException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document xmlDoc = docBuilder.newDocument();
		xmlDoc.setStrictErrorChecking(false);
		return xmlDoc;
	}

	public static Document nodesBuilder(Document xmlDoc) throws FileNotFoundException {

		Element OpcServerConfig = xmlDoc.createElement("OpcServerConfig");
		Element DxConnectionConfig = xmlDoc.createElement("DxConnectionConfig");
		Element General = xmlDoc.createElement("General");
		Element Tick = xmlDoc.createElement("Tick");
		Element Length = xmlDoc.createElement("Length");
		Text text = xmlDoc.createTextNode("");
		Element Synchronization = xmlDoc.createElement("Synchronization");
		Element ServerConnections = xmlDoc.createElement("ServerConnections");

		OpcServerConfig.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema");
		OpcServerConfig.setAttribute("xmlns", "http://www.vtt.fi/OPCUA/connectionconfig.xsd");

		text = xmlDoc.createTextNode("0.1");
		Length.appendChild(text);
		Tick.appendChild(Length);
		DxConnectionConfig.appendChild(Tick);
		text = xmlDoc.createTextNode("false");
		Synchronization.appendChild(text);
		DxConnectionConfig.appendChild(Synchronization);

		//input the number of Dxconnection items 
		int num = 500;
		Connection connection = new Connection();
		connection.setTagName("Connection");
		connection.setEnabled("true");
		connection.setSubscribeeUsesWrite("false");
		connection.getSubscriber().setUrl("opc.tcp://L19-0856:4842");
		connection.getSubscriber().setName("Signals Server");
		connection.getSubscribee().setUrl("opc.tcp://L19-0856:4843");
		connection.getSubscribee().setName("LoopbackSetpoints Server");
		connection.getDxConnection().setDxItemId("DX.DXConnectionsRoot.LoopbackSetpoints Server.toSignals");
		connection.getDxConnection().setDxItemName("from LoopbackSetpoints.SP*");
		connection.getDxConnection().setTargetItemId("TYPES!A!ANALOG_SIGNAL!XAI*.ANALOG_VALUE");
		connection.getDxConnection().setSourceItemId("TYPES!S!SET_POINT!SP*.SP_VALUE");
		Element Connections = connection.builder(xmlDoc, num);
		ServerConnections.appendChild(Connections);

		connection.setTagName("Connection");
		connection.setEnabled("true");
		connection.setSubscribeeUsesWrite("true");
		connection.getSubscriber().setUrl("opc.tcp://L19-0856:4843");
		connection.getSubscriber().setName("LoopbackSetpoints Server");
		connection.getSubscribee().setUrl("opc.tcp://L19-0856:4842");
		connection.getSubscribee().setName("Signals Server");
		connection.getDxConnection().setDxItemId("DX.DXConnectionsRoot.Signals Server.toLoopbackSetpoints");
		connection.getDxConnection().setDxItemName("from Signals.XAO*");
		connection.getDxConnection().setTargetItemId("TYPES!S!SET_POINT!SP*.SP_VALUE");
		connection.getDxConnection().setSourceItemId("TYPES!A!ANALOG_SIGNAL!XAO*.ANALOG_VALUE");
		Connections = connection.builder(xmlDoc, num);
		ServerConnections.appendChild(Connections);

		DxConnectionConfig.appendChild(ServerConnections);
		OpcServerConfig.appendChild(DxConnectionConfig);
		OpcServerConfig.appendChild(General);

		xmlDoc.appendChild(OpcServerConfig);
		return xmlDoc;
	}

	public static void output(Document xmlDoc) throws FileNotFoundException {

		// set outputFormat
		OutputFormat outFormat = new OutputFormat(xmlDoc);
		outFormat.setIndenting(true);

		// declare the file
		File xmlFile = new File("ConnectionConfig.xml");
		// declare the fileOutputStream
		FileOutputStream outStream = new FileOutputStream(xmlFile);

		// XMLSerializer to serialize the xml data with
		XMLSerializer serializer = new XMLSerializer(outStream, outFormat);
		// the specified outputformat
		try {
			serializer.serialize(xmlDoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
