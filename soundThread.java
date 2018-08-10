package fourier_transform;

public class soundThread implements Runnable{
    double[] outputSignal = new double[44100];
    double noteDuration;
    stdAudio sp = new stdAudio();
    public soundThread(double Hz, double amp, double phase, double duration){
        noteDuration = duration; 
        for (int i = 0; i < 44100; i++){
            outputSignal[i] = amp * Math.sin((Hz * i * 2 * Math.PI / 44100) + phase); 
        }
    }

    public void run() {
        while(noteDuration-- > 0){
            sp.play(outputSignal);
        }
        sp.close();
    }

}