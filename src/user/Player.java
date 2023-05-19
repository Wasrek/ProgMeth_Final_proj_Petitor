package user;

import java.util.ArrayList;

import application.main;
import card.BaseCard;
import card.Buyable;
import card.MonsterCard;
import card.SpellCard;
import javafx.application.Platform;
import logic.GameLogic;

public class Player implements Buyable{
	private int index, money , hp, lastBuyS=0, lastBuyM=0;
    private ArrayList<BaseCard> MonHand, SpHand;
	
    public Player(int index, int money, int hp) {
    	this.index = index;
    	this.setMoney(money);
    	this.setHp(hp);
    	this.setMonHand(new ArrayList<>());
    	this.setSpHand(new ArrayList<>());
    }
    
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp <= 0 ) {
			this.hp = 0;
			System.out.println("setHp");
			main.showEndScene();
		}else {
			this.hp = hp;
		}
	}

	public void setMonHand(ArrayList<BaseCard> monHand) {
		MonHand = monHand;
	}

	public void setSpHand(ArrayList<BaseCard> spHand) {
		SpHand = spHand;
	}


	public ArrayList<BaseCard> getMonHand() {
		return MonHand;
	}

	public ArrayList<BaseCard> getSpHand() {
		return SpHand;
	}

	public int getIndex() {
		return index;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = Math.max(money, 0);
	}
	
	public String toString() {
		return "Player " + (this.getIndex()+1);
	}

	@Override
	public void buy(ArrayList<BaseCard> buyCards) {
		// TODO Auto-generated method stub
		if (this.isBuyable(buyCards)) {
			GameLogic.getInstance().getCur().setMoney(GameLogic.getInstance().getCur().getMoney() - GameLogic.getInstance().getSumPrice());
			GameLogic.getInstance().setSumPrice(0);
			for(BaseCard cd : GameLogic.getInstance().getnowClick()) {
				cd.setOwner(getIndex());
				if(cd instanceof MonsterCard) {
					GameLogic.getInstance().getCur().getMonHand().add(cd);
					GameLogic.getInstance().getMonMarket().remove(cd);
					this.lastBuyM = GameLogic.getInstance().getTurnCount();
				}else {
					GameLogic.getInstance().getCur().getSpHand().add(cd);
					GameLogic.getInstance().getSpMarket().remove(cd);
					this.lastBuyS = GameLogic.getInstance().getTurnCount();
				}
			}	
		}
	}

	public boolean isBuyable(ArrayList<BaseCard> buyCards) {
		// TODO Auto-generated method stub		
		if(GameLogic.getInstance().getSumPrice() > GameLogic.getInstance().getCur().getMoney()) {
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
			return false;
		}
		if(Spcnt + GameLogic.getInstance().getCur().getSpHand().size() >3) {
			return false;
		}
		return true;
	}

}