package transactions;

public class Transaction {
	private String id;
	private String carrierName;
	private Request requestId;
	private Offer offerId;
	private int score;
	
	public Transaction(String id,String carrierName, Request requestId, Offer offerId) {
		this.id=id;
		this.carrierName=carrierName;
		this.requestId=requestId;
		this.offerId=offerId;
		score=0;
	}
	
	public String getId() {
		return id;
	}
	
	public String getCarrierName() {
		return carrierName;
	}
	public String getRequestId() {
		return requestId.getId();
	}
	public String getOffer() {
		return offerId.getId();
	}
	
	public String getProductid() {
		return requestId.getProductId();
	}
	
	public void addScore(int score) {
		this.score=score;
	}
	
	public boolean scoreOk(int s) {
		if(score>=s)
			return true;
		return false;
	}
	public int getScore() {
		return score;
	}
}
