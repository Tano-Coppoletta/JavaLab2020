package mountainhuts;

import static java.util.stream.Collectors.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;


/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {
	private String name;
	List<Range> altitudini=new LinkedList<>();
	Map<String,Municipality> comuni=new TreeMap<>();
	Map<String,MountainHut> rifugi=new TreeMap<>();
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name=name;
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		for(String s:ranges) {
			altitudini.add(new Range(s));
		}
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		for(Range r:altitudini) {
			if(r.incluso(altitude)) {
				return r.toString();
			}
		}
		return 0+"-INF";
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		if(comuni.containsKey(name)) {
			return comuni.get(name);
		}
		Municipality m=new Municipality(name,province,altitude);
		comuni.put(name, m);
		return m;
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return comuni.values();
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		if(rifugi.containsKey(name)) {
			return rifugi.get(name);
		}
		MountainHut m=new MountainHut(name,category,bedsNumber,municipality);
		rifugi.put(name,m);
		return m;
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		if(rifugi.containsKey(name)) {
			return rifugi.get(name);
		}
		MountainHut m=new MountainHut(name,altitude,category,bedsNumber,municipality);
		rifugi.put(name,m);
		return m;
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return rifugi.values();
	}

	/**
	 * Factory methods that creates a new region by loadomg its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		List<String> lista=readData(file);
		Region r=new Region(name);
		java.util.Iterator<String> itr=lista.iterator();
		itr.next();
		String[] s1;
		Municipality m;
		while(itr.hasNext()) {
			s1=itr.next().split(";");
			m=r.createOrGetMunicipality(s1[1],s1[0],Integer.parseInt(s1[2]));
			if(s1[4].length()!=0) {
				r.createOrGetMountainHut(s1[3],Integer.parseInt(s1[4]) , s1[5], Integer.parseInt(s1[6]),m );
			}else
				r.createOrGetMountainHut(s1[3], s1[5], Integer.parseInt(s1[6]),m );
		}
		return r;
	}

	/**
	 * Internal class that can be used to read the lines of
	 * a text file into a list of strings.
	 * 
	 * When reading a CSV file remember that the first line
	 * contains the headers, while the real data is contained
	 * in the following lines.
	 * 
	 * @param file the file name
	 * @return a list containing the lines of the file
	 */
	@SuppressWarnings("unused")
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		Map<String, Long> mappa =comuni.values().stream()
					.parallel()
		          .collect(Collectors.groupingBy(Municipality::getProvince,HashMap::new, Collectors.counting()));
		return mappa;
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		Map<String,Map<String,Long>> mappa=rifugi.values().stream()
				.parallel()
				.collect(Collectors.groupingBy((x)->x.getMunicipality().getProvince(),
						HashMap::new,
						Collectors.groupingBy(x->x.getMunicipality().getName(),HashMap::new,Collectors.counting())));
		return mappa;
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		Map<String, Long> mappa=rifugi.values().stream().
			collect(groupingBy((a)->{
				Optional<Integer> alt=a.getAltitude();
				if(alt.isPresent())
					return this.getAltitudeRange(alt.get());
				return this.getAltitudeRange(a.getMunicipality().getAltitude());
			},
				HashMap::new,
				counting()
					));
		altitudini.stream().
			map(Range::toString).
			forEach(r->{
				if(!mappa.containsKey(r)) {
					mappa.put(r,0L);
				}
			});
		return mappa;
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		Map<String,Integer> mappa=rifugi.values().stream()
				.parallel()
				.collect(Collectors.groupingBy(m->m.getMunicipality().getProvince(),HashMap::new,Collectors.summingInt(m->m.getBedsNumber())));
		return mappa;
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		Map<String,Optional<Integer>> mappa=rifugi.values().stream().
												collect(groupingBy((a)->{
													Optional<Integer> alt=a.getAltitude();
													if(alt.isPresent())
														return this.getAltitudeRange(alt.get());
													return this.getAltitudeRange(a.getMunicipality().getAltitude());
												},
												HashMap::new,
												Collectors.mapping(MountainHut::getBedsNumber,Collectors.maxBy(Comparator.naturalOrder()))));
		return mappa;
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
			 Map<Long,List<String>> mappa=comuni.values().stream().
				 map(Municipality::getName).
				 sorted().
				 collect(groupingBy((s)->{
					 						return  rifugi.values().stream()
					 								.collect(collectingAndThen(
					 										groupingBy(
					 												MountainHut::getMunicipalityName,
					 												TreeMap::new,
					 												counting())
											,
											(Map<String,Long> m)-> m.get(s)
											));
				 },
						 TreeMap::new,
						 toList()));
			 
			 //oppure
			// Map<String,Long> res=rifugi.values().stream().
				//	 collect(groupingBy(MountainHut::getMunicipalityName),
					//		 TreeMap::new,
						//	 counting());
			// return res.entrySet().stream().
				//	 collect(groupingBy(Map.Entry::getValue),
					//		 mapping(Map.Entry::getKey,
						//			 toList()));
		
		return mappa;
	}

}
