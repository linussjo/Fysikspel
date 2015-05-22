import java.io.*;
import java.nio.file.FileSystems;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import sun.audio.*;

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
				try {
					clip.open(is);
				} catch (LineUnavailableException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try
				{
					if(loop)
						clip.loop(clip.LOOP_CONTINUOUSLY);
					
					clip.start();
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
			
		}).start();
		System.out.println("s");
	}

	public void stop()
	{
		clip.stop();
	}
}