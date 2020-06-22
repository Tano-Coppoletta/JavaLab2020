package transactions;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {
	private Map<String,Region> regioni=new HashMap<>();
	private Map<String,Place> place=new TreeMap<>();
	private Map<String,Carrier> carriers=new HashMap<>();
	private Map<String,Request> richieste=new HashMap<>();
	private Map<String,Offer> offerte=new HashMap<>();
	private Map<String,Transaction> transazioni=new HashMap<>();
//R1
	public List<String> addRegion(String regionName, String... placeNames) { 
		Region r=new Region(regionName);
		List<String> nomi=new ArrayList<>();
		for(String s:placeNames) {
			if(!place.containsKey(s)) {
				r.addPlace(place.get(s));
				place.put(s,new Place(s,r));
				nomi.add(s);
			}
		}
		regioni.put(regionName, r);
		return nomi.stream().sorted().collect(toList());
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) { 
		Carrier c=new Carrier(carrierName);
		for(String s:regionNames) {
			if(regioni.containsKey(s)) {
				c.addRegion(regioni.get(s));
				regioni.get(s).addCarrier(c);
			}
		}
		carriers.put(carrierName, c);
		
		return c.getRegions().stream().map(Region::getName).sorted().collect(toList());
	}
	
	public List<String> getCarriersForRegion(String regionName) { 
		Region r=regioni.get(regionName);
		return r.getCarriers().stream().map(Carrier::getName)
				.sorted().collect(toList());
		
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId) 
			throws TMException {
		if(richieste.containsKey(requestId))
			throw new TMException();
		if(!place.containsKey(placeName))
			throw new TMException();
		Request r=new Request(requestId,place.get(placeName),productId);
		richieste.put(requestId, r);
	}
	
	public void addOffer(String offerId, String placeName, String productId) 
			throws TMException {
		if(offerte.containsKey(offerId))
			throw new TMException();
		if(!place.containsKey(placeName))
			throw new TMException();
		offerte.put(offerId, new Offer(offerId,place.get(placeName),productId));
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) 
			throws TMException {
		Request richiesta=richieste.get(requestId);
		Offer offerta=offerte.get(offerId);
		if(!richiesta.getProductId().equals(offerta.getProductId()))
			throw new TMException();
		if(offerta.getT())
			throw new TMException();
		if(richiesta.getT())
			throw new TMException();
		
		Carrier carrier=carriers.get(carrierName);
		Region partenza=richiesta.getPlace().getRegion();
		Region arrivo=offerta.getPlace().getRegion();
		if(!carrier.containRegion(partenza) || !carrier.containRegion(arrivo))
			throw new TMException();
		Transaction t=new Transaction(transactionId, carrierName, richieste.get(requestId), offerte.get(offerId));
		transazioni.put(transactionId, t);
		richiesta.addTransaction();
		offerta.addTransaction();
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		if(score>10 || score<=0)
			return false;
		transazioni.get(transactionId).addScore(score);
		return true;
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
		Map<String,Long> mappa=transazioni.values().stream()
								.collect(Collectors.groupingBy(
											s->{
												String s1=s.getRequestId();
												return richieste.get(s1).getPlace().getRegion().getName();
											}
											,
											counting()));
		return mappa.entrySet().stream()
					.collect(Collectors.groupingBy(
							Map.Entry::getValue,
							()->new TreeMap<Long,List<String>>(Comparator.reverseOrder()),
							mapping(e->e.getKey(),toList())));
		
		
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		return transazioni.values().stream()
				.filter(s->s.getScore()>=minimumScore)
				.collect(groupingBy(
						(Transaction::getCarrierName),
						TreeMap::new,
						summingInt(Transaction::getScore)
						));
	}
	
	public SortedMap<String, Long> nTPerProduct() {
		return transazioni.values().stream()
				.map(Transaction::getProductid)
				.collect(groupingBy(s->s,TreeMap::new,counting()));
	}
	
	
}

