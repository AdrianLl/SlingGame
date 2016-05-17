package objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BeanBagColor {

	RED, PURPLE, PINK, GREEN, BLUE;

	private static final List<BeanBagColor> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static BeanBagColor randomBeanBag() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

}
