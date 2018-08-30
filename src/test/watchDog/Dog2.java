package test.watchDog;

import java.util.ArrayList;

import test.watchDog.observer.ObservableDog;
import test.watchDog.observer.ObservateurDog;


public class Dog2 implements ObservableDog, ObservateurDog {

	// -- observable
	protected ArrayList<ObservateurDog> listObservateur = new ArrayList<ObservateurDog>();

	//private ObservateurDog obs;

	private int nbreWaf;

	public Dog2() {
		//this.obs = obs;
		//this.addObservateur(obs);
		nbreWaf = 0;
	}

	public void loop() {
		nbreWaf ++;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Wif Wif je suis le yorkshire, j'aboie "+nbreWaf+" fois !");
		update(nbreWaf);
	}

	public void update(int nbreWaf) {
			if (this.nbreWaf <= nbreWaf) {loop();}
			System.out.println("update yorkshire. la variable"+this.nbreWaf+"le parametre"+nbreWaf);
	}

	public int getNbreWaf() {return nbreWaf;}
	public void setNbreWaf(int nbreWaf) {this.nbreWaf = nbreWaf;}
	
	public void addObservateur(ObservateurDog obs) {listObservateur.add(obs); this.updateObservateur();}
	public void updateObservateur() {for(ObservateurDog obs : listObservateur) {obs.update(nbreWaf);}}
	public void delObservateur() {}
}