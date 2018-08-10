package fourier_transform;

/**
 *
 * @author Daniel Marzari
 */
public class fourierSettings {
    static int[] frequencies = new int[] {440,490, 550};
    static double[] amplitudes = new double[] {1,1,1};
    static double[] phases = new double[] {0,1,1};
    
    public int max_detectedFrequency = 600; //max frequency the algorithm will search for
    public int min_detectedFrequency = 400; //min frequency the algorithm will search for
    
    public int detectedFrequencyRange = max_detectedFrequency - min_detectedFrequency;
     
    public int sampleRate = (max_detectedFrequency * 2);
    public int audioRate = 44100;
    public int graphRateMultiplyer = 10;
    public int audioDuration = 5;  // in seconds
    
    int frequencyCount = frequencies.length;
    
}
