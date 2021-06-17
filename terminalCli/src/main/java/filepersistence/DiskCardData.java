package filepersistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import coreapi.Card;
import data.CardData;

public class DiskCardData implements CardData{

	private String path;
	
	public DiskCardData(String path)
	{
		this.path = path;
	}
	
	public Card getCard(int nCard)
	{
		Card c = null;
		
        try 
        {
            FileInputStream currentCard = new FileInputStream(path + "card" + nCard + ".txt");
            ObjectInputStream CardRead = new ObjectInputStream(currentCard);
            c = (Card)CardRead.readObject();
            CardRead.close();
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        
        return c;
	}
	
	public void saveCard(Card c)
	{
		try {
			FileOutputStream currentCard = new FileOutputStream(path + "card" + c.getCardNumber() + ".txt");
			ObjectOutputStream CardWrite = new ObjectOutputStream(currentCard);
			CardWrite.writeObject(c);
			CardWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
