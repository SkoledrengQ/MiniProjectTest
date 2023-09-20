package modellayer;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik B�rbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class Currency {
	
	public enum ValidCurrency { EURO, DKK }; // Fjernet SEK og NOK som ValidCurrency
	public enum ValidCoinType { FRACTION, INTEGER };

}
