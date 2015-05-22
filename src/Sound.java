import java.io.*;
import java.nio.file.FileSystems;

import sun.audio.*;

/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander, devdaily.com.
 */
public class Sound
{
	private AudioStream as;
	private boolean loop;
	public Sound(String file, boolean loop)
	{
		this.loop = loop;

		InputStream in = null;
		try {
			in = new FileInputStream(FileSystems.getDefault().getPath("data", file).toFile());


			// create an audiostream from the inputstream
			as = new AudioStream(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					while(true)
					{
						AudioPlayer.player.start(as);
					}
				}
				else 
					AudioPlayer.player.start(as);
			}
			
		}).start();
	}

	public void stop()
	{
		AudioPlayer.player.stop(as);
	}
}