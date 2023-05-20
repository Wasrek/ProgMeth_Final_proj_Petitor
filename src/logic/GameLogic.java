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
/**
 * Game System logic
 * @author Wishmeluck
 *
 */
public class GameLogic {
    /**
     * Game System Logic
     */
    private final int initialPlayerCount = 2;
    /**
     * Monster cards in the deck 
     */
    /**
     * Spell cards in the deck
     */
    /**
     * Monster cards in the market
     */
    /**
     * Spell cards in the market
     */
    /**
     * Cards clicked
     */
    private final ArrayList<BaseCard> MonDeck, SpDeck, MonMarket, SpMarket, nowClick;
    /**
     * Current player
     */
    private int currentPlayer;
    /**
     * Current turn
     */
    /**
     * Current phrase in the turn
     */
    /**
     * Summation of the clicked cards price
     */
    private int turnCount, nowPhrase, sumPrice;
    /**
     * ArrayList of players
     */
    private final ArrayList<Player> players;

	/**
	 * create game GameLogic instance
	 */
	private static GameLogic instance = null;

    /**
     * Constructor of GameLogic
     * @param initialPlayerCount	number of players
     */
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

    /**
     * Getter for instance
     * @return instance
     */
    public static GameLogic getInstance() {
        if(instance == null) {
            instance = new GameLogic(2);
        }
        return instance;
    }

    /**
     *  Getter for instance
     * @param initialPlayerCount	number of players
     * @return	instance
     */
    public static GameLogic getInstance(int initialPlayerCount) {
    	System.out.println("getInstance");
        if(instance == null) {
            instance = new GameLogic(initialPlayerCount);
        }
        return instance;
    }

    /**
     * Clear instance
     */
    public static void clearInstance() {
        instance = null;
    }

    /**
     * Initialize game such as card decks, player values, etc.
     */
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

    /**
     * Getter for MonDeck
     * @return	MonDeck
     */
    public ArrayList<BaseCard> getMonDeck() {
        return MonDeck;
    }
    
    /**
     * Getter for SpDeck
     * @return	SpDeck
     */
    public ArrayList<BaseCard> getSpDeck() {
        return SpDeck;
    }

    /**
     * Getter for currentPlayer (number)
     * @return	currentPlayer (number)
     */
    public int getCurrentPlayer() {
    	return currentPlayer;
    }
    
    /**
     * Getter for currentPlayer (Player class)
     * @return	currentPlayer (Player class)
     */
    public Player getCur() {
    	return getInstance().getPlayers().get(getInstance().getCurrentPlayer());
    }
    
    /**
     * Get opposite player (number)
     * @return	opposite player (number)
     */
    public int getOppositePlayer() {
    	return Math.floorMod(getCurrentPlayer()+1, 2);
    }
    /**
     * Get opposite player (Player class)
     * @return	opposite player (Player class)
     */
    public Player getOpp() {
    	return getInstance().getPlayers().get(getInstance().getOppositePlayer());
    }

    /**
     * change player and add money to the player
     */
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
    
    /**
     * Change phrase
     */
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

	/**
	 * Getter for players
	 * @return	players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Getter for turnCount
	 * @return	turnCount
	 */
	public int getTurnCount() {
		return turnCount;
	}

	/**
	 * Setter for turnCount
	 * @param turnCount	current turn
	 */
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	/**
	 * Getter for MonMarket
	 * @return	MonMarket
	 */
	public ArrayList<BaseCard> getMonMarket() {
		return MonMarket;
	}

	/**
	 * Getter for SpMarket
	 * @return	SpMatket
	 */
	public ArrayList<BaseCard> getSpMarket() {
		return SpMarket;
	}
	
	/**
	 * Getter for nowClick
	 * @return	nowClick
	 */
	public ArrayList<BaseCard> getnowClick() {
		return nowClick;
	}

	/**
	 * Getter for nowPhrase
	 * @return	nowPhrase
	 */
	public int getNowPhrase() {
		return nowPhrase;
	}

	/**
	 * Setter for nowPhrase
	 * @param nowPhrase	Current phrase
	 */
	public void setNowPhrase(int nowPhrase) {
		this.nowPhrase = nowPhrase;
	}

	/**
	 * Get the text represents each phrase
	 * @return	Text for each phrase
	 */
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
	
	/**
	 * Getter for sumPrice
	 * @return	sumPrice
	 */
	public int getSumPrice() {
		return sumPrice;
	}

	/**
	 * Setter for sumPrice
	 * @param sumPrice	clicked cards price total
	 */
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * method for calling buying a card function and set the values
	 */
	public void buyClick() {
		this.getCur().buy(getnowClick());
		this.getnowClick().clear();
		((RightGamePane) main.getGameRoot().getChildren().get(2)).updateRightPane();
		((GameFieldPane) main.getGameRoot().getChildren().get(1)).updateField();
		((LeftGamePane) main.getGameRoot().getChildren().get(0)).updateStatus();
	}
	
	/**
	 * method for calling using a card effect function and set the values
	 */
	public void useEffClick() {
		// TODO Auto-generated method stub
		this.getnowClick().get(0).useEffect();
		this.getnowClick().clear();
		((GameFieldPane) main.getGameRoot().getChildren().get(1)).updateField();
		((LeftGamePane) main.getGameRoot().getChildren().get(0)).updateStatus();
	}

	/**
	 * method for attacking function and set the values
	 */
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
	

	
	/**
	 * update cards in the market, fill the cards in the market 
	 */
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
	
	/**
	 * Update animation to attacking animation
	 * @param txt	text to be showed
	 */
	public void updAtkani(String txt) {
		String gifp = "../gif/Claws.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(txt, gifp);
	}
	
	/**
	 * Update animation to card protecting animation
	 */
	public void updProtectani() {
		String gifp = "../gif/protect.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate("Protect!", gifp);
	}
	
	/**
	 * Update animation to using effect animation
	 * @param effname	text describes effect
	 */
	public void updUseEffani(String effname) {
		String gifp = "../gif/Star.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(effname, gifp);
	}
	
	/**
	 * Update animation to buying a card animation
	 */
	public void updBuyani() {
		String gifp = "../gif/lighting.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate("Summon!", gifp);
	}
	
	/**
	 * Update animation to selected text
	 * @param txt text to be showed
	 */
	public void updTextani(String txt) {
		String gifp = "../gif/Loading.gif";
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(txt, gifp);
	}
	
	/**
	 * Update animation to selected text and animation
	 * @param txt	text to be showed
	 * @param gifp	animation image to be showed
	 */
	public void updani(String txt, String gifp) {
		((AnimatePane) ((RightGamePane) main.getGameRoot().getChildren().get(2)).getChildren().get(3)).setupdate(txt, gifp);
	}
}
