package module6;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.HashMap;

/** Implements a visual marker for cities on an earthquake map
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Elena Radkova
 */

public class CityMarker extends CommonMarker {
	
	public static int TRI_SIZE = 5;
	
	public CityMarker(Location location) {
		super(location);
	}
	
	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
	}

	public void drawMarker(PGraphics pg, float x, float y) {
		pg.pushStyle();
		pg.fill(150, 30, 30);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		if(getClicked()){
			pg.fill(255, 255, 255);
			pg.textSize(12);
			String quakecount = "Number of nearby earthquakes: " + String.valueOf(this.getProperty("threadcircle"));
			double average = Math.round((double)this.getProperty("averagemag"));
			String averagemag = "Average mag within threatCircle: " + String.valueOf(average);
			pg.rectMode(PConstants.CORNER);
			pg.rect(x, y+13, Math.max(pg.textWidth(quakecount), pg.textWidth(averagemag))+5,36);
			pg.fill(0, 0, 0);
			pg.textAlign(PConstants.LEFT, PConstants.TOP);
			pg.text(quakecount, x+3, y+13);
			pg.text(averagemag, x+3, y+27);

			HashMap<String, Object> map = this.getProperties();
			int i=1;
			for(String s:map.keySet()){
				if(s.equals(String.valueOf(i))){
					float[] dist = (float[]) map.get(s);
					pg.line(x, y, x+dist[0], y+dist[1]);
					i++;
				}
			}
		}
		else{
			pg.noFill();
			pg.noStroke();
			pg.rect(x, y, 50, 50);
		}
		pg.popStyle();
	}

	public void showTitle(PGraphics pg, float x, float y)
	{
		String name = getCity() + " " + getCountry() + " ";
		String pop = "Pop: " + getPopulation() + " Million";
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-TRI_SIZE-39, Math.max(pg.textWidth(name), pg.textWidth(pop)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-TRI_SIZE-33);
		pg.text(pop, x+3, y - TRI_SIZE -18);
		
		pg.popStyle();
	}
	
	private String getCity()
	{
		return getStringProperty("name");
	}
	
	private String getCountry()
	{
		return getStringProperty("country");
	}
	
	private float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}
}
