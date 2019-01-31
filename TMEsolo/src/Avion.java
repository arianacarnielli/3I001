import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//réponse à la question 2:
//On utilise 3 locks, chacun avec autant de Conditions que de Rangees au Avion. Chaque couple de methodes utilise 1 lock. 
//L'idee est que, comme les cases du tableau etat qui seront changes par des fonctions qui ne sont pas avec le meme lock ne sont pas les memes que celles qu'il faut proteger, 
//on peut laisser les autres methodes le changer.

//in english maybe I can explain better:
// I used 3 locks, with nbRangees Conditions for each lock. 
//As we have 3 couples of methods (attendreEnregistrement/enregistrerPassager, attendreAutorisation/autoriserEmbarquement and attendreEmbarquement/terminerEmbarquement) and I know
//each couple of methods will try to change/look a diferent etat[i], I don't have to protect all methods with the same lock, this way I can have more parallelism.  
//As it is, a passager can embark at the same time another passager register, for example. 

public class Avion{

	private int nbRangees;
	private int nbSieges;
	private int etat[];

	private final ReentrantLock lock_enre = new ReentrantLock();
	private final ReentrantLock lock_auto = new ReentrantLock();
	private final ReentrantLock lock_embar = new ReentrantLock();
	
	private final Condition[] attend_enre;
	private final Condition[] attend_auto;
	private final Condition[] attend_embar;

	public Avion(int nbRangees, int nbSieges){
		
		this.attend_enre = new Condition[nbRangees];
		for(int i = 0; i < nbRangees; i++) {
			attend_enre[i] = lock_enre.newCondition();
		}
		
		this.attend_auto = new Condition[nbRangees];
		for(int i = 0; i < nbRangees; i++) {
			attend_auto[i] = lock_auto.newCondition();
		}
		
		this.attend_embar = new Condition[nbRangees];
		for(int i = 0; i < nbRangees; i++) {
			attend_embar[i] = lock_embar.newCondition();
		}
		
		this.nbRangees=nbRangees;
		this.nbSieges=nbSieges;
		etat=new int[nbRangees];
		for(int i=0;i<nbRangees;i++){
			etat[i]=-nbSieges-1;
		}	
	}

	public int getNbRangees(){
		return nbRangees;
	}

	public void attendreEnregistrement(int rangee) throws InterruptedException{
		lock_enre.lock();
		try {
			while(etat[rangee] != -1) {
				attend_enre[rangee].await();
			}
		}finally {
			lock_enre.unlock();
		}
	}

	public void enregistrerPassager(int rangee){
		lock_enre.lock();
		try {
			etat[rangee]++;
			attend_enre[rangee].signalAll();
		}finally {
			lock_enre.unlock();
		}
	}

	public void attendreAutorisation(int rangee) throws InterruptedException{
		lock_auto.lock();
		try {
			while(etat[rangee] < 0) {
				attend_auto[rangee].await();
			}
		}finally{
			lock_auto.unlock();
		}
	}

	public void autoriserEmbarquement(int rangee){
		lock_auto.lock();
		try {
			etat[rangee] = 0;
			attend_auto[rangee].signalAll();
		}finally {
			lock_auto.unlock();
		}
	}

	public void attendreEmbarquement(int rangee) throws InterruptedException{
		lock_embar.lock();
		try {
			while(etat[rangee] < nbSieges) {
				attend_embar[rangee].await();
			}
		}finally {
			lock_embar.unlock();
		}
	}

	public void terminerEmbarquement(int rangee){
		lock_embar.lock();
		try {
			etat[rangee]++;
			attend_embar[rangee].signalAll();
		}finally {
			lock_embar.unlock();
		}
	}

}