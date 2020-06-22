package mountainhuts;

/**
 * Represents a municipality
 *
 */
public class Municipality {
	private String name;
	private String province;
	private int altitude;
	
	public Municipality(String name,String p,int alt) {
		this.name=name;
		province=p;
		altitude=alt;
	}
	/**
	 * Name of the municipality.
	 * 
	 * Within a region the name of a municipality is unique
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Province of the municipality
	 * 
	 * @return province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Altitude of the municipality
	 * 
	 * @return altitude
	 */
	public Integer getAltitude() {
		return altitude;
	}

}
