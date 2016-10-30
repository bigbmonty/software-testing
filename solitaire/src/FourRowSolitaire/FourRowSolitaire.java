/*
	This file is a part of Four Row Solitaire

	Copyright (C) 2010-2014 by Matt Stephen

	Four Row Solitaire is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	Four Row Solitaire is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with FourRowSolitaire.  If not, see <http://www.gnu.org/licenses/>.
*/

package FourRowSolitaire;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.*;

/**
* Class: FourRowSolitaire
*
* Description: The FourRowSolitaire class adds a menu to the SolitaireBoard Frame.
*
* @author Matt Stephen
*/
public class FourRowSolitaire extends SolitaireBoard implements ActionListener
{
	public static final String version = ".76";

	private final JMenuBar menuBar = new JMenuBar();

	private final JMenu game = new JMenu("Game");
	private final JMenu helpMenu = new JMenu("Help");

	private final JMenuItem newGame = new JMenuItem("New Game");
	private final JMenuItem undo = new JMenuItem("Undo Last Move");
	private final JMenuItem hint = new JMenuItem("Hint");
	private final JMenuItem statistics = new JMenuItem("Statistics");
	private final JMenuItem options = new JMenuItem("Options");
	private final JMenuItem appearance = new JMenuItem("Change Appearance");
	private final JMenuItem topTimes = new JMenuItem("Best Times");
	private final JMenuItem exit = new JMenuItem("Exit");

	private final JMenuItem help = new JMenuItem("View Help");
	private final JMenuItem about = new JMenuItem("About Game");
	private final JMenuItem checkUpdate = new JMenuItem("Check for Updates");

	public FourRowSolitaire()
	{
		setup();
		loadData();

		int intvers = super.getCheckForUpdates();
		int curintvers = (int)(Double.parseDouble(version) * 100.0);
		if(intvers != curintvers)
		{
			checkForUpdate();
		}
	}

	private void setup()
	{
		game.add(newGame);
		game.addSeparator();
		game.add(undo);
		game.add(hint);
		game.addSeparator();
		game.add(statistics);
		game.add(options);
		game.add(appearance);
		game.add(topTimes);
		game.addSeparator();
		game.add(exit);

		newGame.addActionListener(this);
		undo.addActionListener(this);
		hint.addActionListener(this);
		statistics.addActionListener(this);
		options.addActionListener(this);
		appearance.addActionListener(this);
		topTimes.addActionListener(this);
		exit.addActionListener(this);

		helpMenu.add(help);
		helpMenu.add(about);
		helpMenu.add(checkUpdate);

		help.addActionListener(this);
		about.addActionListener(this);
		checkUpdate.addActionListener(this);

		menuBar.add(game);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		newGame.setMnemonic('N');
		newGame.setAccelerator(KeyStroke.getKeyStroke("F2"));
		undo.setMnemonic('u');
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		hint.setMnemonic('h');
		hint.setAccelerator(KeyStroke.getKeyStroke('h'));
		statistics.setMnemonic('s');
		statistics.setAccelerator(KeyStroke.getKeyStroke("F4"));
		options.setMnemonic('o');
		options.setAccelerator(KeyStroke.getKeyStroke("F5"));
		appearance.setMnemonic('a');
		appearance.setAccelerator(KeyStroke.getKeyStroke("F7"));
		topTimes.setMnemonic('b');
		topTimes.setAccelerator(KeyStroke.getKeyStroke("F8"));
		exit.setMnemonic('x');

		help.setMnemonic('v');
		help.setAccelerator(KeyStroke.getKeyStroke("F1"));
		about.setMnemonic('a');
	}

