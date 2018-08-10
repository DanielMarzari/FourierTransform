Fourier Transform

Simple fourier transform with basic algorithm that follows the equation for the fourier transform. 
      Formula: F[k] = SUM(0,N,X[n] * e ^ (-2PI*k*n/N)) 
      Euler's Formula: e^x = cos(x) + i * sin(x)
      New Formula: SUM(X[n] * (cos(-2PI*k*n/N) + i * sin(-2PI*k*n/N)))
            Magnitude: M^2 = cosComponent^2 + sinComponent^2 //doen't use i***
            //Theta (Phase Shift) can also be found however this program does not handle this feature.
            
      Algorithm: Magnitude = Sqrt(SUM(X[n] * cos(-2PI*k*n/N))^2 + SUM(X[n] * sin(-2PI*k*n/N))^2)
            
Files: 
  main.java //code graphs the output of the fourier transform and input wave
  fourierSettings.java //file contains all information pertaining to the inputs of the user
  sampleWave.java //generates the superimposed waves with the frequencies from the user
  fourierTransform.java //completes the fourierTransform on the asembled wave from sampleWave.java 
