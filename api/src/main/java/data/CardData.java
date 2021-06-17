package data;

/**
 * @author Fran
 */
import coreapi.Card;


public interface CardData {
	public Card getCard(int nCard);
	public void saveCard(Card c);
}
