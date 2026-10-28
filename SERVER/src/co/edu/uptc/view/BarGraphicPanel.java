package co.edu.uptc.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import co.edu.uptc.model.Member;

public class BarGraphicPanel extends JPanel {

	private ChartsPanel chartsPanel;
	private JLabel titleLabel;

	private int[] realData = new int[6];
	private String[] ageCategories = {"< 20", "21-30", "31-40", "41-50", "51-60", "60+"};

	private Color[] colors = {
			new Color(107, 89, 204),
			new Color(38, 222, 129), 
			new Color(252, 92, 101), 
			new Color(254, 202, 87), 
			new Color(75, 123, 236), 
			new Color(165, 94, 234)  
	};

	public BarGraphicPanel(ChartsPanel chartsPanel) {
		this.chartsPanel = chartsPanel;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(37, 40, 54));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(45, 48, 62)),new EmptyBorder(10, 10, 10, 10)));

		titleLabel = new JLabel("Usuarios por Rango de Edad");
		titleLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
		add(titleLabel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		refreshChartData();
		createData(realData, ageCategories, g, colors);
	}

	private void refreshChartData() {

		try {
			List<Member> members =Member.getMembersList();
			if (members == null) {
				members =new java.util.ArrayList<>();
			}

			for (int i = 0;i < realData.length;i++) {
				realData[i] = 0;
			}

			for (Member m : members) {
				int age =m.getAge();

				if (age <= 20) {
					realData[0]++;

				} else if (age <= 30) {
					realData[1]++;

				} else if (age <= 40) {
					realData[2]++;

				} else if (age <= 50) {
					realData[3]++;

				} else if (age <= 60) {
					realData[4]++;

				} else {
					realData[5]++;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void createData(int[] data, String[] txt, Graphics g, Color[] colors) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int maxValor = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > maxValor) {
				maxValor = data[i];
			}
		}

		if (maxValor == 0) maxValor = 1; 

		int topMargin = 65; 
		int bottomMargin = 30; 
		int margenLateral = 15;

		int lineBaseY = getHeight() - bottomMargin;
		int heightMax = getHeight() - bottomMargin - topMargin;

		int totalSpaceX = getWidth() - (margenLateral * 2);
		int spacePerBar = totalSpaceX / data.length;

		int barWidth = (int)(spacePerBar * 0.6); 

		if (barWidth > 40) barWidth = 40; 

		int separation = spacePerBar - barWidth;

		int totalWidthGraphic = (barWidth + separation) * data.length - separation;
		int startX = (getWidth() - totalWidthGraphic) / 2;

		for (int i = 0; i < data.length; i++) {

			int heightBar = (int) (((double) data[i] / maxValor) * heightMax);
			int x = startX + (i * (barWidth + separation));
			int y = lineBaseY - heightBar;

			g2.setColor(colors[i % colors.length]); 
			g2.fillRoundRect(x, y, barWidth, heightBar, 10, 10);

			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", Font.BOLD, 12));
			String textValue = String.valueOf(data[i]);
			int textWidth = g2.getFontMetrics().stringWidth(textValue);
			int textX = x + (barWidth / 2) - (textWidth / 2); 
			int textY = y - 8; 
			g2.drawString(textValue, textX, textY);

			g2.setColor(Color.LIGHT_GRAY);
			g2.setFont(new Font("Arial", Font.PLAIN, 11));
			String textMonth = txt[i];
			int widthTextMonth = g2.getFontMetrics().stringWidth(textMonth);
			int textoMesX = x + (barWidth / 2) - (widthTextMonth / 2);
			g2.drawString(textMonth, textoMesX, lineBaseY + 18);
		}
	}
}