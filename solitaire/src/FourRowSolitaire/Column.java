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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 * Class: Column
 *
 * Description: The Column class manages a single column on the board.
 *
 * @author Matt Stephen
 */
public class Column extends CardStack
{
	private Card previewCard;
	private int previewIndex;

	public Column() {}

	@Override
	public Card push(Card card)
	{
		if(isEmpty() && card.getNumber() == Card.KING)
		{
			super.push(card);
			return card;
		}
		else if(card.getColor() != peek().getColor() && card.getNumber() == peek().getNumber() - 1)
		{
			super.push(card);
			return card;
		}

		return null;
	}

	@Override
	public boolean isValidMove(Card card)
	{
		if(isEmpty() && card.getNumber() == Card.KING)
		{
			return true;
		}
		else if(!isEmpty() && card.getColor() != peek().getColor() && card.getNumber() == (peek().getNumber() - 1))
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean isValidMove(CardStack stack)
	{
		return isValidMove(stack.peek());
	}

	public Card previewCard(Point p)
	{
		Object[] cardAndIndex = super.getCardAtLocationPreview(p);

		if(cardAndIndex == null)
		{
			return null;
		}

		previewCard = (Card)cardAndIndex[0];
		previewIndex = (Integer)cardAndIndex[1];

		repaint();
		revalidate();
		return previewCard;
	}

	public boolean stopPreview()
	{
		previewCard = null;
		repaint();
		revalidate();
		return true;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		if(previewCard != null)
		{
			Image image = previewCard.getImage();
			g.drawImage(image, 0, previewIndex * 25, null);
		}
	}
}