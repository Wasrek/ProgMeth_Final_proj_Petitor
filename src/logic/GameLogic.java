package logic;

import java.util.ArrayList;
import java.util.Collections;

import application.main;
import card.BaseCard;
import card.BirdCard;
import card.BlackholeCard;
import card.CatCard;
import card.DuckCard;
import card.GolemCard;
import card.KingCard;
import card.MonsterCard;
import card.NewWorldCard;
import card.TornadoCard;
import card.VitaminCard;
import user.Player;
import gui.AnimatePane;
import gui.GameFieldPane;
import gui.RightGamePane;
import gui.LeftGamePane;
public class GameLogic {
    private final int initialPlayerCount = 2;
    private final ArrayList<BaseCard> MonDeck, SpDeck, MonMarket, SpMarket, nowClick;
    private int currentPlayer;
    private int turnCount, nowPhrase, sumPrice;
    private final ArrayList<Player> players;

	private static GameLogic instance = null;

    private GameLogic(int initialPlayerCount) {
        this.MonDeck = new ArrayList<>();
        this.SpDeck = new ArrayList<>();
        this.SpMarket = new ArrayList<>();
        this.MonMarket = new ArrayList<>();
        this.nowClick = new ArrayList<>();
        this.players = new ArrayList<>();
        this.players.add(new Player(0, 4, 3000));
        this.players.add(new Player(1, 4, 3000));
        this.currentPlayer = 0;
        this.turnCount = 1;
        this.sumPrice = 0;
        this.setNowPhrase(1);
    }

    public static GameLogic getInstance() {
        if(instance == null) {
            instance = new GameLogic(2);
        }
        return instance;
    }

