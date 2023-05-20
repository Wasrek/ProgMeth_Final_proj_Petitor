package user;

import java.util.ArrayList;

import application.main;
import card.BaseCard;
import card.Buyable;
import card.MonsterCard;
import card.SpellCard;
import javafx.application.Platform;
import logic.GameLogic;

/**
 * game player
 * @author Wishmeluck
 *
 */
public class Player implements Buyable{
	/**
	 * index	player's id
	 */
	/**
	 * money	player's money
	 */
	/**
	 * hp	player's life point
	 */
	/**
	 * lastBuyS	player's last buy spell card
	 */
	/**
	 * lastBuyM	player's last buy monster card
	 */
	private int index, money , hp, lastBuyS=0, lastBuyM=0;
    /**
     * MonHand	player's monster cards
     */
    /**
     * SpHand	player's spell cards
     */
    private ArrayList<BaseCard> MonHand, SpHand;
	
    /**
     * Constructor for player
     * @param index	player's id
     * @param money	player's money
     * @param hp	player' life point
     */
    public Player(int index, int money, int hp) {
    	this.index = index;
    	this.setMoney(money);
    	this.setHp(hp);
    	this.setMonHand(new ArrayList<>());
    	this.setSpHand(new ArrayList<>());
    }
    
	/**
	 * Getter for player's life point
	 * @return	player 's life point
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Setter for player's life point
	 * @param hp	player's life point
	 */
	public void setHp(int hp) {
		if (hp <= 0 ) {
			this.hp = 0;
			System.out.println("setHp");
			main.showEndScene();
		}else {
			this.hp = hp;
		}
	}

	/**
	 * Setter for plsyer's monster cards
	 * @param monHand	player's monster cards
	 */
	public void setMonHand(ArrayList<BaseCard> monHand) {
		MonHand = monHand;
	}

	/**
	 * Setter for player's spell cards
	 * @param spHand	player's spell cards
	 */
	public void setSpHand(ArrayList<BaseCard> spHand) {
		SpHand = spHand;
	}


	/**
	 * Getter for player's monster cards
	 * @return	player's spell cards
	 */
	public ArrayList<BaseCard> getMonHand() {
		return MonHand;
	}

	/**
	 * Getter for player's spell cards
	 * @return	player's spell cards
	 */
	public ArrayList<BaseCard> getSpHand() {
		return SpHand;
	}

	/**
	 * Getter for player's id
	 * @return	player's id
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Getter for player's money
	 * @return	player's money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Setter for player's money
	 * @param money player's money
	 */
	public void setMoney(int money) {
		this.money = Math.max(money, 0);
	}
	
	/**
	 *	@return	player's name
	 */
	public String toString() {
		return "Player " + (this.getIndex()+1);
	}

	/**
	 * 	Method to let player buy cards from the market in buy phrase
	 *	@param buyCards	list that player choose to buy
	 */
	@Override
	public void buy(ArrayList<BaseCard> buyCards) {
		// TODO Auto-generated method stub
		if (this.isBuyable(buyCards)) {
			GameLogic.getInstance().updBuyani();
			GameLogic.getInstance().getCur().setMoney(GameLogic.getInstance().getCur().getMoney() - GameLogic.getInstance().getSumPrice());
			GameLogic.getInstance().setSumPrice(0);
			for(BaseCard cd : GameLogic.getInstance().getnowClick()) {
				cd.setOwner(getIndex());
				if(cd instanceof MonsterCard) {
					GameLogic.getInstance().getCur().getMonHand().add(cd);
					GameLogic.getInstance().getMonMarket().remove(cd);
					this.lastBuyM = GameLogic.getInstance().getTurnCount();
					((MonsterCard) cd).setSummonedTurn(GameLogic.getInstance().getTurnCount());
				}else {
					GameLogic.getInstance().getCur().getSpHand().add(cd);
					GameLogic.getInstance().getSpMarket().remove(cd);
					this.lastBuyS = GameLogic.getInstance().getTurnCount();
				}
			}
			GameLogic.getInstance().getnowClick().clear();
		}
	}

	/**
	 * Method to check if can buy cards
	 * 	@param buyCards	list that player choose to buy
	 *	@return boolean that this player can buy those cards or not
	 */
	public boolean isBuyable(ArrayList<BaseCard> buyCards) {
		// TODO Auto-generated method stub		
		if(GameLogic.getInstance().getSumPrice() > GameLogic.getInstance().getCur().getMoney()) {
			GameLogic.getInstance().updTextani("not enough money");
			return false;
		}
		int moncnt=0, Spcnt=0;
		for(BaseCard cd : GameLogic.getInstance().getnowClick()) {
			if(cd instanceof MonsterCard) {
				moncnt++;
				if(this.lastBuyM == GameLogic.getInstance().getTurnCount()) return false;
			}else {
				Spcnt++;
				if(this.lastBuyS == GameLogic.getInstance().getTurnCount()) return false;
			}
		}
		if((moncnt + GameLogic.getInstance().getCur().getMonHand().size()) > 3) {
			GameLogic.getInstance().updTextani("Your field is full");
			return false;
		}
		if(Spcnt + GameLogic.getInstance().getCur().getSpHand().size() >3) {
			GameLogic.getInstance().updTextani("Your field is full");
			return false;
		}
		return true;
	}

}