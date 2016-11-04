package tilen;

import tilen.Threads.Vzporedno;

public class Logic{
	private int width, height;
	public int[] pixels;
	private int type;//1 za zaporedno 2 za vzporedno 3 za porazdeljeno

	public Logic(int width, int height, int type) {
		this.type = type;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < pixels.length; i++) {
			if(Math.random() > 0.8){
				pixels[i] = 0xffffff;
			} else {
				pixels[i] = 0x000000;
			}
		
		}
	}
	
	public void render() {
		switch(type){
		case 1: 
			renderZaporedno();
			break;
		case 2: 
			renderVzporedno();
			break;
		case 3: 
			renderPorazdeljeno();
			break;
		}
	}

	private void renderZaporedno() {
		for (int y = 0; y < height; y++) {
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
	}
	
	private void renderVzporedno() {
		int numOfThreads = height;
		Vzporedno thread[] = new Vzporedno[numOfThreads];
		
		for (int i = 0; i < numOfThreads; i++) {
			
		}
		
		
		//System.out.println(this.height);
		//TODO Make threads and assign work.
		
		for (int i = 0; i < height; i++) {
			thread[i] = new Vzporedno(width, height, pixels, i, i+1, this);
			thread[i].start();
		}
		

		for (int i = 0; i < thread.length; i++) {
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public synchronized void setVzporednoResults(int[] pixels, int from, int to) {
		for (int j = from; j < to; j++) {
			for (int i = 0; i < width; i++) {
				this.pixels[i+j*width] = pixels[i+j*width];
			}
		}
		
		
		
	}
	
	private void renderPorazdeljeno() {
		
	}

	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

}
