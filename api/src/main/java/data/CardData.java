package data;

/**
 * Interface that offers operations to save the data of a card.
 * @author Fran
 * @author Maria
 */
import coreapi.Card;


public interface CardData 
{
	/**
	 * Return a specific card.
	 * @param nCard	The card number of a specific card.
	 * @return 	Return a specific card.
	 */
	public Card getCard(int nCard);
	
	/**
	 * Save a specific card.
	 * @param c	The specific card.
	 */
	public void saveCard(Card c);
}
