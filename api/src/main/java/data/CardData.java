package data;

/**
 * @author Fran
 */
import coreapi.Card;


public interface CardData {
	public User getCard(int nCard);
	public void saveCard(Card c);
}
