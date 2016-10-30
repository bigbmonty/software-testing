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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class: ShowStatistics
 *
 * Description: The ShowStatistics class manages the display of statistics to the user.
 *
 * @author Matt Stephen
 */
public class ShowStatistics extends JDialog implements ActionListener
{
	private JPanel mainPanel;
	private JPanel[] statsPanels;

	BorderLayout mainLayout = new BorderLayout();

	private final JButton close = new JButton("Close");
	private final JButton reset = new JButton("Reset");

	private JComboBox<String> combobox;

	public static final int CLOSE_ACTION = 0;
	public static final int RESET_ACTION = 1;

	private int closingAction = CLOSE_ACTION;

	public ShowStatistics(Component parent, int drawCount, JTextArea stats1e, JTextArea stats1m,
			JTextArea stats1h, JTextArea stats3e, JTextArea stats3m, JTextArea stats3h)
	{
		setTitle("Statistics");
		setSize(550,250);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		setup(drawCount, stats1e, stats1m, stats1h, stats3e, stats3m, stats3h);

		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}

	private void setup(int drawCount, JTextArea stats1e, JTextArea stats1m, JTextArea stats1h,
		JTextArea stats3e, JTextArea stats3m, JTextArea stats3h)
	{
		String[] options = new String[]{"One-Card Draw", "Three-Card Draw"};
		int defaultOption = 0;
		if(drawCount == 3)
		{
			defaultOption = 1;
		}

		close.addActionListener(this);
		reset.addActionListener(this);

		combobox = new JComboBox<String>(options);
		combobox.setSelectedIndex(defaultOption);
		combobox.addActionListener(this);

		combobox.setMinimumSize(new Dimension(150,25));
		combobox.setMaximumSize(new Dimension(150,25));
		combobox.setPreferredSize(new Dimension(150,25));

		JPanel topPanel = new JPanel();
		topPanel.add(combobox);

		mainPanel = new JPanel(mainLayout);
		mainPanel.add(topPanel, BorderLayout.NORTH);

		statsPanels = new JPanel[2];

		stats1e.setMinimumSize(new Dimension(200,150));
		stats1e.setMaximumSize(new Dimension(200,150));
		stats1e.setPreferredSize(new Dimension(200,150));

		stats1m.setMinimumSize(new Dimension(200,150));
		stats1m.setMaximumSize(new Dimension(200,150));
		stats1m.setPreferredSize(new Dimension(200,150));

		stats1h.setMinimumSize(new Dimension(150,150));
		stats1h.setMaximumSize(new Dimension(150,150));
		stats1h.setPreferredSize(new Dimension(150,150));

		stats3e.setMinimumSize(new Dimension(200,150));
		stats3e.setMaximumSize(new Dimension(200,150));
		stats3e.setPreferredSize(new Dimension(200,150));

		stats3m.setMinimumSize(new Dimension(200,150));
		stats3m.setMaximumSize(new Dimension(200,150));
		stats3m.setPreferredSize(new Dimension(200,150));

		stats3h.setMinimumSize(new Dimension(150,150));
		stats3h.setMaximumSize(new Dimension(150,150));
		stats3h.setPreferredSize(new Dimension(150,150));

		JPanel drawonePanel = new JPanel(new BorderLayout());
		drawonePanel.add(stats1e, BorderLayout.WEST);
		drawonePanel.add(stats1m, BorderLayout.CENTER);
		drawonePanel.add(stats1h, BorderLayout.EAST);

		JPanel drawthreePanel = new JPanel(new BorderLayout());
		drawthreePanel.add(stats3e, BorderLayout.WEST);
		drawthreePanel.add(stats3m, BorderLayout.CENTER);
		drawthreePanel.add(stats3h, BorderLayout.EAST);

		statsPanels[0] = drawonePanel;
		statsPanels[1] = drawthreePanel;

		mainPanel.add(statsPanels[defaultOption], BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(close);
		buttonsPanel.add(reset);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	public int getClosingAction()
	{
		return closingAction;
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == close)
		{
			closingAction = CLOSE_ACTION;
			this.setVisible(false);
		}
		else if(e.getSource() == reset)
		{
			closingAction = RESET_ACTION;
			this.setVisible(false);
		}
		else if(e.getSource() == combobox)
		{
			int index = combobox.getSelectedIndex();
			mainPanel.remove(mainLayout.getLayoutComponent(BorderLayout.CENTER));
			mainPanel.add(statsPanels[index], BorderLayout.CENTER);
			mainPanel.repaint();
			mainPanel.revalidate();
		}
	}
}