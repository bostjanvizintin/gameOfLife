package tilen.Threads;

import tilen.Logic;

public class Vzporedno extends Thread{
	
	private int width;
	private int height;
	private int pixels[];
	private int from;
	private int to;
	
	private Logic logic;
	
	
	public Vzporedno(int width, int height, int[] pixels, int from, int to, Logic logic) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
		this.from = from;
		this.to = to;
		
		this.logic = logic;
	}

	@Override
	public void run() {
		for (int y = from; y < to; y++) {
			for (int x = 0; x < width; x++) {
				try {
					if(pixels[(x+y*width)+1] + pixels[(x+y*width)-1] + pixels[(x+y*width)+width] + pixels[(x+y*width)-width] + pixels[(x+y*width)-width-1] + pixels[(x+y*width)-width+1] + pixels[(x+y*width)+width-1] + pixels[(x+y*width)+width+1] < 0xffffff+0xffffff) {
						pixels[(x+y*width)] = 0x000000;
					} else if (pixels[(x+y*width)+1] + pixels[(x+y*width)-1] + pixels[(x+y*width)+width] + pixels[(x+y*width)-width] + pixels[(x+y*width)-width-1] + pixels[(x+y*width)-width+1] + pixels[(x+y*width)+width-1] + pixels[(x+y*width)+width+1] > 0xffffff+0xffffff+0xffffff ){
						pixels[(x+y*width)] = 0x000000;
					} else if(pixels[(x+y*width)+1] + pixels[(x+y*width)-1] + pixels[(x+y*width)+width] + pixels[(x+y*width)-width] + pixels[(x+y*width)-width-1] + pixels[(x+y*width)-width+1] + pixels[(x+y*width)+width-1] + pixels[(x+y*width)+width+1] == 0xffffff+0xffffff+0xffffff) {
						pixels[(x+y*width)] = 0xffffff;
					}
					
				} catch(Exception e){
					
				}
			}
		}
		//return values
		logic.setVzporednoResults(pixels, from, to);
		
	}

}
