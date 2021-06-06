package data;

/**
 * @author Fran
 */
import coreapi.Card;
import coreapi.User;


public interface CardData {
	public User getCard(int nCard);
	public void saveCard(Card c);
}
