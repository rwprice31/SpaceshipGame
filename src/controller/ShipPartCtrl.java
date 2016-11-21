package controller;

public class ShipPartCtrl 
{	
	private int suitPartID;
	private String suitPartName;
	
	/**
	 * @param suitPartID
	 * @param suitPartName
	 */
	public ShipPartCtrl(int suitPartID, String suitPartName) 
	{
		super();
		this.suitPartID = suitPartID;
		this.suitPartName = suitPartName;
	}
	
	/**
	 * @return the suitPartID
	 */
	public int getSuitPartID() {
		return suitPartID;
	}
	
	/**
	 * @param suitPartID the suitPartID to set
	 */
	public void setSuitPartID(int suitPartID)
	{
		this.suitPartID = suitPartID;
	}
	
	/**
	 * @return the suitPartName
	 */
	public String getSuitPartName()
	{
		return suitPartName;
	}
	
	/**
	 * @param suitPartName the suitPartName to set
	 */
	public void setSuitPartName(String suitPartName)
	{
		this.suitPartName = suitPartName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "ShipPartCtrl [suitPartID=" + suitPartID + ", suitPartName=" + suitPartName + "]";
	}
	
	
	
	

}
