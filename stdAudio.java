package fourier_transform;

import javax.sound.sampled.*;

public final class stdAudio{

    public final int audioRate = 44100;

    private final int bytesPerSecond = 2; // 16-bit audio
    private final int bitsPerSecond = 16; // 16-bit audio
    private final int bufferSize = 4096;

    private SourceDataLine line; // to play the sound
    private byte[] buffer; // our internal buffer
    private int currentBufferSize = 0; // number of samples currently in internal
    
    public stdAudio(){
        init();
    }

    private void init() {
        try {
            AudioFormat format = new AudioFormat((float) audioRate, bitsPerSecond, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, bufferSize * bytesPerSecond);
            buffer = new byte[bufferSize * bytesPerSecond / 3];
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        line.start();
    }

    public void close() {
        line.drain();
        line.stop();
    }
    
    public void play(double in) {
        short s = (short) (Short.MAX_VALUE * in);
        buffer[currentBufferSize++] = (byte) s;
        buffer[currentBufferSize++] = (byte) (s >> 8);
        if (currentBufferSize >= buffer.length) {
            line.write(buffer, 0, buffer.length);
            currentBufferSize = 0;
        }
    }
    
    public void play(double[] input) {
        for (int i = 0; i < input.length; i++) {
            play(input[i]);
        }
    }
}