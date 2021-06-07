package data;

/**
 * @author Fran
 */
import coreapi.Card;
import coreapi.User;


public interface CardData {
	public Card getCard(int nCard);
	public void saveCard(Card c);
}