	private void checkForUpdate()
	{
		try
		{
			URL url = new URL("http://www.mastadisasta.com/FourRowSolitaire/version.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String inputLine = in.readLine();
			in.close();

			//Sometimes reads an html document if disconnected from internet
			if(!inputLine.contains("DOCTYPE") && !version.equals(inputLine))
			{
				String[] updateOptions = new String[]{"Get It","Not Now","Don't Remind Me Again"};
				int updateChoice = JOptionPane.showOptionDialog(this, "There is a newer version available.",
					"Newer Version Available", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, updateOptions, updateOptions[0]);

				if(updateChoice == 0)
				{
					checkUpdate.doClick();
				}
				else if(updateChoice == 2)
				{
					int intvers = (int)(Double.parseDouble(version) * 100.0);
					super.setCheckForUpdates(intvers);
				}
				else
				{
					//do nothing
				}
			}
		}
		catch(IOException ex){}
		catch(HeadlessException ex){}
		catch (NumberFormatException ex){}
	}

	private void loadData()
	{
		String fileLocation = System.getProperty("user.home") + System.getProperty("file.separator");
		int count = 0, correctedStatistics = -2;
		int temp;
		int newDrawCount = 1, timerStatus = 0, deckNumber = 3, backgroundNumber = 2;
		int saved = 0, winAnimation = 0, winSounds = 0, drawCount = 1, deckThroughs = 1;
		int difficulty = 2, newDifficulty = 2;
		int numViewableCards = 1;
		int checkForUpdates = 1;

		try
		{
			File file = new File(fileLocation + "frs-statistics.dat");
			file.createNewFile();
			DataInputStream input = new DataInputStream(new FileInputStream(file));

			if(input.available() > 0)
			{
				correctedStatistics = input.readInt();
				count++;
			}

			if(correctedStatistics == -2)
			{
				//No statistics file found
			}
			else if(correctedStatistics == -1)
			{
				//Statistics file is formatted to the new style of saving statistics
				while((input.available() > 0) && count < 44)
				{
					temp = input.readInt();
					switch(count)
					{
						case 31: drawCount = temp; break;
						case 32: newDrawCount = temp; break;
						case 33: deckNumber = temp; break;
						case 34: backgroundNumber = temp; break;
						case 35: timerStatus = temp; break;
						case 36: winAnimation = temp; break;
						case 37: winSounds = temp; break;
						case 38: deckThroughs = temp; break;
						case 39: difficulty = temp; break;
						case 40: newDifficulty = temp; break;
						case 41: numViewableCards = temp; break;
						case 42: saved = temp; break;
						case 43: checkForUpdates = temp; break;

						default: ; break;
					}

					count++;
				}
			}
			else
			{
				//Statistics file is formatted to the old style of saving statistics
				while((input.available() > 0) && count < 14)
				{
					temp = input.readInt();
					switch(count)
					{
						case 5: newDrawCount = temp; break;
						case 6: timerStatus = temp; break;
						case 7: deckNumber = temp; break;
						case 8: backgroundNumber = temp; break;
						case 9: saved = temp; break;
						case 10: winAnimation = temp; break;
						case 11: winSounds = temp; break;
						case 12: drawCount = temp; break;
						case 13: deckThroughs = temp; break;

						default: ; break;
					}

					count++;
				}
			}

			input.close();
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}

		super.setDeckNumber(deckNumber);
		super.setBackgroundNumber(backgroundNumber);
		super.setTimerStatus(timerStatus);
		super.setNewDrawCount(newDrawCount);
		super.setWinAnimationStatus(winAnimation);
		super.setWinSoundsStatus(winSounds);
		super.setDrawCount(drawCount);
		super.setDifficulty(difficulty);
		super.setNewDifficulty(newDifficulty);
		super.setCheckForUpdates(checkForUpdates);

		if(saved == 1)
		{
			super.setDeckThroughs(deckThroughs);
			LinkedList<Integer> cards = new LinkedList<Integer>();

			try
			{
				File file = new File(fileLocation + "frs-savedgame.dat");
				file.createNewFile();
				DataInputStream input = new DataInputStream(new FileInputStream(file));

				while(input.available() > 0)
				{
					cards.add(input.readInt());
				}

				if(cards.size() == 67)
				{
					super.setTimer(cards.pollLast());
					super.createBoard(cards, numViewableCards);
				}
				else
				{
					System.err.println("Problem Loading Saved Game (More or Less Than 52 Cards Stored)... Starting New Game");
					super.createBoard(null, 1);
				}
			}
			catch(IOException ex)
			{
				System.err.println("Problem Loading Saved Game (Unknown Error)... Starting New Game");
				super.createBoard(null, 1);
			}
		}
		else
		{
			super.createBoard(null, 1);
		}

		if(correctedStatistics != -1)
		{
			super.saveOptions();
		}
	}

	public static void main(String[] args)
	{
		FourRowSolitaire fourRowSolitaire = new FourRowSolitaire();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newGame)
		{
			super.newGame(0);
		}
		else if(e.getSource() == undo)
		{
			super.undoMove();
		}
		else if(e.getSource() == hint)
		{
			super.getHint();
		}
		else if(e.getSource() == statistics)
		{
			String fileLocation = System.getProperty("user.home") + System.getProperty("file.separator") +
				"frs-statistics.dat";

			int count = 0;
			int temp;
			int gamesPlayed1e = 0, gamesWon1e = 0, winStreak1e = 0, lossStreak1e = 0,
			currentStreak1e = 0;
			int gamesPlayed1m = 0, gamesWon1m = 0, winStreak1m = 0, lossStreak1m = 0,
			currentStreak1m = 0;
			int gamesPlayed1h = 0, gamesWon1h = 0, winStreak1h = 0, lossStreak1h = 0,
			currentStreak1h = 0;
			int gamesPlayed3e = 0, gamesWon3e = 0, winStreak3e = 0, lossStreak3e = 0,
			currentStreak3e = 0;
			int gamesPlayed3m = 0, gamesWon3m = 0, winStreak3m = 0, lossStreak3m = 0,
			currentStreak3m = 0;
			int gamesPlayed3h = 0, gamesWon3h = 0, winStreak3h = 0, lossStreak3h = 0,
			currentStreak3h = 0;

			try
			{
				File file = new File(fileLocation);
				file.createNewFile();
				DataInputStream input = new DataInputStream(new FileInputStream(file));

				while((input.available() > 0) && count < 31)
				{
					temp = input.readInt();
					switch(count)
					{
						case 1: gamesPlayed1e = temp; break;
						case 2: gamesWon1e = temp; break;
						case 3: winStreak1e = temp; break;
						case 4: lossStreak1e = temp; break;
						case 5: currentStreak1e = temp; break;

						case 6: gamesPlayed1m = temp; break;
						case 7: gamesWon1m = temp; break;
						case 8: winStreak1m = temp; break;
						case 9: lossStreak1m = temp; break;
						case 10: currentStreak1m = temp; break;

						case 11: gamesPlayed1h = temp; break;
						case 12: gamesWon1h = temp; break;
						case 13: winStreak1h = temp; break;
						case 14: lossStreak1h = temp; break;
						case 15: currentStreak1h = temp; break;

						case 16: gamesPlayed3e = temp; break;
						case 17: gamesWon3e = temp; break;
						case 18: winStreak3e = temp; break;
						case 19: lossStreak3e = temp; break;
						case 20: currentStreak3e = temp; break;

						case 21: gamesPlayed3m = temp; break;
						case 22: gamesWon3m = temp; break;
						case 23: winStreak3m = temp; break;
						case 24: lossStreak3m = temp; break;
						case 25: currentStreak3m = temp; break;

						case 26: gamesPlayed3h = temp; break;
						case 27: gamesWon3h = temp; break;
						case 28: winStreak3h = temp; break;
						case 29: lossStreak3h = temp; break;
						case 30: currentStreak3h = temp; break;

						default: ; break;
					}

					count++;
				}

				input.close();
			}
			catch(IOException ex)
			{
				System.err.println(ex);
			}

			int winPercentage1e;
			int winPercentage1m;
			int winPercentage1h;
			int winPercentage3e;
			int winPercentage3m;
			int winPercentage3h;

			if(gamesPlayed1e == 0)
			{
				winPercentage1e = 0;
			}
			else
			{
				winPercentage1e = 100 * gamesWon1e / gamesPlayed1e;
			}

			if(gamesPlayed1m == 0)
			{
				winPercentage1m = 0;
			}
			else
			{
				winPercentage1m = 100 * gamesWon1m / gamesPlayed1m;
			}

			if(gamesPlayed1h == 0)
			{
				winPercentage1h = 0;
			}
			else
			{
				winPercentage1h = 100 * gamesWon1h / gamesPlayed1h;
			}

			if(gamesPlayed3e == 0)
			{
				winPercentage3e = 0;
			}
			else
			{
				winPercentage3e = 100 * gamesWon3e / gamesPlayed3e;
			}

			if(gamesPlayed3m == 0)
			{
				winPercentage3m = 0;
			}
			else
			{
				winPercentage3m = 100 * gamesWon3m / gamesPlayed3m;
			}

			if(gamesPlayed3h == 0)
			{
				winPercentage3h = 0;
			}
			else
			{
				winPercentage3h = 100 * gamesWon3h / gamesPlayed3h;
			}

			JTextArea display1e = new JTextArea();
			display1e.append("" +
				"One-Card Draw (Easy)" +
				"\nGames Played: " + gamesPlayed1e +
				"\nGames Won: " + gamesWon1e +
				"\nWin Percentage: " + winPercentage1e + "%" +
				"\n" +
				"\nBest Streak: " + winStreak1e +
				"\nWorst Streak: " + lossStreak1e +
				"\nCurrent Streak: " + currentStreak1e);

			JTextArea display1m = new JTextArea();
			display1m.append("" +
				"One-Card Draw (Medium)" +
				"\nGames Played: " + gamesPlayed1m +
				"\nGames Won: " + gamesWon1m +
				"\nWin Percentage: " + winPercentage1m + "%" +
				"\n" +
				"\nBest Streak: " + winStreak1m +
				"\nWorst Streak: " + lossStreak1m +
				"\nCurrent Streak: " + currentStreak1m);

			JTextArea display1h = new JTextArea();
			display1h.append("" +
				"One-Card Draw (Hard)" +
				"\nGames Played: " + gamesPlayed1h +
				"\nGames Won: " + gamesWon1h +
				"\nWin Percentage: " + winPercentage1h + "%" +
				"\n" +
				"\nBest Streak: " + winStreak1h +
				"\nWorst Streak: " + lossStreak1h +
				"\nCurrent Streak: " + currentStreak1h);

			JTextArea display3e = new JTextArea();
			display3e.append("" +
				"Three-Card Draw (Easy)" +
				"\nGames Played: " + gamesPlayed3e +
				"\nGames Won: " + gamesWon3e +
				"\nWin Percentage: " + winPercentage3e + "%" +
				"\n" +
				"\nBest Streak: " + winStreak3e +
				"\nWorst Streak: " + lossStreak3e +
				"\nCurrent Streak: " + currentStreak3e);

			JTextArea display3m = new JTextArea();
			display3m.append("" +
				"Three-Card Draw (Medium)" +
				"\nGames Played: " + gamesPlayed3m +
				"\nGames Won: " + gamesWon3m +
				"\nWin Percentage: " + winPercentage3m + "%" +
				"\n" +
				"\nBest Streak: " + winStreak3m +
				"\nWorst Streak: " + lossStreak3m +
				"\nCurrent Streak: " + currentStreak3m);

			JTextArea display3h = new JTextArea();
			display3h.append("" +
				"Three-Card Draw (Hard)" +
				"\nGames Played: " + gamesPlayed3h +
				"\nGames Won: " + gamesWon3h +
				"\nWin Percentage: " + winPercentage3h + "%" +
				"\n" +
				"\nBest Streak: " + winStreak3h +
				"\nWorst Streak: " + lossStreak3h +
				"\nCurrent Streak: " + currentStreak3h);

			display1e.setOpaque(false);
			display1e.setBorder(null);
			display1e.setFont(UIManager.getFont("Label.font"));

			display1m.setOpaque(false);
			display1m.setBorder(null);
			display1m.setFont(UIManager.getFont("Label.font"));

			display1h.setOpaque(false);
			display1h.setBorder(null);
			display1h.setFont(UIManager.getFont("Label.font"));

			display3e.setOpaque(false);
			display3e.setBorder(null);
			display3e.setFont(UIManager.getFont("Label.font"));

			display3m.setOpaque(false);
			display3m.setBorder(null);
			display3m.setFont(UIManager.getFont("Label.font"));

			display3h.setOpaque(false);
			display3h.setBorder(null);
			display3h.setFont(UIManager.getFont("Label.font"));

			ShowStatistics showstats = new ShowStatistics(this, super.getDrawCount(), display1e, display1m, display1h, display3e, display3m, display3h);
			int check = showstats.getClosingAction();
			showstats.dispose();

			if(check == ShowStatistics.RESET_ACTION)
			{
				//Reset stats
				super.resetStats();
			}
			else
			{
				//Close
			}
		}
		else if(e.getSource() == options)
		{
			ChangeOptions co = new ChangeOptions(this, super.getNewDrawCount(), super.getTimerNextGameStatus(), super.getWinAnimationStatus(), super.getWinSoundsStatus(), super.getNewDifficulty());
			int drawCount = co.getDrawCount();
			int timerStatus = co.getTimer();
			int animationStatus = co.getAnimation();
			int soundsStatus = co.getSounds();
			int difficulty = co.getDifficulty();

			if(drawCount != -1)
			{
				super.setNewDrawCount(drawCount);
				super.setTimerStatus(timerStatus);
				super.setWinAnimationStatus(animationStatus);
				super.setWinSoundsStatus(soundsStatus);
				super.setNewDifficulty(difficulty);
			}

			super.saveOptions();
		}
		else if(e.getSource() == appearance)
		{
			ChangeAppearance ca = new ChangeAppearance(this, super.getDeckNumber(), super.getBackgroundNumber());
			int deckNumber = ca.getDeckNumber();
			int backgroundNumber = ca.getBackgroundNumber();
			ca.dispose();

			if(deckNumber != -1)
			{
				super.setAppearance(deckNumber, backgroundNumber);
			}

			super.saveOptions();
		}
		else if(e.getSource() == topTimes)
		{
			TopTimes top = new TopTimes(this);
			top.setVisible(true);
		}
		else if(e.getSource() == exit)
		{
			super.wl.windowClosing(null);
		}

		else if(e.getSource() == help)
		{
			JOptionPane.showMessageDialog(this, "This game is a mixture of FreeCell and normal (Klondike) Solitaire.\n" +
				"\nSimilarities to FreeCell:\n" +
				"   -The Cards in the columns are always visible\n" +
				"   -There are four individual cells at the top\n" +
				"\nSimilarities to Solitaire:\n" +
				"   -There is a deck and discard pile to draw cards from\n" +
				"   -Only Kings may be placed at the top of an empty column\n" +
				"   -Cards may be removed from the Ace piles and placed back onto the playing field\n" +
				"   -Any number of cards can be moved in one move (as long as they are stacked as in Solitaire\n" +
				"\nFeatures unique to this game:\n" +
				"   -The four individual cells start with cards in them\n" +
				"   -You may only go through the deck twice on draw one and three times on draw three (difficulty may change)\n" +
				"   -The obvious: there are only four columns, not 7 or 8 as in Solitaire and FreeCell, respectively",
				"Help!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource() == about)
		{
			JOptionPane.showMessageDialog(this, "Four Row Solitaire was created and programmed by Matt Stephen.\n" +
				"\nYou can modify this code in accordance with GPL v3.0.\n" +
				"\nTo check if there is a newer version, go to fourrow.sourceforge.net.",
				"About Game", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource() == checkUpdate)
		{
			try
			{
				Desktop.getDesktop().browse(new URI("https://sourceforge.net/projects/fourrow"));
			}
			catch(URISyntaxException ex){}
			catch (IOException ex){}
		}
	}
}