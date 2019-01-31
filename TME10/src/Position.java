public enum Position {
	NORD(0), SUD(1);
	
	private final int index;
	
	private Position(int index) {
		this.index = index;
	}
	
	public int index() {
		return index;
	}
}