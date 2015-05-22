import java.io.*;
import java.nio.file.FileSystems;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import sun.audio.*;

/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander, devdaily.com.
 */
public class Sound
{
	private Clip clip;
	private boolean loop;
	AudioInputStream is;
	
	public Sound(String file, boolean loop)
	{
		this.loop = loop;
		try
		{
			clip = AudioSystem.getClip();
			is = AudioSystem.getAudioInputStream(FileSystems.getDefault().getPath("data", file).toFile());
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void play()
	{
		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				
				if(loop)
				{
					try {
						clip.open(is);
					} catch (LineUnavailableException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					while(true)
					{
						if(!clip.isActive())
							clip.start();
					}
				}
				else 
				{
					try {
						clip.open(is);
					} catch (LineUnavailableException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					clip.start();
				}
			}
			
		}).start();
	}

	public void stop()
	{
		clip.stop();
	}
}