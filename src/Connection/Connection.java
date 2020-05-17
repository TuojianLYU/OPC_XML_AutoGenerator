package Connection;

import java.io.FileNotFoundException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import DxConnection.DxConnection;
import ItemConnections.ItemConnections;
import ServerConnections.ServerConnections;
import Subscribee.Subscribee;
import Subscriber.Subscriber;

public class Connection {

	static String tagName = "";
	static String Enabled = "";
	static String SubscribeeUsesWrite = "";

	static DxConnection dxConnection = new DxConnection();
	static ItemConnections itemConnections = new ItemConnections();
	static Subscribee subscribee = new Subscribee();
	static Subscriber subscriber = new Subscriber();
	static ServerConnections serverConnections = new ServerConnections();

	public void test() {
		System.out.println(dxConnection.getDxItemName());
	}

	public Element builder(Document xmlDoc, int num) throws FileNotFoundException {

		Element Connection = xmlDoc.createElement("Connection");
		Element ItemConnections = xmlDoc.createElement("ItemConnections");
		Element Subscriber = SubscriberBuilder(xmlDoc);
		Element Subscribee = SubscribeeBuilder(xmlDoc);
		Element DxConnection = xmlDoc.createElement("DxConnection");

		Element Enabled = xmlDoc.createElement("Enabled");
		Text productNameText2 = xmlDoc.createTextNode(getEnabled());
		Enabled.appendChild(productNameText2);

		Element SubscribeeUsesWrite = xmlDoc.createElement("SubscribeeUsesWrite");
		Text productNameText3 = xmlDoc.createTextNode(getSubscribeeUsesWrite());
		SubscribeeUsesWrite.appendChild(productNameText3);

		for (int i = 0; i < num; i++) {
			DxConnection = DxConnectionBuilder(xmlDoc, i + 1);
			ItemConnections.appendChild(DxConnection);
		}

		Connection.appendChild(Enabled);
		Connection.appendChild(SubscribeeUsesWrite);
		Connection.appendChild(Subscriber);
		Connection.appendChild(Subscribee);
		Connection.appendChild(ItemConnections);
		return Connection;
	}

	public static Element SubscriberBuilder(Document xmlDoc) {

		Element Subscriber = xmlDoc.createElement(subscriber.getTagName());

		Element Url = xmlDoc.createElement("Url");
		Text productNameText1 = xmlDoc.createTextNode(subscriber.getUrl());
		Url.appendChild(productNameText1);
		Element Name = xmlDoc.createElement("Name");
		Text productNameText2 = xmlDoc.createTextNode(subscriber.getName());
		Name.appendChild(productNameText2);

		Subscriber.appendChild(Url);
		Subscriber.appendChild(Name);

		return Subscriber;
	}

	public static Element SubscribeeBuilder(Document xmlDoc) {

		Element Subscribee = xmlDoc.createElement(subscribee.getTagName());

		Element Url = xmlDoc.createElement("Url");
		Text productNameText1 = xmlDoc.createTextNode(subscribee.getUrl());
		Url.appendChild(productNameText1);
		Element Name = xmlDoc.createElement("Name");
		Text productNameText2 = xmlDoc.createTextNode(subscribee.getName());
		Name.appendChild(productNameText2);

		Subscribee.appendChild(Url);
		Subscribee.appendChild(Name);

		return Subscribee;
	}

	public static Element DxConnectionBuilder(Document xmlDoc, int i) throws FileNotFoundException {

		Element DxConnection = xmlDoc.createElement("DxConnection");
		Element BrowsePath = xmlDoc.createElement("BrowsePath");
		Element Description = xmlDoc.createElement("Description");
		Element DefaultOverrideValue = xmlDoc.createElement("DefaultOverrideValue");
		Element SubstituteValue = xmlDoc.createElement("SubstituteValue");
		Element VendorData = xmlDoc.createElement("VendorData");

		Text text = xmlDoc.createTextNode(subscribee.getName());
		BrowsePath.appendChild(text);

//		System.out.println(dxConnection.getDxItemName());
		String DxItemName_text_after = dxConnection.getDxItemName().replace("*", String.valueOf(i));

		String TargetItemId_text_after = dxConnection.getTargetItemId().replace("*", String.valueOf(i));
		String SourceItemId_text_after = dxConnection.getSourceItemId().replace("*", String.valueOf(i));

//		dxConnection.setDxItemName(DxItemName_text_after);// need to increase
//		dxConnection.setTargetItemId(TargetItemId_text_after);// need to increase
//		dxConnection.setSourceItemId(SourceItemId_text_after);// need to increase

//		System.out.println(DxItemName_text_after);

		DxConnection.setAttribute("DxItemId", dxConnection.getDxItemId());
		DxConnection.setAttribute("DxItemName", DxItemName_text_after);
		DxConnection.setAttribute("TargetItemPath", dxConnection.getTargetItemPath());
		DxConnection.setAttribute("TargetItemName", dxConnection.getTargetItemName());
		DxConnection.setAttribute("TargetItemId", TargetItemId_text_after);
		DxConnection.setAttribute("TargetNamespaceIndex", dxConnection.getTargetNamespaceIndex());
		DxConnection.setAttribute("TargetItemDataType", dxConnection.getTargetItemDataType());
		DxConnection.setAttribute("SourceItemPath", dxConnection.getSourceItemPath());
		DxConnection.setAttribute("SourceItemName", dxConnection.getSourceItemName());
		DxConnection.setAttribute("SourceNamespaceIndex", dxConnection.getSourceNamespaceIndex());
		DxConnection.setAttribute("SourceItemId", SourceItemId_text_after);
		DxConnection.setAttribute("Scale", dxConnection.getScale());

		DxConnection.appendChild(BrowsePath);
		DxConnection.appendChild(Description);
		DxConnection.appendChild(DefaultOverrideValue);
		DxConnection.appendChild(SubstituteValue);
		DxConnection.appendChild(VendorData);

		return DxConnection;
	}

	public String getTagName() {
		return tagName;
	}

	public static void setTagName(String tagName1) {
		tagName = tagName1;
	}

	public String getEnabled() {
		return Enabled;
	}

	public static void setEnabled(String enabled1) {
		Enabled = enabled1;
	}

	public String getSubscribeeUsesWrite() {
		return SubscribeeUsesWrite;
	}

	public static void setSubscribeeUsesWrite(String subscribeeUsesWrite1) {
		SubscribeeUsesWrite = subscribeeUsesWrite1;
	}

	public static DxConnection getDxConnection() {
		return dxConnection;
	}

	public static void setDxConnection(DxConnection dxConnection) {
		Connection.dxConnection = dxConnection;
	}

	public static ItemConnections getItemConnections() {
		return itemConnections;
	}

	public static void setItemConnections(ItemConnections itemConnections) {
		Connection.itemConnections = itemConnections;
	}

	public static Subscribee getSubscribee() {
		return subscribee;
	}

	public static void setSubscribee(Subscribee subscribee) {
		Connection.subscribee = subscribee;
	}

	public static Subscriber getSubscriber() {
		return subscriber;
	}

	public static void setSubscriber(Subscriber subscriber) {
		Connection.subscriber = subscriber;
	}

	public static ServerConnections getServerConnections() {
		return serverConnections;
	}

	public static void setServerConnections(ServerConnections serverConnections) {
		Connection.serverConnections = serverConnections;
	}

}
