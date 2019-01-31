public class Chargeur implements Runnable{
	private Chariot chariot;
	private AleaStock stock;
	
	public Chargeur (AleaStock stock, Chariot chariot) {
		this.chariot = chariot;
		this.stock = stock;
	}
	
	
	@Override
	public void run() {
		AleaObjet march;
		boolean bol;
		while(true) {
			try {
				march = stock.sorterStock();
				if(march == null) {
					chariot.setStockFini();
					break;
				}
				bol = chariot.ajouterMarch(march);
				if(!bol) {
					stock.ajouterStock(march);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ne doit pas arriver!");
			}
				
		}
	}

}
