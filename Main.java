package fourier_transform;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
/**
 *
 * @author Daniel Marzari
 */
public class Main extends Canvas{
    static fourierTransform ft = new fourierTransform();
    static fourierSettings fs = new fourierSettings();
    
    sampleWave sw = new sampleWave(fs.frequencies, fs.amplitudes, fs.phases, fs.sampleRate, fs.graphRateMultiplyer);
    
    double[][] values = ft.fourierTransform(fs, sw);
    int currentX, currentY, nextX, nextY;
    boolean done = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Main();
        canvas.setBackground(Color.white);
        canvas.setSize(820, 720);
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void paint(Graphics g) {
        if(!done){
            done = true;
            playSounds();
        }
        drawBoarder(g);
        drawFourier(g);
        drawFunction(g);
    }
    
    public void playSounds(){
        Thread[] threads = new Thread[fs.frequencies.length];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new soundThread(fs.frequencies[i], fs.amplitudes[i], fs.phases[i], fs.audioDuration));
            threads[i].start();
        }
    }
    
    public void drawBoarder(Graphics g){
        g.drawLine(10, 10, 10, 310);
        g.drawLine(10, 160, 810, 160); //midline
        g.drawLine(10, 410, 10, 710);
        g.drawLine(10, 560, 810, 560); //midline
    }
    
    public void drawFourier(Graphics g){
        double scaleX = 800 / (fs.max_detectedFrequency - fs.min_detectedFrequency);
        double scaleY = 150 / sw.maxAmplitude; //height / Max amplitude = 1;
        for(int i = 0; i < values[2].length; i++){
            currentX = 10 + (int) Math.floor((i) * scaleX);
            currentY = 10 + (150 - (int) Math.floor(values[2][i] * scaleY));
            g.setColor(Color.GREEN);
            g.drawLine(currentX, 160, currentX, currentY);
            if(values[2][i] >= .9){
                g.setColor(Color.MAGENTA);
                g.drawString((i + fs.min_detectedFrequency) + "", (int)(currentX - Math.ceil(3 * Math.log10(i + fs.min_detectedFrequency))), 172);
            }else if (i == 0){
                g.setColor(Color.MAGENTA);
                g.drawString(fs.min_detectedFrequency + "", (int)(currentX - Math.ceil(3 * Math.log10(i + fs.min_detectedFrequency))), 172);
            }else if (i == values[2].length - 1){
                g.setColor(Color.MAGENTA);
                g.drawString(fs.max_detectedFrequency + "", (int)(currentX - Math.ceil(3 * Math.log10(i + fs.min_detectedFrequency))), 172);
            }
        }
    }
    
    public void drawFunction(Graphics g){
        g.setColor(Color.BLUE);
        double scaleX = (double) 798 / sw.pointArr.length;
        double scaleY = 150 / sw.maxAmplitude;
        //first point
        currentX = 11;
        currentY = 410 + (150 - (int) Math.floor(sw.pointArr[0] / fs.frequencies.length * scaleY));
        g.fillOval(currentX - 2, currentY - 2, 4, 4);
        
        for(int i = 1; i < sw.pointArr.length; i++){
            if(i % fs.graphRateMultiplyer == 0){
                g.fillOval(currentX - 2, currentY - 2, 4, 4);
            }
            nextX = 11 + (int) Math.floor((i) * scaleX);
            nextY = 410 + (150 - (int) Math.floor(sw.pointArr[i] / fs.frequencies.length * scaleY));
            g.drawLine(currentX, currentY, nextX, nextY);
            currentX = nextX;
            currentY = nextY;
        }
        //last point;
        g.fillOval(currentX - 2, currentY - 2, 4, 4);
    }
}
