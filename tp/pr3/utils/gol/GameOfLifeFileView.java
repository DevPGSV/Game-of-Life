package tp.pr3.utils.gol;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;
import tp.pr3.logic.world.World;
import tp.pr3.utils.Utils;

public class GameOfLifeFileView extends FileView {
	
	
    
    public String getName(File file) {
        return null; //let the L&F FileView figure this out
    }

    public String getDescription(File file) {
        return null; //let the L&F FileView figure this out
    }

    public Boolean isTraversable(File file) {
        return null; //let the L&F FileView figure this out
    }

    public String getTypeDescription(File file) {
        if (Utils.checkIfCorrectExtension(file.getAbsolutePath())) {
        	return "'Game of life' file";
        } else {
        	return null;
        }
    }
    
    public Icon getIcon(File file) {
    	if (Utils.checkIfCorrectExtension(file.getAbsolutePath())) {
    		String text = "";
    		Color color = Color.BLUE;
        	try {
				World w = World.load(new Scanner(file));
				text = w.getWorldTypeAsString() + "(" + w.getRows() + "x" + w.getColumns() + ")";
			} catch (Exception e) {
				text = "Invalid file";
				color = Color.RED;
			}
    		Font font = new Font("Tahoma", Font.PLAIN, 11);
    		FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);
    		Rectangle2D bounds = font.getStringBounds(text, fontRenderContext);
    		int imageWidth = (int) bounds.getWidth() + 10;
    		int imageHeight = (int) bounds.getHeight() + 6;
    		
    		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    		Graphics2D g = image.createGraphics();
    		g.setColor(Color.WHITE);
    		g.setFont(font);
    		g.fillRect(0, 0, imageWidth, imageHeight);
    		g.setColor(color);
    		g.drawString(text, (float) bounds.getX() + 5, (float) -bounds.getY() + 3);
    		g.dispose();
    		return new ImageIcon(image);
        }
    	return null;
    }
    
    
    
}
