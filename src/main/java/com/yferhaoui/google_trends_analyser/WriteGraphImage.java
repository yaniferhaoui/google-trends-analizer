package com.yferhaoui.google_trends_analyser;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.text.DateFormat;
import java.awt.Graphics2D;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class WriteGraphImage {

	private Integer scaleStringLine, nbMesure;
	private Double marginHeight, marginWidth, marginWidthGraph, marginHeightGraph, width, height, realHeight, realWidth,
			scaleWidth, scaleHeight, littleSpace;
	private String nameGraph;
	private WordTrend wordTrend;

	private Color backgroundColor, colorGraph, borderDeep;
	private Graphics2D ig2;
	private BufferedImage bi;

	public WriteGraphImage(WordTrend wordTrend) {
		this.wordTrend = wordTrend;
		this.nameGraph = this.wordTrend.getName();

		this.backgroundColor = new Color(235, 235, 235);
		this.colorGraph = new Color(25, 160, 218);
		this.borderDeep = new Color(40, 40, 40, 25);

		this.nbMesure = 100;
		this.height = 1800.0;
		this.width = 4900.0;

		this.realWidth = this.width;
		this.realHeight = this.height;

		this.marginWidthGraph = this.width * 0.095;
		this.marginHeightGraph = this.height * 0.25;

		this.marginWidth = this.marginWidthGraph * 0.8;
		this.marginHeight = this.marginHeightGraph * 0.8;
		this.littleSpace = this.marginHeight * 0.1;

		this.height = this.height + (this.marginHeightGraph * 2.0);
		this.width = this.width + (this.marginWidthGraph * 2.0);

		this.scaleWidth = this.realWidth / Double.valueOf(this.wordTrend.getTrends().size());
		this.scaleHeight = this.realHeight / Double.valueOf(this.nbMesure);
		this.scaleStringLine = (int) (this.realHeight / 580.0);

		this.bi = new BufferedImage(this.width.intValue(), this.height.intValue(), BufferedImage.TYPE_INT_ARGB);
		this.ig2 = this.bi.createGraphics();
	}

	public void drawGraph() {
		/*
		 * This function draw the default Graph
		 */
		this.ig2.setColor(this.backgroundColor);
		this.ig2.fillRect(0, 0, this.bi.getWidth(), this.bi.getHeight());

		this.drawLines();
		this.drawFonts(this.scaleStringLine * 10);
		this.drawCurves(this.scaleStringLine * 10);
	}

	public void drawLines() {

		this.ig2.setColor(Color.black);
		this.ig2.setStroke(new BasicStroke(this.scaleStringLine * 2));
		// x Line
		this.ig2.drawLine((int) (this.marginWidth - this.littleSpace), (int) (this.height - this.marginHeight),
				(int) (this.width - this.marginWidth), (int) (this.height - this.marginHeight));

		// y Line
		this.ig2.drawLine(this.marginWidth.intValue(), (int) (this.height - this.marginHeight + this.littleSpace),
				this.marginWidth.intValue(), this.marginHeight.intValue());

		// gray x and y lines
		Double spaceLinesY = this.realHeight / 8.0;
		Double spaceLinesX = this.realWidth / 8.0;
		this.ig2.setColor(this.borderDeep);
		this.ig2.setStroke(new BasicStroke(this.scaleStringLine));
		if (spaceLinesY > 0.0 && spaceLinesX > 0.0) {
			for (Double n = 0.0; n < 8.0; n++) {
				this.ig2.drawLine(this.marginWidth.intValue(),
						(int) (this.height - this.marginHeight - (spaceLinesY * n)),
						(int) (this.width - this.marginWidth),
						(int) (this.height - this.marginHeight - (spaceLinesY * n)));
				this.ig2.drawLine((int) (this.marginWidth + n * spaceLinesX), this.marginHeight.intValue(),
						(int) (this.marginWidth + n * spaceLinesX), (int) (this.height - this.marginHeight));
			}
		}
	}

	public void drawFonts(int heightFont) {
		// font for String
		this.ig2.setColor(Color.black);
		this.ig2.setFont(new Font("TimesRoman", Font.PLAIN, this.scaleStringLine * 15));

		this.ig2.drawString(this.nameGraph,
				(int) (this.width - this.littleSpace) - this.ig2.getFontMetrics().stringWidth(this.nameGraph),
				this.scaleStringLine * 15 + this.littleSpace.intValue());

		this.ig2.setFont(new Font("TimesRoman", Font.PLAIN, this.scaleStringLine * 12));

		String numberHeight = " Number of Height values";
		int widthFont = this.ig2.getFontMetrics().stringWidth(numberHeight);
		this.ig2.drawString(numberHeight, this.littleSpace.intValue(),
				this.scaleStringLine * 10 + this.littleSpace.intValue());
		this.ig2.drawString("  :  " + this.nbMesure, (int) (this.littleSpace * 2.0) + widthFont,
				this.scaleStringLine * 10 + this.littleSpace.intValue());

		String numberWidth = " Number of Width values";
		this.ig2.drawString(numberWidth, (int) (this.littleSpace * 10.0) + widthFont,
				this.scaleStringLine * 10 + this.littleSpace.intValue());
		String sizeNumberWidth = String.valueOf(this.wordTrend.getTrends().size());
		this.ig2.drawString("  :  " + sizeNumberWidth,
				(int) (this.littleSpace * 10.0) + widthFont + this.ig2.getFontMetrics().stringWidth(numberWidth),
				this.scaleStringLine * 10 + this.littleSpace.intValue());

		String heightTitle = " Height of the window";
		this.ig2.drawString(heightTitle, this.littleSpace.intValue(),
				(heightFont * 2) + (int) (this.littleSpace * 2.0));
		this.ig2.drawString("  :  " + this.realHeight.intValue(), (int) (this.littleSpace * 2.0) + widthFont,
				(heightFont * 2) + (int) (this.littleSpace * 2.0));

		this.ig2.drawString(" Width of the window", (int) (this.littleSpace * 10.0) + widthFont,
				(heightFont * 2) + (int) (this.littleSpace * 2.0));
		this.ig2.drawString("  :  " + this.realWidth.intValue(),
				(int) (this.littleSpace * 10.0) + widthFont + this.ig2.getFontMetrics().stringWidth(numberWidth),
				(heightFont * 2) + (int) (this.littleSpace * 2.0));

		this.ig2.setFont(new Font("TimesRoman", Font.PLAIN, this.scaleStringLine * 12));
		this.ig2.drawString("100",
				(int) (this.marginWidth - this.littleSpace) - this.ig2.getFontMetrics().stringWidth("100"),
				this.marginHeightGraph.intValue());
		this.ig2.drawString("0",
				(int) (this.marginWidth - this.littleSpace) - this.ig2.getFontMetrics().stringWidth("0"),
				(int) (this.height - this.marginHeightGraph));

	}

	public void drawCurves(int heightFont) {
		Iterator<Entry<Date, Integer>> it = this.wordTrend.getTrends().entrySet().iterator();

		if (it.hasNext()) {

			int y, x, yb, xb;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

			yb = (int) (this.marginHeightGraph + (this.scaleHeight
					* (this.nbMesure - this.wordTrend.getTrends().get(this.wordTrend.getTrends().firstKey()))));
			xb = this.marginWidthGraph.intValue();
			it.next();

			this.ig2.setColor(this.colorGraph);
			this.ig2.setStroke(new BasicStroke(this.scaleStringLine * 2));
			while (it.hasNext()) {

				Entry<Date, Integer> e = it.next();
				Integer theCurrentValue = e.getValue();

				y = (int) (this.marginHeightGraph + (this.scaleHeight * (this.nbMesure - theCurrentValue)));
				x = xb + this.scaleWidth.intValue();

				this.ig2.drawLine(xb, yb, x, y);
				yb = y;
				xb = x;
			}

			int n = 0;
			it = this.wordTrend.getTrends().entrySet().iterator();
			while (it.hasNext()) {

				Entry<Date, Integer> e = it.next();
				Date theCurrentDate = e.getKey();
				Integer theCurrentValue = e.getValue();

				if (n % 4 == 0) {
					this.drawCircle(theCurrentDate, theCurrentValue);
				}
				n++;
			}

			// last value (price-Date) of the graph
			Date theLastDate = this.wordTrend.getTrends().lastKey();
			this.ig2.setColor(Color.black);
			this.ig2.setFont(new Font("TimesRoman", Font.PLAIN, this.scaleStringLine * 12));

			this.ig2.drawString(df.format(this.wordTrend.getTrends().firstKey()), this.marginWidth.intValue(),
					(int) (this.height - this.marginHeight + this.littleSpace) + heightFont);

			this.ig2.drawString(df.format(theLastDate),
					(int) (this.width - this.marginWidth)
							- this.ig2.getFontMetrics().stringWidth(df.format(theLastDate)),
					(int) (this.height - this.marginHeight + this.littleSpace) + this.scaleStringLine * 10);
		}
	}

	public void drawCircle(Date theDate, Integer valueHeight) {
		/*
		 * This function draw a Circle and a String for show a new position on the graph
		 * (sell or buy or start price)
		 */
		int y = (int) (this.marginHeightGraph + (this.scaleHeight * (this.nbMesure - valueHeight)));
		int x = this.marginWidthGraph.intValue() + (this.getIndex(theDate) * this.scaleWidth.intValue());

		this.ig2.setColor(Color.black);
		this.ig2.setStroke(new BasicStroke(this.scaleStringLine * 2));
		this.ig2.drawOval(x - this.scaleStringLine * 4, y - this.scaleStringLine * 4, this.scaleStringLine * 8,
				this.scaleStringLine * 8);

		this.ig2.setFont(new Font("TimesRoman", Font.PLAIN, this.scaleStringLine * 10));
		this.ig2.drawString(String.valueOf(valueHeight), x - this.scaleStringLine * 4, y - this.scaleStringLine * 8);

	}

	public int getIndex(Date theDate) {
		/*
		 * This function return the index of the element in wordTrend which have the key
		 * "theDate"
		 */
		Iterator<Entry<Date, Integer>> it = this.wordTrend.getTrends().entrySet().iterator();
		int n = 0;

		while (it.hasNext()) {
			Date theCurrentDate = it.next().getKey();
			if (theDate.compareTo(theCurrentDate) == 0) {
				return n;
			}
			n++;
		}
		return -1;
	}

	public void createImage(String pathImg) {
		/*
		 * This function generate the image in a file.png
		 */
		try {
			ImageIO.write(this.bi, "PNG", new File(pathImg));
			System.out.println("   Create image of the Graph : " + this.nameGraph);
		} catch (Exception e) {
			// just send a mail
			// EmailSender.sendMailError(e, StaticGeneral.pathReport);
		}
		System.gc();
		System.runFinalization();
	}
}
