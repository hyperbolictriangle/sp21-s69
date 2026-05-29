package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;


public class GuitarHero {
    public final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public int keyboard_length;
    public GuitarString[] strings;

    public GuitarHero() {
        keyboard_length = KEYBOARD.length();
        strings = new GuitarString[keyboard_length];
        for (int i = 0; i < keyboard_length; i++) {
            double string_freq = 440 * Math.pow(2.0, (double) (i - 24) / 12);
            strings[i] = new GuitarString(string_freq);
        }
    }

    public static void main(String[] args) {
        GuitarHero gh = new GuitarHero();

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int ind = gh.KEYBOARD.indexOf(key);
                if (ind != -1) {
                    gh.strings[ind].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString gs: gh.strings) {
                sample += gs.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString gs: gh.strings) {
                gs.tic();
            }
        }

    }
}
