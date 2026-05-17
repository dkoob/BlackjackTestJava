package util;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FisherYatesShuffle implements ShuffleMethod {
    @Override
    public <T> void shuffle(List<T> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = ThreadLocalRandom.current().nextInt(i + 1);
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}
