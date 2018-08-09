/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourier_transform;

/**
 * @author Daniel
 * 
 * All constructors use amplitude * Sin(2pi * (frequency/sampleRate)t + phase)
 * a = amplitude 
 * 2pi = 6.27...
 * f = frequency 
 * s = sampling rate
 * t = time
 * c = phase shift
 */
public class sampleWave {
    public double maxAmplitude;
    public double[] pointArr; //minimum array for computer to analyze the waves and graph array for display

    //multiple frequencies of different phase and amplitude in superposition 
    public sampleWave(int[] frequencies, double[] amplitudes, double[] phases, int sampleRate, int graphMultiplyer){
        double amplitude = 1, phase = 0; 
        
        pointArr = new double[(sampleRate * graphMultiplyer) + 1];
        
        for(double a : amplitudes){
            if(a > maxAmplitude){
                maxAmplitude = a;
            }
        }
        
        for(int i = 0; i <= sampleRate * graphMultiplyer; i++){
            for(int j = 0; j < frequencies.length; j++){
                if(amplitudes != null){
                    amplitude = amplitudes[j];
                }
                if(phases != null){
                    phase = phases[j] * Math.PI;    // All phase shifts are relative to pi
                }
                pointArr[i] += amplitude * Math.sin((frequencies[j] * i * 2 * Math.PI / (sampleRate * graphMultiplyer)) + phase); 
            }
        }
        System.out.println(pointArr.length);
    }
}
