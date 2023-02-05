package com.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.rafaskoberg.boom.Boom;
import com.rafaskoberg.boom.BoomChannel;
import com.rafaskoberg.boom.effect.DistortionData;
import com.rafaskoberg.boom.effect.ReverbPreset;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private Boom boom;
    private Sound sound1;
    private Sound sound2;
    private Sound sound3;
    private BoomChannel channel1;
    private BoomChannel channel2;

    @Override
    public void create() {
        boom = Boom.init(true);
        sound1 = Gdx.audio.newSound(Gdx.files.internal("numbers.wav"));
        sound2 = Gdx.audio.newSound(Gdx.files.internal("rhythm.wav"));
        sound3 = Gdx.audio.newSound(Gdx.files.internal("carnivalrides.ogg"));
        channel1 = boom.createChannel(1);
        channel2 = boom.createChannel(2);



        DistortionData distortionData = new DistortionData();
        distortionData.edge = 0.7f;
        distortionData.gain = 1f;
        distortionData.lowPassCutoff = 80f;
        distortionData.eqCenter = 1000f;
        distortionData.eqBandwidth = 1000f;
        channel2.addEffect(ReverbPreset.AUDITORIUM);
        channel1.addEffect(distortionData);

        long sound1Id = boom.play(sound1, channel1);
        long sound2Id = boom.play(sound2, channel2);

        sound3.play();
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {
        channel1.removeAllEffects();
        channel2.removeAllEffects();
        sound1.dispose();
        sound2.dispose();
        sound3.dispose();
    }
}