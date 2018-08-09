/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourier_transform;

/**
 *
 * @author Daniel
 */
public class fourierSettings {
    static int[] frequencies = new int[] {1,27};
    static double[] amplitudes = new double[] {2,1};
    static double[] phase = new double[] {0,1};
    
    public int max_detectedFrequency = 30; //max frequency the algorithm will search for
    public int min_detectedFrequency = 0; //min frequency the algorithm will search for
    
    public int detectedFrequencyRange = max_detectedFrequency - min_detectedFrequency;
     
    public int sampleRate = (max_detectedFrequency * 2);
    public int graphRateMultiplyer = 10;
    
    int frequencyCount = frequencies.length;
    
}