    public static GameLogic getInstance(int initialPlayerCount) {
    	System.out.println("getInstance");
        if(instance == null) {
            instance = new GameLogic(initialPlayerCount);
        }
        return instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    public void initGame() {
        // Create the full monster's deck of 20 cards
        // Bird, Golem , Cat and Duck x4
    	System.out.println("InitGame");
        for (int i=0; i<4; i++) {
        	MonDeck.add(new BirdCard());   
        	MonDeck.add(new GolemCard());   
        	MonDeck.add(new CatCard());   
        	MonDeck.add(new DuckCard());    
        }
        // King x2
        for (int i=0; i<2; i++)
            MonDeck.add(new KingCard());
        
        // Create the full spell's deck of 20 cards
        // New World, Vitamin and Tornado x4
        for (int i=0; i<4; i++) {
        	SpDeck.add(new NewWorldCard());   
        	SpDeck.add(new VitaminCard());   
        	SpDeck.add(new TornadoCard());   
        }
        // Blackhole x2
        for (int i=0; i<2; i++)
            SpDeck.add(new BlackholeCard());
        System.out.println(MonDeck.size());
        // Shuffle and hand out 3 cards to Market (If Market's cards have price greater than 4, reshuffle)
        Collections.shuffle(MonDeck);
        Collections.shuffle(SpDeck);
        System.out.println("Card Shuffled");
        while (MonMarket.size() < 3) {
        	if (turnCount == 1) {
        		if (MonDeck.get(0).getPrice() <= 4) {
        			BaseCard card = MonDeck.remove(0);
        			MonMarket.add(card);  
        		}else {
        			Collections.shuffle(MonDeck);
        		}
        	}else {
        		BaseCard card = MonDeck.remove(0);
    			MonMarket.add(card);   
        	}
        }
        System.out.println("Gen MM");
        System.out.println(MonMarket.size());
        while (SpMarket.size() < 3) {
        	if (turnCount == 1) {
        		if (SpDeck.get(0).getPrice() <= 4) {
        			BaseCard card = SpDeck.remove(0);
        			SpMarket.add(card);  
        		}else {
        			Collections.shuffle(SpDeck);
        		}
        	}else {
        		BaseCard card = SpDeck.remove(0);
    			SpMarket.add(card);   
        	}
        }
        System.out.println("Gen SM");
        System.out.println(SpMarket.size());
    }

    public ArrayList<BaseCard> getMonDeck() {
        return MonDeck;
    }
    
    public ArrayList<BaseCard> getSpDeck() {
        return SpDeck;
    }

    public int getCurrentPlayer() {
    	return currentPlayer;
    }
    
    public Player getCur() {
    	return getInstance().getPlayers().get(getInstance().getCurrentPlayer());
    }
    
    public int getOppositePlayer() {
    	return Math.floorMod(getCurrentPlayer()+1, 2);
    }
    public Player getOpp() {
    	return getInstance().getPlayers().get(getInstance().getOppositePlayer());
    }

    public void goToNextPlayer() {
    	System.out.println("nextpla");
    	turnCount += 1;
    	currentPlayer = Math.floorMod(this.currentPlayer + 1, initialPlayerCount);
    	int currentMoney = this.getPlayers().get(this.getCurrentPlayer()).getMoney();
    	int addMoney = Math.floorDiv(turnCount-1, 4) + 1;
    	System.out.println("money added");
    	this.getPlayers().get(this.getCurrentPlayer()).setMoney(currentMoney + addMoney);
    	System.out.println("nextpla");
    }
    
    public void goToNextPhrase() {
    	System.out.println("nextcallph");
    	this.setNowPhrase(this.getNowPhrase()%3+1);
    	System.out.println(this.getNowPhrase());
    	this.setSumPrice(0);
    	if(this.getNowPhrase() == 1) {
    		this.goToNextPlayer();
    		System.out.println("upmarket");
    		updateMarket();
    	}
    	this.getnowClick().clear();
    	System.out.println("upmarket");
		((RightGamePane) main.getGameRoot().getChildren().get(2)).updateRightPane();
		((GameFieldPane) main.getGameRoot().getChildren().get(1)).updateField();
		((LeftGamePane) main.getGameRoot().getChildren().get(0)).updateStatus();
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setnormal();
    }

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	public ArrayList<BaseCard> getMonMarket() {
		return MonMarket;
	}

	public ArrayList<BaseCard> getSpMarket() {
		return SpMarket;
	}
	
	public ArrayList<BaseCard> getnowClick() {
		return nowClick;
	}

	public int getNowPhrase() {
		return nowPhrase;
	}

	public void setNowPhrase(int nowPhrase) {
		this.nowPhrase = nowPhrase;
	}

	public String getPhraseText() {
		switch (this.getNowPhrase()) {
	        case 1:
	            return "Buy";
	        case 2:
	            return "Use";
	        case 3:
	            return "Attack";
	        default:
	            return "Invalid";
		}
	}
	
	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	public void buyClick() {
		this.getCur().buy(getnowClick());
		this.getnowClick().clear();
		((RightGamePane) main.getGameRoot().getChildren().get(2)).updateRightPane();
		((GameFieldPane) main.getGameRoot().getChildren().get(1)).updateField();
		((LeftGamePane) main.getGameRoot().getChildren().get(0)).updateStatus();
	}
	
	public void useEffClick() {
		// TODO Auto-generated method stub
		this.getnowClick().get(0).useEffect();
		this.getnowClick().clear();
		((GameFieldPane) main.getGameRoot().getChildren().get(1)).updateField();
		((LeftGamePane) main.getGameRoot().getChildren().get(0)).updateStatus();
	}

	public void AtkClick() {
		// TODO Auto-generated method stub
		//nowClick.get(0) Atk with nowClick.get(1)
		if(this.getTurnCount()!=1 && this.getnowClick().size()==1 && this.getnowClick().get(0).getOwner()==this.getCurrentPlayer()) {
			if(!((MonsterCard) this.getnowClick().get(0)).isAttackable(null)) {
				return ;
			}
			this.updAtkani("Player attacked");
			((MonsterCard) this.getnowClick().get(0)).setLastAtk(GameLogic.getInstance().getTurnCount());
			GameLogic.getInstance().getOpp().setHp(GameLogic.getInstance().getOpp().getHp()-((MonsterCard) this.getnowClick().get(0)).getVal());
			((LeftGamePane) main.getGameRoot().getChildren().get(0)).updateStatus();
		}
		if(this.getnowClick().size()!=2) return ;
		if(this.getnowClick().get(0).getOwner()==this.getCurrentPlayer()) {
			if(((MonsterCard) this.getnowClick().get(0)).isAttackable(this.getnowClick().get(1))){
				((MonsterCard) this.getnowClick().get(0)).attack((MonsterCard) this.getnowClick().get(1));
			}
		}else {
			if(((MonsterCard) this.getnowClick().get(1)).isAttackable(this.getnowClick().get(0))){
				((MonsterCard) this.getnowClick().get(1)).attack((MonsterCard)this.getnowClick().get(0));
			}
		}
		this.getnowClick().clear();
		((GameFieldPane) main.getGameRoot().getChildren().get(1)).updateField();
		((LeftGamePane) main.getGameRoot().getChildren().get(0)).updateStatus();
	}
	

	
	public void updateMarket() {
    	while(this.getMonMarket().size()<3) {
    		if(this.getMonDeck().size()!=0) {
				BaseCard card = MonDeck.remove(0);
    			MonMarket.add(card);  
			}else {
				break;
			}
    	}
    	while(this.getSpMarket().size()<3) {
    		if(this.getSpDeck().size()!=0) {
				BaseCard card = SpDeck.remove(0);
    			SpMarket.add(card);  
			}else {
				break;
			}
    	}
    }
	
	public void updAtkani(String txt) {
		String gifp = "../gif/Claws.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(txt, gifp);
	}
	
	public void updProtectani() {
		String gifp = "../gif/protect.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate("Protect!", gifp);
	}
	
	public void updUseEffani(String effname) {
		String gifp = "../gif/Star.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(effname, gifp);
	}
	
	public void updBuyani() {
		String gifp = "../gif/lighting.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate("Field added!", gifp);
	}
	
	public void updTextani(String txt) {
		String gifp = "../gif/Loading.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(txt, gifp);
	}
	
	public void updani(String txt, String gifp) {
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(txt, gifp);
	}
}
