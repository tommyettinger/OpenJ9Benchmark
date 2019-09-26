package com.github.tommyettinger.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

public class SpriteBatchBenchmark extends ApplicationAdapter
{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = false;
		config.foregroundFPS = 0;
		config.backgroundFPS = 0;
		config.resizable = false;
		config.width = 512 + 32;
		config.height = 512 + 32;
		config.forceExit = false;
		new LwjglApplication(new com.github.tommyettinger.desktop.SpriteBatchBenchmark(), config);
	}
	
	private FPSLogger fps = new FPSLogger();
	private SpriteBatch batch;
	private TextureRegion region;
	private GLProfiler profiler;
	private float time;
	private int drawCalls;
	private int frames;
	
	@Override
	public void create() {
		profiler = new GLProfiler(Gdx.graphics);
		profiler.enable();
		batch = new SpriteBatch(8191);
		Pixmap pm = new Pixmap(32, 32, Format.RGBA8888);
//		pm.setColor(Color.BLUE);
//		pm.drawRectangle(0, 0, 32, 32);
		Texture texture = new Texture(pm);
		region = new TextureRegion(texture);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1f, 1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		final int N = 8191 * 2;
		batch.begin();
		for(int i=0 ; i<N ; i++){
			batch.draw(region, i * 0xC13FA9A902A6328FL >>> 55, i * 0x91E10DA5C79E7B1DL >>> 55);
		}
		batch.end();
		fps.log();
		float delta = Gdx.graphics.getDeltaTime();
		time += delta;
		if(time > 1){
			System.out.println(drawCalls + " / " + frames);
			time = 0;
			drawCalls = 0;
			frames = 0;
		}
		drawCalls += profiler.getDrawCalls();
		frames++;
		profiler.reset();
	}

}