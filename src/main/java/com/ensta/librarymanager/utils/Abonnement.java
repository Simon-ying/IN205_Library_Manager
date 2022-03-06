package com.ensta.librarymanager.utils;

public enum Abonnement {
	BASIC("Basic", 2),
	PREMIUM("Premium", 5),
	VIP("Vip", 20);
	
	private String type;
	private int value;
	
	Abonnement(String type, int value) {
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return this.type;
	}
	public int getValue() {
		return this.value;
	}
	public static Abonnement getFromString(String type) {
		switch(type.toLowerCase()) {
		case "basic":
			return Abonnement.BASIC;
		case "premium":
			return Abonnement.PREMIUM;
		case "vip":
			return Abonnement.VIP;
		default:
			return null;
		}
	}
}
