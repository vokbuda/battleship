package com.battlefield.ships;

/**
 * @author dubkov
 */
public interface TypeReturn {
	/**
	 * @param size size of cells set
	 * @return appropriate ship, depending on size passed as parameter
	 */
	public Ship returnAppropriate(int size);

}
