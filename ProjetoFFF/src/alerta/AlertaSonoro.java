package alerta;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AlertaSonoro {

    public static AlertaSonoro instance;

    public static AlertaSonoro getInstance() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
            if (instance == null) {
                instance = new AlertaSonoro();
            }
            return instance;
        }

    public AlertaSonoro(){
    }

public void iniciarAlerta() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    File arquivo = new File("src/alerta/alertaserio.wav");
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivo);
    AudioFormat formato = audioInputStream.getFormat();
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, formato);
    SourceDataLine linhaDeAudio = (SourceDataLine) AudioSystem.getLine(info);
    linhaDeAudio.open(formato);
    linhaDeAudio.start();
    int bufferSize = 4096;
    byte[] buffer = new byte[bufferSize];

    int bytesRead = 0;
    while ((bytesRead = audioInputStream.read(buffer)) != -1) {
        linhaDeAudio.write(buffer, 0, bytesRead);
    }
    linhaDeAudio.stop();
    linhaDeAudio.close();
    audioInputStream.close();
}
}
