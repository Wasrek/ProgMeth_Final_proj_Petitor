package card;

import logic.CardName;
import logic.CardType;
import logic.GameLogic;

public abstract class MonsterCard extends BaseCard implements Attackable, Guardable{
	private int DefVal, AtkVal;
	private boolean Status;
	private int lastUsedTurn, lastAtk;
	
	public MonsterCard(CardName name, int price, int AtkVal, int DefVal, boolean Status) {
		super(CardType.MONSTER, name, price, 0);
		this.DefVal = DefVal;
		this.AtkVal = AtkVal;
		this.Status = Status;
		this.lastUsedTurn = -1;
		this.lastAtk = -1;
		// TODO Auto-generated constructor stub
	}
	

	public abstract String performEffect();
	
	public String play() {
//		GameLogic.getInstance().setTopMonCard(this);
//		GameLogic.getInstance().getCurrentMonPlayerHand().remove(this);
		return performEffect();
	}

	public int getDefVal() {
		return DefVal;
	}

	public void setDefVal(int defVal) {
		DefVal = defVal;
	}

	public int getAtkVal() {
		return AtkVal;
	}

	public void setAtkVal(int atkVal) {
		AtkVal = atkVal;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
	
	public int getVal() {
		if(this.getStatus()) {
			return this.AtkVal;
		}
		return this.DefVal;
	}
	
	public int getLastUsedTurn() {
		return lastUsedTurn;
	}

	public void setLastUsedTurn(int lastUsedTurn) {
		this.lastUsedTurn = lastUsedTurn;
	}
	
	public int getLastAtk() {
		return lastAtk;
	}
	
	public void setLastAtk(int lastAtk) {
		this.lastAtk = lastAtk;
	}
	
	// atk, def, status, effect
	// last status modify(check attackable), last turn active(check if already attack on that turn or not)
}
