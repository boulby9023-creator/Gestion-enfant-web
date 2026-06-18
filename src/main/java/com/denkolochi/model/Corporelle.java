package com.denkolochi.model;

import java.time.LocalDate;
import java.util.Date;

public class Corporelle {
	private int id;
	private int id_enfant;
	private double poids;
	private double taille;
	private double imc;
	private LocalDate date_mesure;

	public Corporelle(int id, int id_enfant, double poids, double taille, double imc, LocalDate date_mesure) {
		this.id = id;
		this.id_enfant = id_enfant;
		this.poids = poids;
		this.taille = taille;
		this.imc = imc;
		this.date_mesure = date_mesure;
	}

	public Corporelle() {
	}

	public int getId() {
		return id;
	}

	public int getId_enfant() {
		return id_enfant;
	}

	public void setId_enfant(int id_enfant) {
		this.id_enfant = id_enfant;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public double getTaille() {
		return taille;
	}

	public void setTaille(double taille) {
		this.taille = taille;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public LocalDate getDate_mesure() {
		return date_mesure;
	}

	public void setDate_mesure(LocalDate date_mesure) {
		this.date_mesure = date_mesure;
	}

}