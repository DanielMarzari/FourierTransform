package fourier_transform;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 * change k from whole number Hz to make it more accurate
 */
public class fourierTransform {
    public double[][] fourierTransform(fourierSettings fs, sampleWave sw){ 
        /*
            mathArr is a triple array that contains cos, sine, magnitude, and evneutally theta
            mathArr has length of sample frequency range + 1 bc it is a 1 base array
        */
        double[][] mathArr = new double[3][fs.detectedFrequencyRange + 1]; 
        
        System.out.println("Analysing frequencies from " + fs.min_detectedFrequency + " to " + fs.max_detectedFrequency + "Hz");
        System.out.println("Computation will finish in "); //FINISH LINE OF CODE
        
        for(int k = fs.min_detectedFrequency; k <= fs.max_detectedFrequency; k++){ //for the itteration of each bucket (Xk)
            for(int n = 0; n <= fs.sampleRate; n++){ //for the itteration of each sample point (n)
                mathArr[0][k - fs.min_detectedFrequency] += sw.pointArr[n * fs.graphRateMultiplyer] * Math.cos(-2 * Math.PI * k * n / (fs.sampleRate)); //cosine values
                mathArr[1][k - fs.min_detectedFrequency] += sw.pointArr[n * fs.graphRateMultiplyer] * Math.sin(-2 * Math.PI * k * n / (fs.sampleRate)); //sine values * imaginary "j" 
            }
            mathArr[2][k] = Math.sqrt(Math.pow(mathArr[0][k], 2) + Math.pow(mathArr[1][k], 2)) * 2 / fs.sampleRate; // two sided frequency plot error fix || returns amplitude
            //System.out.println(k + ": " + mathArr[2][k]);
            if((mathArr[2][k] > 0.1) && (k <= fs.max_detectedFrequency) && (k >= fs.min_detectedFrequency)){
                System.out.format("%d Hz = %.2f%n", k, Math.floor(Math.ceil(mathArr[2][k] * 100)/100)); //WOW DIDN'T KNOW THIS EXISTED!!
            }
        }
        return mathArr;
    }
}
