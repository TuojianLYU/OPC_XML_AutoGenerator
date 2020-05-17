package DxConnetionConfigure;

public class DxConnectionConfig {

	String Tick = "0.1";
	String Synchronization = "false";
	String tagName = "DxConnectionConfig";

	public String getTick() {
		return Tick;
	}

	public void setTick(String tick) {
		Tick = tick;
	}

	public String getSynchronization() {
		return Synchronization;
	}

	public void setSynchronization(String synchronization) {
		Synchronization = synchronization;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
